package ci.ada.data;


import ci.ada.model.*;
import ci.ada.model.enumaration.compteType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public static void deleteInfo(BasicInfo user){
        infoList.remove(user);
    }

    public static void addUser(User user) {
        userList.add(user);
    }

    // --- Customer ---
    public static List<Customer> getCustomerList() {
        return infoList.stream()
                .filter(user -> user.getUserAccount().getCompteType().compareTo(compteType.CUSTOMER) == 0)
                .map(user -> (Customer) user)
                .collect(Collectors.toList());
    }

    public static void addCustomer(Customer customer) {
        customerList.add(customer);
    }

    // --- Marchant ---
    public static List<Marchant> getMarchantList() {

        return infoList.stream()
                .filter(user -> user.getUserAccount().getCompteType().compareTo(compteType.MERCHANT) == 0)
                .map(user -> (Marchant) user)
                .collect(Collectors.toList());
    }

    public static void addMarchant(Marchant marchant) {
        marchantList.add(marchant);
    }

    // --- Admin ---
    public static List<Admin> getAdminList() {

        return infoList.stream()
                .filter(user -> user.getUserAccount().getCompteType().compareTo(compteType.ADMIN) == 0)
                .map(user -> (Admin) user)
                .collect(Collectors.toList());
    }

    public static void addAdmin(Admin admin) {
        adminList.add(admin);
    }
}
