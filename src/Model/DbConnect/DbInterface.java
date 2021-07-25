package Model.DbConnect;
import Application.Constantes;
import Model.DataAccessLayer.DalShopItem;
import Model.Shop.Car;
import Model.Shop.ShopItem;
import Model.User.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexis
 * Interface between Data Access Layer and Database. Retrieve and update data
 */
public class DbInterface {

    static Connection dbConnect;


    /**
     * Select all shop item
     * @return
     * @throws SQLException
     */
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

    /**
     * Close the connection to the server
     */
    public static void CloseConnection()
    {
        try{
            if(dbConnect != null)
                dbConnect.close();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Generic methode to retrieve data from the database
     * @param query
     * @return
     * @throws SQLException
     */
    public static ResultSet GetData(String query) throws SQLException
    {
        try {
            // create a connection to the database
            //Class.forName("com.mysql.cj.jdbc.Driver");
            dbConnect = DriverManager.getConnection(Constantes.URL, Constantes.USER, Constantes.PASSWORD);

            Statement stmt= dbConnect.createStatement();

            ResultSet rs = stmt.executeQuery(query);

            return rs;

        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Generic method to update data on database.
     * @param query
     * @throws SQLException
     */
    public static void UpdateData(String query) throws SQLException
    {
        dbConnect = DriverManager.getConnection(Constantes.URL, Constantes.USER, Constantes.PASSWORD);
        Statement stmt= dbConnect.createStatement();
        stmt.executeUpdate(query);
    }


    /**
     * Test method for dataBase
     * @param arg
     */
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