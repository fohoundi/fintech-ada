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
        Customer customer = customerDao.findCustomerById(id);
        return customer;
    }



    public List<Customer> findAll() {
        CustomerDao customerDao = new CustomerDao();
        List<Customer> customers = customerDao.findAllCustomers();
        return customers;
    }

    public void delete(Long id){
        CustomerDao customerDao = new CustomerDao();
        customerDao.deleteCustomerById(id);
    }
}
