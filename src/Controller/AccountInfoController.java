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
        Integer cptAudi = 0;
         Integer cptMercedes = 0;
         Integer cptPorsche = 0;
         Integer cptVolkswagen = 0;
         Integer cptOther = 0;

        List<Booking> allBooking = new ArrayList<>();
        try {
            allBooking = DalShopItem.getAllBooking();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        for (Booking book : allBooking)
        {
            if(book.getCar().getBrand().equals(Constantes.AUDI))
            {
                cptAudi++;
            }
            else if (book.getCar().getBrand().equals(Constantes.MERCEDES))
            {
                cptMercedes++;
            }
            else if (book.getCar().getBrand().equals(Constantes.PORSCHE))
            {
                cptPorsche++;
            }
            else if (book.getCar().getBrand().equals(Constantes.VOLKSWAGEN))
            {
                cptVolkswagen++;
            }
            else if (book.getCar().getBrand().equals(""))
            {
                cptOther++;
            }
        }


        PieChart.Data slice1 = new PieChart.Data(Constantes.PORSCHE, cptPorsche);
        PieChart.Data slice2 = new PieChart.Data(Constantes.MERCEDES, cptMercedes);
        PieChart.Data slice3 = new PieChart.Data(Constantes.AUDI, cptAudi);
        PieChart.Data slice4 = new PieChart.Data(Constantes.VOLKSWAGEN, cptVolkswagen);
        PieChart.Data slice5 = new PieChart.Data("Other", cptOther);

        pieChart.getData().add(slice1);
        pieChart.getData().add(slice2);
        pieChart.getData().add(slice3);
        pieChart.getData().add(slice4);
        pieChart.getData().add(slice5);

        pieChart.setLegendSide(Side.LEFT);
    }



}
