package Model.DataAccessLayer;

import Model.DbConnect.DbInterface;
import Model.Shop.Car;
import Model.Shop.ShopItem;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DalShopItem {
    public static List<ShopItem> GetShopItem()
    {
        List<ShopItem> results = new ArrayList<ShopItem>();
        DbInterface dbconnect = new DbInterface();

        return results;
    }


    public static List<Car> GetCars() {
        String request = "Select * from cars";
        List<Car> results = new ArrayList<>();
        try {
            results = DbInterface.GetCar(request);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return results;
    }

}
