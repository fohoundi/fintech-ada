    package services;

    import model.Customer;
    import model.User;

    import java.util.List;

    public interface UserServiceInterface {

        public User register(User user);

        Customer register(Customer customer);

        public User update(User user);

        public User findById(String id);

        public List<User> findAll();
    }
