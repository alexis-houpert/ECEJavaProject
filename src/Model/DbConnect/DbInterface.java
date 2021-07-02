package Model.DbConnect;
import Model.Shop.Car;

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
            String url       = "jdbc:mysql://localhost:3306/rent_car";
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
                Car car = new Car(rs.getString(1), rs.getString(2), rs.getInt(3),
                        rs.getInt(4), rs.getString(5));
                results.add(car);
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