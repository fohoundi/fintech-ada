package ci.ada.dao;


import ci.ada.common.ConnectionSingleton;
import ci.ada.model.Customer;
import ci.ada.model.UserAccount;
import ci.ada.model.Wallet;
import ci.ada.model.enumaration.Gender;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao {

    private static final String INSERT = "INSERT INTO Customer (matricule,lastname,firstname ,phoneNumber,email,gender,idUserAccount,idWallet) VALUES(?,?,?,?,?,?,?,?)";
    private static final String UPDATE_BY_ID = "UPDATE Customer SET matricule=?, lastname=?, firstname=?, phoneNumber=?, email=?, gender=?, idUserAccount=?, idWallet=? WHERE id=?";
    private static final String DELETE_BY_ID = "DELETE FROM Customer WHERE id=?";
    private static final String DELETE_BY_LOGIN = "DELETE FROM Customer WHERE idUserAccount=?";
    private static final String READ_BY_ID = "SELECT * FROM Customer WHERE id=?";
    private static final String READ_BY_LOGIN = "SELECT * FROM Customer WHERE idUserAccount=?";
    private static final String READ_ALL = "SELECT * FROM Customer ";
    private static final Connection connection = ConnectionSingleton.getInstance().getConnection();


    public Customer createCustomer(Customer customer){
        try {
            PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            statement = setStatementProps(statement,customer);
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

    public Customer updateCustomer(Customer customer){
        try {

            PreparedStatement statement = connection.prepareStatement(UPDATE_BY_ID);
            statement = setStatementProps(statement,customer);
            statement.setLong(9,customer.getId());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Erreur de requete, aucune ligne n'a ete modifiee.");
            }else {
                return customer;
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteCustomerById(Long id){
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
    public void deleteCustomerByLogin(Long id){
        try {
            PreparedStatement statement = connection.prepareStatement(DELETE_BY_LOGIN);
            statement.setLong(1,id);

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Erreur de requete, aucune ligne n'a ete modifiee.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Customer findCustomerById(Long id){
        try {
            PreparedStatement statement = connection.prepareStatement(READ_BY_ID);
            statement.setLong(1,id);
            return getCustomer(statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Customer findCustomerByLogin(Long id){
        try {
            PreparedStatement statement = connection.prepareStatement(READ_BY_LOGIN);
            statement.setLong(1,id);
            return getCustomer(statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Customer> findAllCustomers(){
        try {
            PreparedStatement statement = connection.prepareStatement(READ_ALL);
            final List<Customer> finalCustomers = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                Customer customer = mappingCustomerFromResultSet(resultSet);
                finalCustomers.add(customer);
            }
            return finalCustomers;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /*******Utilitaires **********/
    private PreparedStatement setStatementProps(PreparedStatement statement, Customer customer) throws SQLException {
        statement.setString(1, customer.getMatricule());
        statement.setString(2,customer.getLastName());
        statement.setString(3,customer.getFirstName());
        statement.setString(4,customer.getPhoneNumber());
        statement.setString(5,customer.getEmail());
        statement.setString(6,customer.getGender().name());
        statement.setLong(7,customer.getUserAccount().getId());
        statement.setLong(8,customer.getWallet().getId());

        return statement;
    }

    private Customer getCustomer(PreparedStatement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery();
        Customer customerNew = new Customer();
        while (resultSet.next()){
            customerNew = mappingCustomerFromResultSet(resultSet);
        }
        return customerNew;
    }

    private Customer mappingCustomerFromResultSet(ResultSet result) throws SQLException {
        Customer customerNew = new Customer();
        try {
            customerNew.setGender(Gender.valueOf(result.getString("gender")));
            customerNew.setEmail(result.getString("email"));
            customerNew.setFirstName(result.getString("firstName"));
            customerNew.setLastName(result.getString("lastName"));
            customerNew.setPhoneNumber(result.getString("phoneNumber"));
            customerNew.setMatricule(result.getString("matricule"));

            UserAccount userAccount;
            UserAccountDao userAccountDao = new UserAccountDao();
            userAccount = userAccountDao.findById(result.getLong("idUserAccount"));
            customerNew.setUserAccount(userAccount);

            WalletDao walletDao = new WalletDao();
            Wallet wallet = walletDao.readWallet(result.getLong("idWallet"));
            if (wallet == null) {
                throw new NullPointerException("Le portefeuille lié au client est introuvable.");
            }
            customerNew.setWallet(wallet);


        } catch (IllegalArgumentException | SQLException e) {
            throw new RuntimeException(e);
        }
        return customerNew;
    }

}








