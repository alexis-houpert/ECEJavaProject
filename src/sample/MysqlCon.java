package sample;
import java.sql.*;

class MysqlCon{

    public static Connection dbConnect;

    public static void main(String[] args) {
        Connection conn = null;
        try {
            // db parameters - ptest is the name of the database
            String url       = "jdbc:mysql://localhost:3306/car_rent";
            String user      = "root";
            String password  = "";

            // create a connection to the database
            conn = DriverManager.getConnection(url, user, password);
            // more processing here
            // ...
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery("select * from customer");
            while(rs.next())
            {
                System.out.println(rs.getInt(1)+"  "+rs.getString(2));
            }

        } catch(SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try{
                if(conn != null)
                    conn.close();
            }catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
        }
    }

    public static void main(String arg) {
    }

}