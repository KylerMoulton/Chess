package dataAccess;

import model.UserModel;

/**
 * Class that creates, updates, and deletes UserModels in the Database
 */
public class UserDAO {
    /**
     * Creates a user in the Database
     * @param user Takes in a UserModel
     * @throws DataAccessException Throws a DataAccessException
     */
    void CreateUser(UserModel user) throws DataAccessException{

    }

    /**
     * Gets a user from the Database
     * @param user Takes in the User's username as a String
     * @return Returns the UserModel of the user
     * @throws DataAccessException Throws DataAccessException
     */
    UserModel GetUser(String user) throws DataAccessException{
        return null;
    }

    /**
     * Updates the user's username
     * @param user Takes in the UserModel of the wanted user
     * @param username Takes in a String of the new username
     * @throws DataAccessException Throws DataAccessException
     */
    void UpdateUserUsername(UserModel user,String username) throws DataAccessException{

    }
    /**
     * Updates the user's password
     * @param user Takes in the UserModel of the wanted user
     * @param password Takes in a String of the new password
     * @throws DataAccessException Throws DataAccessException
     */
    void UpdateUserPassword(UserModel user,String password) throws DataAccessException{

    }
    /**
     * Updates the user's email
     * @param user Takes in the UserModel of the wanted user
     * @param email Takes in a String of the new email
     * @throws DataAccessException Throws DataAccessException
     */
    void UpdateUserEmail(UserModel user, String email) throws DataAccessException{

    }

    /**
     * Deletes a user from the Database
     * @param user Takes in the UserModel of the user
     * @throws DataAccessException Throws DataAccessException
     */
    void DeleteUser(UserModel user) throws DataAccessException{

    }

    /**
     * Deletes all users from the Database
     * @throws DataAccessException Throws DataAccessException
     */
    void clearUsers() throws DataAccessException{

    }
}
