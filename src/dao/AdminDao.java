package dao;

import model.Admin;
import model.Marchant;
import model.UserAccount;
import model.Wallet;

import java.sql.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

public class AdminDao {
    private static final String INSERT = "INSERT INTO Admin (matricule, lastname,firstname ,phoneNumber,email,idUserAccount,privilege) VALUES(?,?,?,?,?,?,?)";
    private static final String UPDATE = "UPDATE Admin SET matricule=?, lastname=?, firstname=?, phoneNumber=?, email=?, idUserAccount=?, privilege=? WHERE idUserAccount=?";
    private static final String DELETE = "DELETE FROM Admin WHERE idUserAccount=?";
    private static final String READ = "SELECT * FROM Admin WHERE id=?";
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

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Erreur de requete, aucune ligne n'a ete modifiee.");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
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
            statement.setString(1,admin.getMatricule());
            statement.setString(2,admin.getLastName());
            statement.setString(3,admin.getFirstName());
            statement.setString(4,admin.getPhoneNumber());
            statement.setString(5,admin.getEmail());
            statement.setLong(6,admin.getUserAccount().getId());
            statement.setString(7,admin
                    .getPrivileges()
                    .stream()
                    .collect(Collectors.joining(", "))
            );
            statement.setLong(8,admin.getUserAccount().getId());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Erreur de requete, aucune ligne n'a ete modifiee.");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Admin readAdmin(Long id){
        try {
            PreparedStatement statement = connection.prepareStatement(READ);
            statement.setLong(1,id);
            System.out.println("Recherche du customer avec ID = " + id);

            ResultSet result = statement.executeQuery();

            if (result.next()){
                Admin admin = new Admin();
                admin.setPrivileges(Collections.singletonList(result.getString("privileges")));
                admin.setPrivileges(Arrays.asList(result.getString("privileges").split(",\\s*")));
                admin.setEmail(result.getString("email"));
                admin.setFirstName(result.getString("firstName"));
                admin.setLastName(result.getString("lastName"));
                admin.setPhoneNumber(result.getString("phoneNumber"));



                UserAccount userAccount;
                UserAccountDao userAccountDao = new UserAccountDao();
                userAccount = userAccountDao.findById(result.getLong("idUserAccount"));
                admin.setUserAccount(userAccount);

                return admin;
            }else {
                throw new SQLException("Customer with ID " + id + " not found.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
