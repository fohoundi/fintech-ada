package ci.ada.facades;


import ci.ada.common.ConnectionSingleton;
import ci.ada.dao.AdminDao;
import ci.ada.dao.CustomerDao;
import ci.ada.dao.MerchantDao;
import ci.ada.dao.UserAccountDao;
import ci.ada.iterators.GenericIterator;
import ci.ada.iterators.UserIteratorInterface;
import ci.ada.model.Customer;
import ci.ada.model.UserAccount;

import java.sql.Connection;
import java.util.List;

public class UserFacade {
    private static final Connection connection = ConnectionSingleton.getInstance().getConnection();
    private final UserAccountDao userAccountDao = new UserAccountDao();
    private final CustomerDao customerDao = new CustomerDao();
    private final MerchantDao merchantDao = new MerchantDao();
    private final AdminDao adminDao = new AdminDao();

    public UserFacade() {

    }

    // --- Customer CRUD ---
    public Customer createCustomer(Customer customer){ return customerDao.createCustomer(customer);}
    public Customer updateCustomer(Customer customer){ return customerDao.updateCustomer(customer);}
    public Customer findCustomerById(Long id){return customerDao.findCustomerById(id);}
    public Customer findCustomerByLogin(Long id){return customerDao.findCustomerByLogin(id);}
    //public List<Customer> findAllCustomers(){return customerDao.findAllCustomers();}
    public UserIteratorInterface<Customer> findAllCustomers(){return new GenericIterator<>(customerDao.findAllCustomers());} //implementation de iterator



    // --- UserAccount CRUD ---
    public UserAccount createUserAccount(UserAccount userAccount) {
        return userAccountDao.createUser(userAccount);
    }

    public UserAccount updateUserAccount(UserAccount userAccount) {
        return userAccountDao.updateUserAccount(userAccount);
    }

    public void deleteUserAccount(Long id) {
        userAccountDao.deleteUserAccount(id);
    }

    public UserAccount findUserAccountById(Long id) {
        return userAccountDao.findById(id);
    }

    public List<UserAccount> findAllUserAccounts() {
        return userAccountDao.findAll();
    }

}
