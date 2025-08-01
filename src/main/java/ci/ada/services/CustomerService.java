package ci.ada.services;



import ci.ada.dao.CustomerDao;
import ci.ada.model.Customer;

import java.util.List;

public class CustomerService implements UserServiceInterface <Customer>{
    private final static CustomerDao customerDao = new CustomerDao();

    @Override
    public Customer register(Customer user) {
        user = customerDao.createCustomer(user);
        return user;
    }

    @Override
    public Customer update(Customer user) {
        user = customerDao.updateCustomer( user);
        return user;
    }

    @Override
    public Customer findById(Long id) {
        return customerDao.findCustomerById(id);
    }

    @Override
    public Customer findByLogin(Long id) {
        return customerDao.findCustomerByLogin(id);
    }

    @Override
    public List<Customer> findAll() {
        return customerDao.findAllCustomers();
    }

    @Override
    public void deleteById(Long id) {
        customerDao.deleteCustomerById(id);
    }

    @Override
    public void deleteByLogin(Long id) {
        customerDao.deleteCustomerByLogin(id);
    }



}
