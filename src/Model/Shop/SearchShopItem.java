package Model.Shop;

import Model.DataAccessLayer.DalShopItem;

import java.util.ArrayList;
import java.util.List;

public class SearchShopItem {
    private List<ShopItem> listShopItem;

    public SearchShopItem()
    {
       this.listShopItem =  DalShopItem.GetShopItem();
    }

    public List<ShopItem> GetListShopItem()
    {
        return this.listShopItem;
    }
}
