package dao;

import model.Customer;
import model.Marchant;
import model.UserAccount;
import model.Wallet;
import model.enumaration.Gender;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MerchantDao {


    private static final String INSERT = "INSERT INTO Merchant (matricule, lastname,firstname ,phoneNumber,email,localisation,idUserAccount,idWallet) VALUES(?,?,?,?,?,?,?,?)";
    private static final String UPDATE_BY_ID = "UPDATE Merchant SET matricule=?, lastname=?, firstname=?, phoneNumber=?, email=?, localisation=?, idUserAccount=?, idWallet=? WHERE id=?";
    private static final String DELETE_BY_ID = "DELETE FROM Merchant WHERE id=?";
    private static final String READ_BY_ID = "SELECT * FROM Merchant WHERE id=?";
    private static final String READ_ALL = "SELECT * FROM Merchant";
    private static final Connection connection;//format de l'url

    static {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fintechAda","root","root");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Marchant createMerchant(Marchant marchant){
        try {
            PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            statement = setStatementProps(statement,marchant);

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Erreur de requete, aucune ligne n'a ete modifiee.");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    marchant.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
            return marchant;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateMarchant(Marchant marchant){
        try {

            PreparedStatement statement = connection.prepareStatement(UPDATE_BY_ID);
            statement = setStatementProps(statement,marchant);
            statement.setLong(9,marchant.getId());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Erreur de requete, aucune ligne n'a ete modifiee.");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void deleteMarchantById(Long id){
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


    public Marchant findMarchantById(Long id){
        try {
            PreparedStatement statement = connection.prepareStatement(READ_BY_ID);
            statement.setLong(1,id);
            return getMarchand(statement);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Marchant> findAll(){
        try {
            PreparedStatement statement = connection.prepareStatement(READ_ALL);
            final List<Marchant> finalMarchands = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                Marchant marchant = mappingMarchandFromResultSet(resultSet);
                finalMarchands.add(marchant);
            }
            return finalMarchands;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /*******Utilitaires **********/
    private PreparedStatement setStatementProps(PreparedStatement statement, Marchant marchant) throws SQLException {

        statement.setString(1,marchant.getMatricule());
        statement.setString(2,marchant.getLastName());
        statement.setString(3,marchant.getFirstName());
        statement.setString(4,marchant.getPhoneNumber());
        statement.setString(5,marchant.getEmail());
        statement.setString(6,marchant.getLocation());
        statement.setLong(7,marchant.getUserAccount().getId());
        statement.setLong(8,marchant.getWallet().getId());


        return statement;
    }

    private Marchant getMarchand(PreparedStatement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery();
        Marchant marchantNew = new Marchant();
        while (resultSet.next()){
            marchantNew = mappingMarchandFromResultSet(resultSet);
        }
        return marchantNew;
    }

    private Marchant mappingMarchandFromResultSet(ResultSet result) throws SQLException {
        Marchant marchantNew = new Marchant();
        try {

            marchantNew.setLocation(result.getString("localisation"));
            marchantNew.setEmail(result.getString("email"));
            marchantNew.setFirstName(result.getString("firstName"));
            marchantNew.setLastName(result.getString("lastName"));
            marchantNew.setPhoneNumber(result.getString("phoneNumber"));


            UserAccount userAccount;
            UserAccountDao userAccountDao = new UserAccountDao();
            userAccount = userAccountDao.findById(result.getLong("idUserAccount"));
            marchantNew.setUserAccount(userAccount);

            WalletDao walletDao = new WalletDao();
            Wallet wallet = walletDao.readWallet(result.getLong("idWallet"));
            marchantNew.setWallet(wallet);

            return marchantNew;
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }
}
