package dao;

import model.UserAccount;
import model.enumaration.compteType;

import java.sql.*;

public class UserAccountDao {

    private static final String INSERT = "INSERT INTO UserAccount(compteType,login,password) VALUES(?,?,?)";
    private static final String UPDATE = "UPDATE UserAccount SET login=?, password=?, compteType=? WHERE login=?";
    private static final String DELETE = "DELETE FROM UserAccount WHERE login=?";
    private static final String READ = "SELECT * FROM UserAccount WHERE login=?";
    private static final Connection connection;//format de l'url

    static {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fintechAdaDB","root","root");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public  UserAccount create(UserAccount userAccount){
        /*
             -getConnection ici prend 3 parametres: url, le username, le mot de passe
             -format de l'url: jdbc:type_de_BD://ipduserveur:port/nomBD
             -le port est celui sur lequel la machine hote ecoute (mysql)
         */
        try {

            PreparedStatement statement =  connection.prepareStatement(INSERT,Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,userAccount.getCompteType().name());
            statement.setString(2,userAccount.getLogin());
            statement.setString(3,userAccount.getPassword());


            int affectedRows = statement.executeUpdate();//executeUpdate est utiliser pour les op d'ecriture dans la bd

            if (affectedRows == 0) {
                throw new SQLException("Erreur de requete, aucune ligne n'a ete modifiee.");
            }
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

    public  UserAccount update(UserAccount userAccount){

        try {
            PreparedStatement statement = connection.prepareStatement(UPDATE);
            statement.setString(1,userAccount.getLogin());//
            statement.setString(2,userAccount.getPassword());//
            statement.setString(3,userAccount.getCompteType().name());
            statement.setString(4, userAccount.getLogin());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Erreur de requete, aucune ligne n'a ete modifiee.");
            }

            return userAccount;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteUserAccount(String login){
        try {
            PreparedStatement statement = connection.prepareStatement(DELETE);
            statement.setString(1,login);
            int deteltedRows = statement.executeUpdate();
            if (deteltedRows < 0){
                throw new SQLException("Impossible d'effectuer l'operation");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public UserAccount readUserAccount(String login){
        try {
            PreparedStatement statement = connection.prepareStatement(READ);
            statement.setString(1,login);
            ResultSet result = statement.executeQuery();

            if (result.next()){
                UserAccount user = new UserAccount();
                user.setId(result.getLong("id"));
                user.setLogin(result.getString("login"));
                user.setPassword(result.getString("password"));
                String resCompteType = result.getString("compteType");

                switch (resCompteType){
                    case "customer": user.setCompteType(compteType.CUSTOMER);break;
                    case "admin": user.setCompteType(compteType.ADMIN);break;
                    case "merchant": user.setCompteType(compteType.MERCHANT);
                }
                return user;
            }else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
