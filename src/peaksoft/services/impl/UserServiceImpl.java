package peaksoft.services.impl;

import peaksoft.dao.impl.UserDaoImpl;
import peaksoft.exceptions.InvalidEmailException;
import peaksoft.exceptions.NotFoundException;
import peaksoft.models.User;
import peaksoft.services.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDaoImpl userDao;

    public UserServiceImpl(UserDaoImpl userDao) {
        this.userDao = userDao;
    }

    @Override
    public String save(User newUser) {
        try {
            if (!newUser.getEmail().contains("@")) throw new InvalidEmailException("Invalid email");
            if (newUser.getPassword().length() < 4) throw new IllegalArgumentException("Invalid password");
        } catch (IllegalArgumentException | InvalidEmailException e) {
            return "User not saved [ " + e.getMessage() + "]";
        }
        userDao.save(newUser);
        return String.format("User with email: %s successfully saved!", newUser.getEmail());
    }

    @Override
    public User findById(Long id) {
        User foundUser;
        try {
            foundUser = userDao.findById(id);
        } catch (NotFoundException e) {
            System.err.println(e.getMessage());
            return null;
        }
        return foundUser;
    }

    @Override
    public User signIn(String email, String password) {
        List<User> allUsers = userDao.findAll();
        for (User user : allUsers) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)){
                return user;
            }
        }
        throw new IllegalArgumentException("Invalid email or password");
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }


}
