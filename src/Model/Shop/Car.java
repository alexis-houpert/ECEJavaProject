package Model.Shop;

public class Car {
    private String name;
    private String plateNumber;
    private Integer horsePower;
    private Integer nbSeats;
    private String options;

    public Car(String name, String plateNumber, int horsePower, int nbSeats) {
        this.name = name;
        this.plateNumber = plateNumber;
        this.horsePower = horsePower;
        this.nbSeats = nbSeats;
    }

    public Car(String name, String plateNumber, int horsePower, int nbSeats, String options) {
        this.name = name;
        this.plateNumber = plateNumber;
        this.horsePower = horsePower;
        this.nbSeats = nbSeats;
        this.options = options;
    }

    public Integer getNbSeats() {
        return nbSeats;
    }

    public String getName() {
        return name;
    }

    public Integer getHorsePower() {
        return horsePower;
    }

    public String getOptions() {
        return options;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHorsePower(Integer horsePower) {
        this.horsePower = horsePower;
    }

    public void setNbSeats(Integer nbSeats) {
        this.nbSeats = nbSeats;
    }

    public void setOptions(String options) {
        this.options = options;
    }
    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }
}
