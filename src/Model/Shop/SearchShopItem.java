package Model.Shop;

import Model.DataAccessLayer.DalShopItem;

import java.util.Date;
import java.util.List;

public class SearchShopItem {
    private List<ShopItem> listShopItem;

    private Date startDate;
    private Date endDate;

    // TODO : selection par adresse non pris en charge
    private String startAddress;
    private String endAddress;

    public SearchShopItem() {
        this.listShopItem =  DalShopItem.GetAllShopItem();

    }

    public SearchShopItem(Date startDate, Date endDate)
    {
        this.startDate = startDate;
        this.endDate = endDate;
        this.listShopItem =  DalShopItem.GetShopItems(startDate, endDate);
    }

    public SearchShopItem(Date startDate, Date endDate, String startAddress, String endAddress)
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
