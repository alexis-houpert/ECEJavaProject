package Controller;

import Model.DataAccessLayer.DalShopItem;
import Model.DbConnect.DbInterface;
import Model.Shop.Car;
import Model.Shop.ShopItem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddShopItemController implements Initializable
{

    @FXML public TextField brand;
    @FXML public TextField name;
    @FXML public TextField horsePower;
    @FXML public TextField nbSeats;
    @FXML public TextField color;
    @FXML public TextField price;

    @FXML public Pane header;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initHeader();

    }

    public void initHeader()
    {
        try {
            AnchorPane headerAnchor = FXMLLoader.load(getClass().getResource("/View/Header.fxml"));
            header.getChildren().add(headerAnchor);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void addShopItem(ActionEvent event)
    {
        String carBrand = brand.getText();
        String carName = name.getText();
        int carHp = Integer.parseInt(horsePower.getText());
        int carNbSeats = Integer.parseInt(nbSeats.getText());
        String carColor = color.getText();
        int itemPrice = Integer.parseInt(price.getText());

        Car car = new Car(carBrand, carName, Car.getNewSerialNumber(), carHp, carNbSeats, carColor);

        DalShopItem.addShopItem(new ShopItem(ShopItem.getNewId(),car,itemPrice));

        
    }
   
    


}
