package Model.Shop;

import Model.DbConnect.DbInterface;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Alexis HOUPERT
 * Car is used to store data about car in client side
 */
public class Car {
    private String name;
    private String brand;
    private int serialNumber;
    private Integer horsePower;
    private Integer nbSeats;
    private String color;

    public Car(String brand, String name, int serialNumber, int horsePower, int nbSeats) {
        this.brand = brand;
        this.name = name;
        this.serialNumber = serialNumber;
        this.horsePower = horsePower;
        this.nbSeats = nbSeats;
    }

    public Car(String brand, String name, int serialNumber, int horsePower, int nbSeats, String color) {
        this.brand = brand;
        this.name = name;
        this.serialNumber = serialNumber;
        this.horsePower = horsePower;
        this.nbSeats = nbSeats;
        this.color = color;
    }

    public static int getNewSerialNumber()
    {
        int result = 0;
        try {
            ResultSet rs = DbInterface.GetData("select serialNumber from car ORDER by serialNumber DESC;");
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
        return result;
    }

    public Integer getNbSeats() {
        return nbSeats;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public Integer getHorsePower() {
        return horsePower;
    }

    public String getColor() {
        return color;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setHorsePower(Integer horsePower) {
        this.horsePower = horsePower;
    }

    public void setNbSeats(Integer nbSeats) {
        this.nbSeats = nbSeats;
    }

    public void setColor(String color) {
        this.color = color;
    }
    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }
}
