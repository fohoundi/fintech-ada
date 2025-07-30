package dao;

import model.*;
import model.enumaration.Gender;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AdminDao {
    private static final String INSERT = "INSERT INTO Admin (matricule, lastname,firstname ,phoneNumber,email,idUserAccount,privilege) VALUES(?,?,?,?,?,?,?)";
    private static final String UPDATE = "UPDATE Admin SET matricule=?, lastname=?, firstname=?, phoneNumber=?, email=?, idUserAccount=?, privilege=? WHERE idUserAccount=?";
    private static final String DELETE_BY_ID = "DELETE FROM Admin WHERE id=?";
    private static final String READ_BY_ID = "SELECT * FROM Admin WHERE id=?";
    private static final String READ_BY_LOGIN = "SELECT * FROM Admin WHERE idUserAccount=?";
    private static final String READ_ALL = "SELECT * FROM Admin";

    private static final Connection connection;//format de l'url

    static {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fintechAda","root","root");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Admin createAdmin(Admin admin){
        try {
            PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            statement = setStatementProps(statement,admin);
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                System.out.println(generatedKeys);
                if (generatedKeys.next()) {
                    admin.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
            return admin;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateAdmin(Admin admin){
        try {

            PreparedStatement statement = connection.prepareStatement(UPDATE);
            statement = setStatementProps(statement,admin);
            statement.setLong(8,admin.getUserAccount().getId());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Erreur de requete, aucune ligne n'a ete modifiee.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void deleteAdminById(Long id){
        try {
            PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID);
            statement.setLong(1,id);

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Erreur de requete, aucune ligne n'a ete modifiee.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Admin findadminById (Long id){
        try {
            PreparedStatement statement = connection.prepareStatement(READ_BY_ID);
            statement.setLong(1,id);

            return getAdmin(statement);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Admin findadminByLogin (Long id){
        try {
            PreparedStatement statement = connection.prepareStatement(READ_BY_LOGIN);
            statement.setLong(1,id);
            return getAdmin(statement);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Admin> findAll(){
        try {
            PreparedStatement statement = connection.prepareStatement(READ_ALL);
            final List<Admin> finalAdmins = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                Admin admin = mappingAdminFromResultSet(resultSet);
                finalAdmins.add(admin);
            }
            return finalAdmins;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    /*******Utilitaires **********/
    private PreparedStatement setStatementProps(PreparedStatement statement, Admin admin) throws SQLException {
        statement.setString(1,admin.getMatricule());
        statement.setString(2,admin.getLastName());
        statement.setString(3,admin.getFirstName());
        statement.setString(4,admin.getPhoneNumber());
        statement.setString(5,admin.getEmail());
        statement.setLong(6,admin.getUserAccount().getId());
        statement.setString(7,admin
                .getPrivileges()
                .stream()
                .collect(Collectors.joining(", ")));

        return statement;
    }

    private Admin getAdmin(PreparedStatement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery();
        Admin admin = new Admin();

        while (resultSet.next()){
            admin = mappingAdminFromResultSet(resultSet);
        }
        return admin;
    }

    private Admin mappingAdminFromResultSet(ResultSet result) throws SQLException {
        Admin admin = new Admin();
        try {
            admin.setPrivileges(Collections.singletonList(result.getString("privilege")));
            admin.setPrivileges(Arrays.asList(result.getString("privilege").split(",\\s*")));
            admin.setEmail(result.getString("email"));
            admin.setFirstName(result.getString("firstName"));
            admin.setLastName(result.getString("lastName"));
            admin.setPhoneNumber(result.getString("phoneNumber"));



            UserAccount userAccount;
            UserAccountDao userAccountDao = new UserAccountDao();
            userAccount = userAccountDao.findById(result.getLong("idUserAccount"));
            admin.setUserAccount(userAccount);

            return admin;
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
