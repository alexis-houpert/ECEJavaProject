package Model.DataAccessLayer;

import Model.DbConnect.DbInterface;
import Model.Exception.ConnectException;
import Model.User.Customer;
import Model.User.Employee;
import Model.User.User;
import Model.User.UserInterface;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class DalUser {

    public static User GetUser(String email, String hashPasswd) throws SQLException {
        // Query email to db and get Custommer object
        String request = "SELECT * from User U where email = '" + email + "' AND password = '" + hashPasswd + "';";

        return DbInterface.GetUser(request);
    }

    public static void Login(String email, String password) throws ConnectException {

        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(password.getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }

        User user = null;
        try {
            user = GetUser(email, generatedPassword);
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

    public static void AddUser(User user) throws SQLException {
        String role = "";
        if (user.GetFirstName() == null || user.GetFirstName().isEmpty())
        {
            String request = "INSERT INTO user (email, password, roleCode, numCust, numEmploye, firstName, lastName, adress) " +
                    "VALUES ('" + user.GetEmail() + "', '" + user.GetHashPassword() + "','" + role + "'," +
                    "'" + (user.getClass().getSimpleName() == "Customer" ? ((Customer) user).GetNum():((Employee) user).GetNum() ) + "'," +
                    "'" + (user.getClass().getSimpleName() == "Customer" ? "NCU" : "EMP") + "'," +
                    "'" + user.GetFirstName() + "'," +
                    "'" + user.GetLastName() + "'," +
                    "'" + user.GetAdress() + "' );";
            DbInterface.InsertUser(request);
        }
    }

    public static void AddCustomer(Customer cust) throws SQLException {
        String role = "NCU";
        if (cust.GetFirstName() != null || !cust.GetFirstName().isEmpty())
        {
            String request = "INSERT INTO user (email, password, roleCode, numCust, firstName, lastName, adress) " +
                    "VALUES ('" + cust.GetEmail() + "', '" + cust.GetHashPassword() + "','" + role + "'," +
                    "'" + cust.GetNum() + "'," +
                    "'" + cust.GetFirstName() + "'," +
                    "'" + cust.GetLastName() + "'," +
                    "'" + cust.GetAdress() + "' );";
            DbInterface.InsertUser(request);
        }
    }
}
