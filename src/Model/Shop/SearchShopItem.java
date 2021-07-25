package Model.Shop;

import Model.DataAccessLayer.DalShopItem;

import java.time.LocalDate;
import java.util.Date;

import java.util.List;

/**
 * SearchShopItem is a class that store all the data relative to the view IndexShop.
 */
public class SearchShopItem {
    private List<ShopItem> listShopItem;

    private LocalDate startDate;
    private LocalDate endDate;

    // TODO : selection par adresse non pris en charge
    private String startAddress;
    private String endAddress;

    public SearchShopItem() {
        this.listShopItem =  DalShopItem.GetAllShopItem();

    }

    public SearchShopItem(LocalDate startDate, LocalDate endDate)
    {
        this.startDate = startDate;
        this.endDate = endDate;
        this.listShopItem =  DalShopItem.GetShopItems(startDate, endDate);
    }

    public SearchShopItem(LocalDate startDate, LocalDate endDate, String startAddress, String endAddress)
    {
        this.startDate = startDate;
        this.endDate = endDate;
        this.startAddress = startAddress;
        this.endAddress = endAddress;
        this.listShopItem =  DalShopItem.GetShopItems(startDate, endDate);


    }

    public List<ShopItem> GetListShopItem()
    {
        return this.listShopItem;
    }
}
