package Model.DataAccessLayer;

import Model.DbConnect.DbInterface;
import Model.Exception.ConnectException;
import Model.User.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DalUser {

    public static User GetUser(String email, String hashPasswd) throws SQLException {
        // Query email to db and get Custommer object
        String request = "SELECT * from User U where email = '" + email + "' AND password = '" + hashPasswd + "';";

        return DbInterface.GetUser(request);
    }

    public static User GetUserById(int id)
    {
        User user = new User();
        String query = "SELECT * from user where id = "+ id +"";
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

    public static User Login(String email, String password) throws ConnectException, SQLException {
        User user = null;
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



            user = GetUser(email, generatedPassword);


        if (user == null)
        {
            throw new ConnectException("The email or/and password are incorrect");
        }
        return user;
    }

    public static void AddUser(User user) throws SQLException {

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
