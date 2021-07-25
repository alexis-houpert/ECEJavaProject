package Model.Shop;

import Model.DbConnect.DbInterface;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Alexis HOUPERT
 * THe class store data for 1 item in the shop
 */
public class ShopItem {

    private Integer id;
    private Car car;
    private double rentPrice;

    public ShopItem(int id, Car car, double rentPrice)
    {
        this.id = id;
        this.car = car;
        this.rentPrice = rentPrice;
    }

    public ShopItem() {}

    public static int getNewId()
    {
        int result = 0;
        try {
            ResultSet rs = DbInterface.GetData("select id from shopitem ORDER by id DESC;");
            while (rs.next())
            {
                result = rs.getInt(1);
                if(result == 0)
                {
                    result++;
                }
                else{
                    result++;
                }

                break;
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return result ;
    }


    public double GetRentPrice(){
        return this.rentPrice;
    }

    public Car GetCar(){
        return this.car;
    }

    public Integer GetId()
    {
        return this.id;
    }

}
