package Controller;

import Application.Constantes;
import Model.DataAccessLayer.DalShopItem;
import Model.DataAccessLayer.DalUser;
import Model.Shop.Booking;
import Model.Shop.ShopItem;
import Model.User.UserSession;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AccountInfoController implements Initializable {

    @FXML
    private Pane header;

    @FXML private VBox conteneur;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1)
    {

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

    public void initData()
    {
        conteneur.getChildren().clear();
        List<Booking> bookings = new ArrayList<>();
        try {
            bookings = DalShopItem.getBookingById(UserSession.getUser().GetId());
            for (Booking booking : bookings )
            {
                AnchorPane item = null;
                try {
                    item = FXMLLoader.load(getClass().getResource("/View/ItemBook.fxml"));


                    ((Label) item.lookup(Constantes.VUE_BOOK_ID)).setText(String.valueOf((Integer) booking.getId()));
                    ((Label) item.lookup(Constantes.VUE_BOOK_NAME)).setText(booking.getItem().GetCar().getBrand() + " " +
                            booking.getCar().getName());
                    ((Label) item.lookup(Constantes.VUE_BOOK_DATE_START)).setText(new SimpleDateFormat("yyyy-MM-dd")
                            .format(booking.getStartDate()));
                    ((Label) item.lookup(Constantes.VUE_BOOK_DATE_END)).setText(new SimpleDateFormat("yyyy-MM-dd")
                            .format(booking.getEndDate()));
                    if(booking.getStartAdress() != null)
                    {
                        ((Label) item.lookup(Constantes.VUE_BOOK_ADDRESS_START)).setText(booking.getStartAdress());

                    }
                    if (booking.getEndAdress() != null)
                    {
                        ((Label) item.lookup(Constantes.VUE_BOOK_ADDRESS_END)).setText(booking.getEndAdress());

                    }
                    conteneur.getChildren().add(item);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                catch (IllegalArgumentException e)
                {
                    e.printStackTrace();
                }
            }
    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }
    }

}
