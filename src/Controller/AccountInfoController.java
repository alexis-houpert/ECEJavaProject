package Controller;

import Application.Constantes;
import Model.DataAccessLayer.DalShopItem;
import Model.DataAccessLayer.DalUser;
import Model.DataPieChart;
import Model.Shop.Booking;
import Model.Shop.ShopItem;
import Model.User.UserSession;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Alexis HOUPERT
 * @author Louis DUTTIER
 * This controller is used to handle action and data displaying in the account section where we can find the history of booking and more
 */
public class AccountInfoController implements Initializable {

    @FXML
    private Pane header;

    @FXML private VBox conteneur;
    @FXML private Label email;
    @FXML private Label firstName;
    @FXML private Label lastName;
    @FXML private Label address;

    @FXML private PieChart pieChart;


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
        email.setText(UserSession.getUser().GetEmail());
        firstName.setText(UserSession.getUser().GetFirstName());
        lastName.setText(UserSession.getUser().GetLastName());
        address.setText(UserSession.getUser().GetAdress());



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
                    ((Label) item.lookup(Constantes.VUE_BOOK_DATE_START)).setText(booking.getStartDate().toString());
                    ((Label) item.lookup(Constantes.VUE_BOOK_DATE_END)).setText(booking.getEndDate().toString());
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
      caclculPieChart();
    }

    public void caclculPieChart()
    {
        PieChart.Data slice1 = new PieChart.Data(Constantes.PORSCHE, DataPieChart.getCptPorsche());
        PieChart.Data slice2 = new PieChart.Data(Constantes.MERCEDES, DataPieChart.getCptMercedes());
        PieChart.Data slice3 = new PieChart.Data(Constantes.AUDI, DataPieChart.getCptAudi());
        PieChart.Data slice4 = new PieChart.Data(Constantes.VOLKSWAGEN, DataPieChart.getCptVolkswagen());
        PieChart.Data slice5 = new PieChart.Data("Other", DataPieChart.getCptOther());



        pieChart.getData().add(slice1);
        pieChart.getData().add(slice2);
        pieChart.getData().add(slice3);
        pieChart.getData().add(slice4);
        pieChart.getData().add(slice5);

        pieChart.setLegendSide(Side.LEFT);
    }



}
