package dao;

import model.Customer;
import model.UserAccount;
import model.Wallet;
import model.enumaration.Gender;
import model.enumaration.compteType;

import java.sql.*;

public class CustomerDao {

    private static final String INSERT = "INSERT INTO Customer (matricule,lastname,firstname ,phoneNumber,email,gender,idUserAccount,idWallet) VALUES(?,?,?,?,?,?,?,?)";
    private static final String UPDATE = "UPDATE Customer SET matricule=?, lastname=?, firstname=?, phoneNumber=?, email=?, gender=?, idUserAccount=?, idWallet=? WHERE idUserAccount=?";
    private static final String DELETE = "DELETE FROM Customer WHERE idUserAccount=?";
    private static final String READ = "SELECT * FROM Customer WHERE id=?";
    private static final Connection connection;//format de l'url

    static {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fintechAda","root","root");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Customer createCustomer(Customer customer){
        try {
            PreparedStatement statement = connection.prepareStatement(INSERT,Statement.RETURN_GENERATED_KEYS);
            System.out.println(customer.getMatricule());
            statement.setString(1, customer.getMatricule());
            statement.setString(2,customer.getLastName());
            statement.setString(3,customer.getFirstName());
            statement.setString(4,customer.getPhoneNumber());
            statement.setString(5,customer.getEmail());
            statement.setString(6,customer.getGender().name());
            statement.setLong(7,customer.getUserAccount().getId());
            statement.setLong(8,customer.getWallet().getId());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Erreur de requete, aucune ligne n'a ete modifiee.");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    customer.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
            return customer;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateCustomer(Customer customer){
        try {

            PreparedStatement statement = connection.prepareStatement(UPDATE);
            statement.setString(1, customer.getMatricule());
            statement.setString(2,customer.getLastName());
            statement.setString(3,customer.getFirstName());
            statement.setString(4,customer.getPhoneNumber());
            statement.setString(5,customer.getEmail());
            statement.setString(6,customer.getGender().name());
            statement.setLong(7,customer.getUserAccount().getId());
            statement.setLong(8,customer.getWallet().getId());
            statement.setLong(9,customer.getUserAccount().getId());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Erreur de requete, aucune ligne n'a ete modifiee.");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteCustomer(Customer customer){
        try {
            PreparedStatement statement = connection.prepareStatement(DELETE);
            statement.setLong(1,customer.getUserAccount().getId());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Erreur de requete, aucune ligne n'a ete modifiee.");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Customer readCustomer(Long id){
        try {
            PreparedStatement statement = connection.prepareStatement(READ);
            statement.setLong(1,id);
            System.out.println("Recherche du customer avec ID = " + id);

            ResultSet result = statement.executeQuery();

            if (result.next()){
                Customer customerNew = new Customer();
                customerNew.setGender(Gender.valueOf(result.getString("gender")));
                customerNew.setEmail(result.getString("email"));
                customerNew.setFirstName(result.getString("firstName"));
                customerNew.setLastName(result.getString("lastName"));
                customerNew.setPhoneNumber(result.getString("phoneNumber"));


                UserAccount userAccount;
                UserAccountDao userAccountDao = new UserAccountDao();
                userAccount = userAccountDao.findById(result.getLong("idUserAccount"));
                customerNew.setUserAccount(userAccount);

                WalletDao walletDao = new WalletDao();
                Wallet wallet = walletDao.readWallet(result.getLong("idWallet"));
                customerNew.setWallet(wallet);
                //   String resCompteType = result.getString("compteType");

              //  switch (resCompteType){
             //       case "customer": customerNew.getUserAccount().setCompteType(compteType.CUSTOMER);break;
               //     case "admin": customerNew.getUserAccount().setCompteType(compteType.ADMIN);break;
                 //   case "merchant": customerNew.getUserAccount().setCompteType(compteType.MERCHANT);
               // }
                return customerNew;
            }else {
                throw new SQLException("Customer with ID " + id + " not found.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}








