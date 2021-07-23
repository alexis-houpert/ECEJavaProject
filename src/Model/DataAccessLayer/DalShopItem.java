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
        try {
            results = DbInterface.GetShopItems();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return results;
    }


    public static List<Car> GetAllCars() {
        String request = "Select * from car";
        List<Car> results = new ArrayList<>();
        try {
            results = DbInterface.GetCars(request);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return results;
    }

}
