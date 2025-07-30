package services;

import model.User;

import java.util.List;

public interface UserServiceInterface<T extends User> {

    T register(T user);

    T update(T user);

    T findById(Long id);

    T findByLogin(Long id);

    List<T> findAll();

    void deleteById(Long id);

    void deleteByLogin(Long id);
}
