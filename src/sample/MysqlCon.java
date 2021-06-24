package sample;
import java.sql.*;

public class MysqlCon{

    public static Connection dbConnect;

    public MysqlCon(){
        setDbConnect();
    }

    public void setDbConnect() {
        dbConnect = null;
        try {
            // db parameters - ptest is the name of the database
            String url       = "jdbc:mysql://localhost:3306/car_rent";
            String user      = "root";
            String password  = "";

            // create a connection to the database
            dbConnect = DriverManager.getConnection(url, user, password);
            // more processing here
            // ...


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
    }

    public static void main(String arg) {
    }

}