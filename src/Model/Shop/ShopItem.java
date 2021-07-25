package Model.Shop;

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
