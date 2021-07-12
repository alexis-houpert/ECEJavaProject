package Model.DbConnect;
import Application.Constantes;
import Model.Shop.Car;
import Model.User.User;
import Model.User.UserInterface;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbInterface {

    /**
     * This method establish a connection to the database, execute the query and return result
     * @param request
     * @return results of the request. Data from row are mixed into a String
     * @throws SQLException
     */
    public static List<Car> GetCar(String request) throws SQLException {
        List<Car> results = new ArrayList<>();
        Connection dbConnect = null;
        try {
            // create a connection to the database
            dbConnect = DriverManager.getConnection(Constantes.URL, Constantes.USER, Constantes.PASSWORD);

            Statement stmt= dbConnect.createStatement();
            ResultSet rs = stmt.executeQuery(request);

            if (false && rs != null) { //TODO : code à remplacer lorsque la bd sera terminé
                while (rs.next()) {
                    Car car = new Car(rs.getString(1), rs.getString(2), rs.getInt(3),
                            rs.getInt(4), rs.getString(5));
                    results.add(car);
                }
            }
            results.add(new Car("AUDI A4","dqsdsq", 302, 5));
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

    public static User GetUser(String request) throws SQLException {
        Connection dbConnect = null;

            // create a connection to the database
            dbConnect = DriverManager.getConnection(Constantes.URL, Constantes.USER, Constantes.PASSWORD);
            Statement stmt = dbConnect.createStatement();
            ResultSet rs = stmt.executeQuery(request);
            while (rs.next())
            {
                return new User(rs.getString(1), rs.getString(2));

            }
            return null;

    }

    public static void InsertUser(String request)  throws SQLException {
        Connection dbConnect = null;

        // create a connection to the database
        dbConnect = DriverManager.getConnection(Constantes.URL, Constantes.USER, Constantes.PASSWORD);
        Statement stmt = dbConnect.createStatement();
        stmt.executeUpdate(request);
    }



    public static void main(String[] arg) {

        try {
            List<Car> rs = DbInterface.GetCar("select * from car");
            for(Car car : rs)
            {
                System.out.println(car.getName() + car.getPlateNumber() + car.getNbSeats());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}