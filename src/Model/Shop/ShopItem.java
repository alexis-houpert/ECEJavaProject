package Model.Shop;

public class ShopItem {
    private Car car;
    private double rentPrice;

    public ShopItem(Car car, double rentPrice)
    {
        this.car = car;
        this.rentPrice = rentPrice;
    }

    public double GetRentPrice(){
        return this.rentPrice;
    }

    public Car GetCar(){
        return this.car;
    }



}
