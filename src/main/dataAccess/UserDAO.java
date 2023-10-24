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
     * @throws DataAccessException Throws a DataAccessException
     */
    public void CreateUser(String username, String password, String email) throws DataAccessException{
        UserModel newUser = new UserModel(username,password,email);
        storeUser(newUser);
    }

    /**
     * Gets a user from the Database
     * @param username Takes in the User's username as a String
     * @return Returns the UserModel of the user
     * @throws DataAccessException Throws DataAccessException
     */
    public String GetUser(String username) throws DataAccessException{
        if (createdUsers.get(username)!=null) {
            return createdUsers.get(username).getUsername();
        }
        return null;
    }

    /**
     * Updates the user's username
     * @param user Takes in the UserModel of the wanted user
     * @param username Takes in a String of the new username
     * @throws DataAccessException Throws DataAccessException
     */
    public void UpdateUserUsername(UserModel user,String username) throws DataAccessException{

    }
    /**
     * Updates the user's password
     * @param user Takes in the UserModel of the wanted user
     * @param password Takes in a String of the new password
     * @throws DataAccessException Throws DataAccessException
     */
    public void UpdateUserPassword(UserModel user,String password) throws DataAccessException{

    }
    /**
     * Updates the user's email
     * @param user Takes in the UserModel of the wanted user
     * @param email Takes in a String of the new email
     * @throws DataAccessException Throws DataAccessException
     */
    public void UpdateUserEmail(UserModel user, String email) throws DataAccessException{

    }

    /**
     * Deletes a user from the Database
     * @param user Takes in the UserModel of the user
     * @throws DataAccessException Throws DataAccessException
     */
    public void DeleteUser(UserModel user) throws DataAccessException{

    }

    /**
     * Deletes all users from the Database
     * @throws DataAccessException Throws DataAccessException
     */
    public void clearUsers() throws DataAccessException{
        createdUsers.clear();
    }
    public void storeUser(UserModel userModel) throws DataAccessException{
        createdUsers.put(userModel.getUsername(),userModel);
    }
}
