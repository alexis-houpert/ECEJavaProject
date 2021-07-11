package Model.DataAccessLayer;

import Model.DbConnect.DbInterface;
import Model.Exception.ConnectException;
import Model.User.Customer;
import Model.User.Employee;
import Model.User.User;
import Model.User.UserInterface;

import java.sql.SQLException;

public class DalUser {

    public static User GetUser(String email, String hashPasswd) throws SQLException {
        // Query email to db and get Custommer object
        String request = "SELECT * from User U where email = '" + email + "' AND password = '" + hashPasswd + "';";

        return DbInterface.GetUser(request);
    }

    public static void Login(String email, String mdp) throws ConnectException {
        String hashPasswd = mdp; //TODO : implémenter le cryptage
        User user = null;
        try {
            user = GetUser(email, hashPasswd);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        if (user == null)
        {
            throw new ConnectException("The email or/and password are incorrect");
        }
    }

    public void AddCustommer(String email, String hashPasswd)
    {
        // TODO : Implement AddCustommer method
    }
}
