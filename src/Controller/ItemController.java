package Controller;

import Model.DataAccessLayer.DalShopItem;
import Model.Shop.Booking;
import Model.Shop.ShopItem;
import Model.User.UserSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class ItemController implements Initializable{

    @FXML private Pane header;
    @FXML private Label name;
    @FXML private Label brand;


    @FXML private Label startDateLabel;
    @FXML private Label endDateLabel;

    private ShopItem item;
    private Date startDate;
    private Date endDate;
    private String startAddress;
    private String endAddress;


    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    @FXML
    public void actionGetItemInfo(ActionEvent event)
    {
        Object node = event.getSource(); //returns the object that generated the event
        Button b = (Button)node;

        try {
            String start = ((TextField)b.getScene().lookup("#startDateText")).getText();
            String end = ((TextField)b.getScene().lookup("#endDateText")).getText();
            if (start == null | start.isEmpty() | end == null |end.isEmpty())
            {
                throw new IllegalArgumentException("Please enter your start date and your end date with the format yyyy-MM-dd");
            }

            DateFormat originalFormat = new SimpleDateFormat("dd/mm/yyyy");
            DateFormat targetFormat = new SimpleDateFormat("yyyy-mm-dd");
            Date date = originalFormat.parse(start);
            //startDate = targetFormat.par

            startAddress = ((TextField)b.getScene().lookup("#startAddressText")).getText();
            endAddress = ((TextField)b.getScene().lookup("#endAddressText")).getText();


            int id = Integer.parseInt(b.getId());


            try {

                FXMLLoader fxmlLoader =  new FXMLLoader(getClass().getResource("/View/ItemDisplay.fxml"));
                Parent tableViewParent = (Parent)fxmlLoader.load();
                Scene tableViewScene = new Scene(tableViewParent);
                Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
                window.setScene(tableViewScene);
                window.centerOnScreen();
                ItemController controller = fxmlLoader.<ItemController>getController();
                controller.GetShopItemById(id);
                window.show();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        catch (IllegalArgumentException e) {
            Label lab = (Label)b.getScene().lookup("#error");
            lab.setText(e.getMessage());
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
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

    public void InitView()
    {
        name.setText(item.GetCar().getName());
        brand.setText(item.GetCar().getBrand());
    }

    public void GetShopItemById(int id)
    {
        initHeader();
        this.item = DalShopItem.GetShopItemById(id);
        InitView();
    }

    @FXML
    public void bookCar(ActionEvent event)
    {
        Booking booking = new Booking(Booking.getNewId(),UserSession.getUser(),item,startDate, endDate, startAddress, endAddress);
        Button node = (Button) event.getSource();
        Label lab = (Label) node.getScene().lookup("#bookInfoMessage");
        try {
            DalShopItem.addBooking(booking);

                lab.setText("Booking saved : redirection in 5 sec");
                lab.setTextFill(Paint.valueOf("GREEN"));



        } catch (SQLException e) {
            e.printStackTrace();
            lab.setText("Error has occur with the insertion in the database");
        }

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        try {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("/View/IndexShop.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            window.setScene(tableViewScene);
            window.centerOnScreen();
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
