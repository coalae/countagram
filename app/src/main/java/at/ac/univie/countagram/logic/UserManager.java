package at.ac.univie.countagram.logic;

import android.app.Activity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import at.ac.univie.countagram.model.User;
import at.ac.univie.countagram.repository.UserRepository;

/**
 * UserManager class manages the interaction with the repository, i.e. it extracts data from the
 * repository and also updates data in the repository.
 */

public class UserManager {

    /**
     * Instance variable userRepository
     */
    private UserRepository userRepository;

    /**
     * Constructor
     */
    public UserManager(){

        userRepository = new UserRepository();
    }

    /**
     * Adds a given User to the userRepository
     * @param user
     * @return
     */
    public boolean addUser(User user){
        return userRepository.addUser(user);
    }

    /**
     * Does the login for the parameter username and pw (password)
     * @param username
     * @param pw
     * @return
     */
    public boolean loginUser(String username, String pw){
        User user = userRepository.getUserByUsername(username);
        if (user != null) {
            if (user.getUsername().equals(username) && user.getPassword().equals(pw))
                return true;
            else
                return false;
        }
        else
            return false;
    }

    /**
     * Gets a User by the given username parameter
     * @param username
     * @return
     */
    public User getUserByUsername(String username){
        return userRepository.getUserByUsername(username);
    }

    /**
     * TODO: implementation of method)
     * Does the logout for the User u given as parameter
     * @param u
     * @return
     */
    public boolean logoutUser(User u) {

        return true;
    }

    /**
     * Updates a User in the repository
     * @param u
     * @return
     */
    public boolean updateUser(User u) {
        return userRepository.updateUser(u);
    }

    // TODO: check implementation of the method (is currently only for testing purposes using log)
    public void getAllUser(){
        UserRepository userRepository = new UserRepository();
        List<User> userList = userRepository.findAll();
        for(User user : userList)
        {
            Log.e("Background", "\n" + user.getEmail() +"\n" + user.getFirstname() + "\n" + user.getLastname());
        }
    }

    /**
     * Gets User by user_id given as a parameter from the repository
     * @param user_id
     * @return
     */
    public User getUserById(int user_id){
        return userRepository.getUserById(user_id);
    }
}
