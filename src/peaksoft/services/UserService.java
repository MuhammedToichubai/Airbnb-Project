package peaksoft.services;

import peaksoft.models.User;

import java.util.List;

public interface UserService {
    String save(User newUser);

    User findById(Long id);

    User signIn(String email, String password);

    List<User> findAll();

}
