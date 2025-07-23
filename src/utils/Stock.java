package utils;

import model.*;

import java.util.ArrayList;
import java.util.List;

public class Stock {

    private  static final List<User> userList = new ArrayList<User>();

    private static final List<Customer> customerList= new ArrayList<>();

    private static final List<Marchant> marchantList = new ArrayList<>();

    private static final List<Admin> adminList = new ArrayList<>();

    private static final List<BasicInfo> infoList = new ArrayList<>();

    // --- User ---
    public static List<User> getUserList() {
        return userList;
    }

    public static void addInfo(BasicInfo user) {
        infoList.add(user);
    }

    // --- Info ---
    public static List<BasicInfo> getInfoList() {
        return infoList;
    }

    public static void addUser(User user) {
        userList.add(user);
    }

    // --- Customer ---
    public static List<Customer> getCustomerList() {
        return customerList;
    }

    public static void addCustomer(Customer customer) {
        customerList.add(customer);
    }

    // --- Marchant ---
    public static List<Marchant> getMarchantList() {
        return marchantList;
    }

    public static void addMarchant(Marchant marchant) {
        marchantList.add(marchant);
    }

    // --- Admin ---
    public static List<Admin> getAdminList() {
        return adminList;
    }

    public static void addAdmin(Admin admin) {
        adminList.add(admin);
    }
}
