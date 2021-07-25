package Model.DataAccessLayer;

import Model.DbConnect.DbInterface;
import Model.Shop.Booking;
import Model.Shop.Car;
import Model.Shop.ShopItem;
import Model.User.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DalShopItem {
    public static List<ShopItem> GetAllShopItem()
    {
        List<ShopItem> results = new ArrayList<ShopItem>();
        try {
            results = DbInterface.GetShopItems();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return results;
    }

    public static List<ShopItem> GetShopItems(LocalDate startDate, LocalDate endDate)
    {
        List<ShopItem> results = new ArrayList<ShopItem>();
        String startDateFormat = startDate.toString();
        String endDateFormat = endDate.toString();
        try
        {
            String query = "Select * from car join shopitem on car.serialNumber = shopitem.serialNumber " +
                    "where shopitem.id Not in (" +
                    "SELECT b.id from shopitem s " +
                    "join booking b on s.id = b.shopItemId " +
                    "WHERE DATE('"+startDateFormat+"') >= b.dateStartBook and DATE('"+startDateFormat+"') <= b.dateEndBook "+
            " OR DATE('"+endDateFormat+"') >= b.dateStartBook and DATE('\"+endDateFormat+\"') <= b.dateEndBook" +
            " OR b.dateStartBook >= DATE('"+startDateFormat+"') and b.dateStartBook <= DATE('"+endDateFormat+"'));";

            ResultSet rs = DbInterface.GetData(query);
            extractShopItems(results, rs);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        finally {
            DbInterface.CloseConnection();
        }
        return results;
    }

    public static void extractShopItems(List<ShopItem> results, ResultSet rs) throws SQLException {
        while (rs.next())
        {
            Car car = new Car(rs.getString(2), rs.getString(3), rs.getInt(1),
                    rs.getInt(4), rs.getInt(5), rs.getString(6));

            results.add(new ShopItem(rs.getInt(7), car, rs.getInt(9)));
        }
    }

    public static ShopItem GetShopItemById(int id)
    {
        ShopItem shopItem = new ShopItem();
        String query = "Select * from shopitem join car on shopitem.serialNumber = car.serialNumber where shopitem.id = "+ id + ";";
        try {
            ResultSet rs = DbInterface.GetData(query);
            while (rs.next())
            {
                Car car = new Car(rs.getString(6), rs.getString(7), rs.getInt(5),
                        rs.getInt(8), rs.getInt(9), rs.getString(10));
                shopItem = new ShopItem(rs.getInt(1), car, rs.getInt(3));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            DbInterface.CloseConnection();
        }
        return shopItem;
    }

    public static void addShopItem(ShopItem item)
    {
        String query = "INSERT INTO car (serialNumber, brand, name, horsePower, nbSeat, color) " +
                "VALUES (" +
                item.GetCar().getSerialNumber() + ", '" + item.GetCar().getBrand() + "', '" + item.GetCar().getName() +
                "', "+ item.GetCar().getHorsePower()+ ", " + item.GetCar().getNbSeats() + ", '" + item.GetCar().getColor() +
                "');";

        String queryItem = "INSERT INTO shopItem (id, serialNumber, rentPrice) " +
                "VALUES ("+ item.GetId() + ", " + item.GetCar().getSerialNumber() + ", " + item.GetRentPrice() + ");";
        try {
            DbInterface.UpdateData(query);
            DbInterface.UpdateData(queryItem);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public static void addBooking(Booking booking) throws SQLException {

            String query = "INSERT INTO booking (id, userId, shopItemId, dateStartBook, " +
                    "dateEndBook, adressStartBook, adressEndBook) " +
                    "VALUES (NULL," +
                    "'"+booking.getUser().GetId()+"'," +
                    "'"+booking.getItem().GetId()+"'," +
                    " '"+ new SimpleDateFormat("yyyy-MM-dd").format(booking.getStartDate()) +"'," +
                    " '"+ new SimpleDateFormat("yyyy-MM-dd").format(booking.getEndDate()) +"' ," +
                    " '"+ booking.getStartAdress() +"'," +
                    " '"+ booking.getEndAdress() +"');";
            DbInterface.UpdateData(query);

    }

    public static List<Booking> getBookingById(int userId) throws SQLException
    {
        String query = "Select * from booking where userId = "+ userId +";";
        return extractBookings(userId, query);
    }

    public static List<Booking> getAllBooking() throws SQLException, ParseException {
        List<Booking> results = new ArrayList<>();
        String query = "Select * from booking;";
        ResultSet rs = DbInterface.GetData(query);
        while (rs.next())
        {
            User user = DalUser.GetUserById(rs.getInt(2));
            ShopItem item = DalShopItem.GetShopItemById(rs.getInt(3));
            Date start = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString(4));
            Date end = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString(5));

            LocalDate lStart = start.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate lEnd = end.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            results.add(new Booking(rs.getInt(1),user , item, lStart, lEnd, rs.getString(6), rs.getString(7)));
        }

        return results;
    }

    private static List<Booking> extractBookings(int userId, String query) {
        List<Booking> bookings = new ArrayList<>();
        try {
            ResultSet rs = DbInterface.GetData(query);
            while (rs.next())
            {
                User user = DalUser.GetUserById(userId);
                ShopItem item = DalShopItem.GetShopItemById(rs.getInt(3));
                Date start = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString(4));
                Date end = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString(5));

                LocalDate lStart = start.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate lEnd = end.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                bookings.add(new Booking(rs.getInt(1),user , item, lStart, lEnd, rs.getString(6), rs.getString(7)));
            }
        } catch (SQLException | ParseException throwables) {
            throwables.printStackTrace();
        }
        return bookings;
    }

}
