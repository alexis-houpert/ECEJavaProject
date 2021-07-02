package Model.Shop;

import Model.DataAccessLayer.DalShopItem;

import java.util.List;

public class SearchShopItem {
    private List<ShopItem> listShopItem;

    public SearchShopItem()
    {
        // à remplacer par shopItem
       List<Car> cars=  DalShopItem.GetCars(); //TODO : convertir avec SHopItem
       for (Car car : cars)
       {
           listShopItem.add(new ShopItem(car, 50));
       }

    }

    public List<ShopItem> GetListShopItem()
    {
        return this.listShopItem;
    }
}
