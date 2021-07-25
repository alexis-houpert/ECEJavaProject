package Model.DbConnect;
import Application.Constantes;
import Model.DataAccessLayer.DalShopItem;
import Model.Shop.Car;
import Model.Shop.ShopItem;
import Model.User.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbInterface {

    static Connection dbConnect;

    /**
     * This method establish a connection to the database, execute the query and return result
     * @param request
     * @return results of the request. Data from row are mixed into a String
     * @throws SQLException
     */
    public static List<Car> GetCars(String request) throws SQLException {
        List<Car> results = new ArrayList<>();
        Connection dbConnect = null;
        try {
            // create a connection to the database
            dbConnect = DriverManager.getConnection(Constantes.URL, Constantes.USER, Constantes.PASSWORD);

            Statement stmt= dbConnect.createStatement();
            ResultSet rs = stmt.executeQuery(request);


                while (rs.next()) {
                    Car car = new Car(rs.getString(2), rs.getString(3), rs.getInt(1),
                            rs.getInt(4), rs.getInt(5), rs.getString(6));
                    results.add(car);
                }


            return results;

        } catch(SQLException e) {
            e.printStackTrace();
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

    public static List<ShopItem> GetShopItems() throws SQLException
    {
        List<ShopItem> results = new ArrayList<>();
        Connection dbConnect = null;
        try {
            // create a connection to the database
            dbConnect = DriverManager.getConnection(Constantes.URL, Constantes.USER, Constantes.PASSWORD);

            Statement stmt= dbConnect.createStatement();

            ResultSet rs = stmt.executeQuery("Select * from car, shopitem where car.serialNumber = shopitem.serialNumber ");
            DalShopItem.extractShopItems(results, rs);
            return results;

        } catch(SQLException e) {
            e.printStackTrace();
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

    public static void CloseConnection()
    {
        try{
            if(dbConnect != null)
                dbConnect.close();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    public static ResultSet GetData(String query) throws SQLException
    {
        try {
            // create a connection to the database
            dbConnect = DriverManager.getConnection(Constantes.URL, Constantes.USER, Constantes.PASSWORD);

            Statement stmt= dbConnect.createStatement();

            ResultSet rs = stmt.executeQuery(query);

            return rs;

        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void UpdateData(String query) throws SQLException
    {
        dbConnect = DriverManager.getConnection(Constantes.URL, Constantes.USER, Constantes.PASSWORD);
        Statement stmt= dbConnect.createStatement();
        stmt.executeUpdate(query);
    }

    public static User GetUser(String request) throws SQLException {
        Connection dbConnect = null;
            User user = new User();
            // create a connection to the database
            dbConnect = DriverManager.getConnection(Constantes.URL, Constantes.USER, Constantes.PASSWORD);
            Statement stmt = dbConnect.createStatement();
            ResultSet rs = stmt.executeQuery(request);
            while (rs.next())
            {
                user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(7), rs.getString(8), rs.getString(9));
                break;
            }
            return user;
    }



    public static void InsertUser(String request)  throws SQLException {
        Connection dbConnect = null;

        // create a connection to the database
        dbConnect = DriverManager.getConnection(Constantes.URL, Constantes.USER, Constantes.PASSWORD);
        Statement stmt = dbConnect.createStatement();
        stmt.executeUpdate(request);
    }

    public static int GetNbCustomer() throws SQLException {
        Connection dbConnect = null;

        // create a connection to the database
        dbConnect = DriverManager.getConnection(Constantes.URL, Constantes.USER, Constantes.PASSWORD);
        Statement stmt = dbConnect.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * from user WHERE numCust != NULL");
        int cpt = 0;
        while (rs.next())
        {
           cpt++;
        }
        return cpt;
    }


    public static void main(String[] arg) {

        try {
            ResultSet rs = DbInterface.GetData("select * from car");
            while (rs.next())
            {
                System.out.println(rs.getString(2));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}