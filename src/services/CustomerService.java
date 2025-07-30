package services;

import dao.CustomerDao;
import model.Customer;
import model.User;

import java.util.List;

public class CustomerService {

    public Customer register(Customer customer) {
        CustomerDao customerDao = new CustomerDao();
        customer = customerDao.createCustomer(customer);
        return customer;
    }


    public Customer update(Customer customer) {
        CustomerDao customerDao = new CustomerDao();
        customer = customerDao.updateCustomer(customer);
        return customer;
    }


    public Customer findById(Long id) {
        CustomerDao customerDao = new CustomerDao();
        return customerDao.findCustomerById(id);
    }

    public Customer findByLogin(Long id) {
        CustomerDao customerDao = new CustomerDao();
        return customerDao.findCustomerByLogin(id);
    }


    public List<Customer> findAll() {
        CustomerDao customerDao = new CustomerDao();
        return customerDao.findAllCustomers();
    }

    public void delete(Long id){
        CustomerDao customerDao = new CustomerDao();
        customerDao.deleteCustomerById(id);
    }
    public void deleteByLogin(Long id){
        CustomerDao customerDao = new CustomerDao();
        customerDao.deleteCustomerByLogin(id);
    }
}
