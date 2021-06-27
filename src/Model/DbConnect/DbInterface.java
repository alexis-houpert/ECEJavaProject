package Model.DbConnect;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DbInterface {

    /**
     * This method establish a connection to the database, execute the query and return result
     * @param request
     * @return results of the request. Data from row are mixed into a String
     * @throws SQLException
     */
    public static List<String> connectDbRequest(String request) throws SQLException {
        List<String> results = new ArrayList<String>();
        Connection dbConnect = null;
        try {
            // db parameters - ptest is the name of the database
            String url       = "jdbc:mysql://localhost:3306/car_rent";
            String user      = "root";
            String password  = "";

            // create a connection to the database
            dbConnect = DriverManager.getConnection(url, user, password);
            // more processing here
            // ...
            Statement stmt= dbConnect.createStatement();
            ResultSet rs = stmt.executeQuery(request);

            while(rs.next())
            {
                results.add(rs.getInt(1)+" ; "+rs.getString(2));
            }

            return results;

        } catch(SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try{
                if(dbConnect != null)
                    dbConnect.close();
            }catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
        }
        return results;
    }

    public static void main(String[] arg) {
        DbInterface dbUti = new DbInterface();
        try {
            List<String> rs = dbUti.connectDbRequest("select * from customer");
            for(String str : rs)
            {
                System.out.println(str);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}