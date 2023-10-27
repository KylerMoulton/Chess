package dataAccess;

import model.UserModel;

import java.util.HashMap;

/**
 * Class that creates, updates, and deletes UserModels in the Database
 */
public class UserDAO {
    public static HashMap<String, UserModel> createdUsers = new HashMap<>();
    /**
     * Creates a user in the Database
     * @param username Takes in a UserModel
     */
    public void CreateUser(String username, String password, String email) {
        UserModel newUser = new UserModel(username,password,email);
        storeUser(newUser);
    }

    /**
     * Gets a user from the Database
     * @param username Takes in the User's username as a String
     * @return Returns the UserModel of the user
     */
    public String GetUser(String username) {
        if (createdUsers.get(username)!=null) {
            return createdUsers.get(username).getUsername();
        }
        return null;
    }

    public String GetPassword(String username) {
        if (createdUsers.get(username)!=null) {
            return createdUsers.get(username).getPassword();
        }
        return null;
    }

    /**
     * Deletes all users from the Database
     */
    public void clearUsers() {
        createdUsers.clear();
    }
    public void storeUser(UserModel userModel) {
        createdUsers.put(userModel.getUsername(),userModel);
    }
}
