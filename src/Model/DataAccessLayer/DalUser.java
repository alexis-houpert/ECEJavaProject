package Model.DataAccessLayer;

import Model.DbConnect.DbInterface;
import Model.Exception.ConnectException;
import Model.User.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Alexis HOUPERT
 * DalUser is the layer close to database interface responsible for retrieving data related to User
 */
public class DalUser {

    public static User GetUser(String email, String hashPasswd) throws SQLException {
        // Query email to db and get Custommer object
        User user = new User();
        String query = "SELECT * from user U where email = '" + email + "' AND password = '" + hashPasswd + "';";

        return extractUser(user, query);
    }

    private static User extractUser(User user, String query) {
        try {
            ResultSet rs = DbInterface.GetData(query);
            while (rs.next())
            {
                user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(7), rs.getString(8), rs.getString(9));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            DbInterface.CloseConnection();
        }
        return user;
    }

    public static User GetUserById(int id)
    {
        User user = new User();
        String query = "SELECT * from user where id = "+ id +"";
        return extractUser(user, query);
    }

    public static User Login(String email, String password) throws ConnectException {
        User user = new User();
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


        try {
            user = GetUser(email, generatedPassword);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new ConnectException(throwables.getMessage());
        }


        if (user == null )
        {
            throw new ConnectException("The email or/and password are incorrect");
        }
        if(user.GetEmail() == null)
        {
            throw new ConnectException("No user found");
        }
        return user;
    }

    public static boolean IsAccountExist(String email)
    {
        String request = "SELECT email from user where email = '" +email+"';";
        try {
            ResultSet rs = DbInterface.GetData(request);
            int cpt = 0;
            while (rs.next())
            {
                cpt++;
            }
            return cpt != 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    public static void AddUser(User user) throws IllegalArgumentException, SQLException {

        if(IsAccountExist(user.GetEmail()))
        {
            throw new IllegalArgumentException("This user already existing");
        }

        if (user.GetFirstName() != null || !user.GetFirstName().isEmpty())
        {
            String request = "INSERT INTO user (email, password, roleCode, numCust, numEmploye, firstName, lastName, adress) " +
                    "VALUES ('" + user.GetEmail() + "', '" + user.GetHashPassword() + "','" + user.getRole()+ "'," +
                    "NULL, NULL," +
                    "'" + user.GetFirstName() + "'," +
                    "'" + user.GetLastName() + "'," +
                    "'" + user.GetAdress() + "' );";
            DbInterface.UpdateData(request);
        }
        else
        {
         throw new IllegalArgumentException("Invalid data");
        }

    }

}
