package Model.Shop;

import Model.DbConnect.DbInterface;
import Model.User.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

public class Booking {
    private int id;
    private User user;
    private ShopItem item;

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public ShopItem getItem() {
        return item;
    }

    public Car getCar() {
        return car;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getStartAdress() {
        return startAdress;
    }

    public String getEndAdress() {
        return endAdress;
    }

    private Car car;

    private LocalDate startDate;
    private LocalDate endDate;

    private String startAdress;
    private String endAdress;

    public Booking(int id, User user, ShopItem item, LocalDate startDate, LocalDate endDate, String startAdress, String endAdress) {
        this.id = id;
        this.user = user;
        this.item = item;
        this.car = item.GetCar();
        this.startDate = startDate;
        this.endDate = endDate;
        this.startAdress = startAdress;
        this.endAdress = endAdress;
    }

    public static int getNewId()
    {
        int result = 0;
        try {
            ResultSet rs = DbInterface.GetData("select id from booking ORDER by id DESC;");
            while (rs.next())
            {
                result = rs.getInt(1);
                if(result == 0)
                {
                    result++;
                }
                break;
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return result;
    }


}
