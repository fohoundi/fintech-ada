package dao;

import model.UserAccount;
import model.enumaration.compteType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserAccountDao {

    private static final String INSERT = "INSERT INTO UserAccount(compteType,login,password) VALUES(?,?,?)";
    private static final String UPDATE_BY_LOGIN = "UPDATE UserAccount SET login=?, password=?, compteType=? WHERE login=?";
    private static final String DELETE_BY_ID = "DELETE FROM UserAccount WHERE id=?";
    private static final String READ_BY_LOGIN = "SELECT * FROM UserAccount WHERE login=?";
    private static final String READ_BY_ID = "SELECT * FROM UserAccount WHERE id=?";
    private static final String READ_ALL = "SELECT * FROM UserAccount ";
    private static final Connection connection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fintechAda","root","root");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public  UserAccount createUser(UserAccount userAccount){
        /*
             -getConnection ici prend 3 parametres: url, le username, le mot de passe
             -format de l'url: jdbc:type_de_BD://ipduserveur:port/nomBD
             -le port est celui sur lequel la machine hote ecoute (mysql)
         */
        try {

            PreparedStatement statement =  connection.prepareStatement(INSERT,Statement.RETURN_GENERATED_KEYS);
            // statement.setString(1,userAccount.getCompteType().name());
            String type = (userAccount.getCompteType() != null)
                    ? userAccount.getCompteType().name()
                    : "CUSTOMER";
            statement.setString(1, type);

            statement.setString(2,userAccount.getLogin());//
            statement.setString(3,userAccount.getPassword());//
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    userAccount.setId(generatedKeys.getLong(1)); // suppose qu’il y a un setId
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
            return userAccount;

        } catch (SQLException e) {
            System.out.println("Error:" + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public  UserAccount updateUserAccount(UserAccount userAccount){

        try {
            PreparedStatement statement = connection.prepareStatement(UPDATE_BY_LOGIN);
            statement.setString(1,userAccount.getLogin());//
            statement.setString(2,userAccount.getPassword());//
            statement.setString(3,userAccount.getCompteType().name());
            statement.setString(4, userAccount.getLogin());
            statement.executeUpdate();
            return userAccount;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public void deleteUserAccount(Long id){
        try {
            PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID);
            statement.setLong(1,id);
            statement.executeUpdate();
            /*if (deteltedRows < 0){
                throw new SQLException("Impossible d'effectuer l'operation");
            }*/
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public UserAccount findById(Long id){
        try {
            PreparedStatement statement = connection.prepareStatement(READ_BY_ID);
            statement.setLong(1,id);
            return getUserAccount(statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public UserAccount findByLogin(String login) {
        try {
            PreparedStatement statement = connection.prepareStatement(READ_BY_LOGIN);
            statement.setString(1, login);
            return getUserAccount(statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<UserAccount> findAll(){
        try {
            PreparedStatement statement = connection.prepareStatement(READ_ALL);
            ResultSet resultSet = statement.executeQuery();
            final List<UserAccount> finalUserAccounts = new ArrayList<>();
            while (resultSet.next()){
                UserAccount userAccount = mappingUserFromResultSet(resultSet);
                finalUserAccounts.add(userAccount);
            }
            return finalUserAccounts;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    /*******Utilitaires **********/
    private UserAccount getUserAccount(PreparedStatement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery();
        UserAccount userAccount = new UserAccount();
        while (resultSet.next()){
           userAccount = mappingUserFromResultSet(resultSet);
        }
        return userAccount;

    }
    private  UserAccount mappingUserFromResultSet(ResultSet resultSet) throws SQLException {
        UserAccount userAccount = new UserAccount();
        try {
            userAccount.setId(resultSet.getLong("id"));
            userAccount.setLogin(resultSet.getString("login"));
            userAccount.setPassword(resultSet.getString("password"));
            userAccount.setCompteType(compteType.valueOf(resultSet.getString("compteType")));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userAccount;
    }
    private PreparedStatement makeQuery(PreparedStatement statement,UserAccount userAccount) throws SQLException {
        statement.setString(1,userAccount.getLogin());//
        statement.setString(2,userAccount.getPassword());//
        statement.setString(3,userAccount.getCompteType().name());
        statement.setString(4, userAccount.getLogin());
        statement.executeUpdate();
        return statement;
    }

}
