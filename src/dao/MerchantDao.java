package dao;

import model.Customer;
import model.Marchant;
import model.UserAccount;
import model.Wallet;
import model.enumaration.Gender;

import java.sql.*;

public class MerchantDao {


    private static final String INSERT = "INSERT INTO Merchant (matricule, lastname,firstname ,phoneNumber,email,localisation,idUserAccount,idWallet) VALUES(?,?,?,?,?,?,?,?)";
    private static final String UPDATE = "UPDATE Merchant SET matricule=?, lastname=?, firstname=?, phoneNumber=?, email=?, localisation=?, idUserAccount=?, idWallet=? WHERE idUserAccount=?";
    private static final String DELETE = "DELETE FROM Merchant WHERE idUserAccount=?";
    private static final String READ = "SELECT * FROM Merchant WHERE id=?";
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
            statement.setString(1,marchant.getMatricule());
            statement.setString(2,marchant.getLastName());
            statement.setString(3,marchant.getFirstName());
            statement.setString(4,marchant.getPhoneNumber());
            statement.setString(5,marchant.getEmail());
            statement.setString(6,marchant.getLocation());
            statement.setLong(7,marchant.getUserAccount().getId());
            statement.setLong(8,marchant.getWallet().getId());

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

            PreparedStatement statement = connection.prepareStatement(UPDATE);
            statement.setString(1,marchant.getMatricule());
            statement.setString(2,marchant.getLastName());
            statement.setString(3,marchant.getFirstName());
            statement.setString(4,marchant.getPhoneNumber());
            statement.setString(5,marchant.getEmail());
            statement.setString(6,marchant.getLocation());
            statement.setLong(7,marchant.getUserAccount().getId());
            statement.setLong(8,marchant.getWallet().getId());
            statement.setLong(9,marchant.getUserAccount().getId());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Erreur de requete, aucune ligne n'a ete modifiee.");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteMarchant(Marchant marchant){
        try {
            PreparedStatement statement = connection.prepareStatement(DELETE);
            statement.setLong(1,marchant.getUserAccount().getId());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Erreur de requete, aucune ligne n'a ete modifiee.");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Marchant readMarchant(Long id){
        try {
            PreparedStatement statement = connection.prepareStatement(READ);
            statement.setLong(1,id);
            System.out.println("Recherche du customer avec ID = " + id);

            ResultSet result = statement.executeQuery();

            if (result.next()){
                Marchant marchantNew = new Marchant();
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
                //   String resCompteType = result.getString("compteType");

                //  switch (resCompteType){
                //       case "customer": customerNew.getUserAccount().setCompteType(compteType.CUSTOMER);break;
                //     case "admin": customerNew.getUserAccount().setCompteType(compteType.ADMIN);break;
                //   case "merchant": customerNew.getUserAccount().setCompteType(compteType.MERCHANT);
                // }
                return marchantNew;
            }else {
                throw new SQLException("Customer with ID " + id + " not found.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
