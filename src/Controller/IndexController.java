package Controller;

import Application.Constantes;
import Model.Shop.SearchShopItem;
import Model.Shop.ShopItem;
import Model.ThreadUpdateDataPieChart;
import Model.User.UserSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 * @author Alexis HOUPERT, Louis DUTTIER
 * This controller is used to handle all actions from shop view
 */
public class IndexController implements Initializable {

    @FXML private Pane header;

    @FXML private VBox conteneur;
    @FXML public Button addButton;

    @FXML private DatePicker startDateText;
    @FXML private DatePicker endDateText;
    @FXML private TextField startAddressText;
    @FXML private TextField endAddressText;


    private SearchShopItem searchShopItem;


    @Override
    public void initialize(URL arg0, ResourceBundle arg1)
    {
        initHeader();
        Thread routine = new ThreadUpdateDataPieChart();
        routine.start();
        if (UserSession.getUser().getRole().equals("NCU"))
        {
            addButton.setOpacity(0);
        }
        searchShopItem = new SearchShopItem();
        initData();

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
        for (ShopItem shopItem : searchShopItem.GetListShopItem() )
        {
            AnchorPane item = null;
            try {
                item = FXMLLoader.load(getClass().getResource("/View/Item.fxml"));

                    Label label = (Label) item.lookup("#name");
                    if (label == null)
                    {
                        throw new IllegalArgumentException("Conteneur non trouvé dans le FXML");
                    }
                item.lookup("#button").setId(shopItem.GetId().toString());
                    ((Label) item.lookup(Constantes.VUE_ITEM_NAME)).setText(shopItem.GetCar().getBrand() + " - " + shopItem.GetCar().getName());
                    ((Label) item.lookup(Constantes.VUE_ITEM_HORSE_POWER)).setText(shopItem.GetCar().getHorsePower().toString());
                    ((Label) item.lookup(Constantes.VUE_ITEM_NB_SEATS)).setText(shopItem.GetCar().getNbSeats().toString());
                ((Label) item.lookup(Constantes.VUE_ITEM_PRICE)).setText(String.valueOf(shopItem.GetRentPrice()));


                conteneur.getChildren().add(item);
            } catch (IOException e) {
                e.printStackTrace();
            }
            catch (IllegalArgumentException e)
            {
                e.printStackTrace();
            }
        }



    }

    /**
     * Change la vue actuelle
     * @param viewName
     * @param event
     */
    private void changeView(String viewName, ActionEvent event) {
        try {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("/View/" + viewName + ".fxml"));
            Scene tableViewScene = new Scene(tableViewParent);
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            window.setScene(tableViewScene);
            window.centerOnScreen();
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





    @FXML
    private void searchShopItem(ActionEvent event) throws Exception
    {
        LocalDate startLocalDate = startDateText.getValue();
        LocalDate endLocalDate = endDateText.getValue();
        if (startLocalDate != null && endLocalDate != null)
        {
            String startAdress = startAddressText.getText();
            String endAddress = endAddressText.getText();

            this.searchShopItem = new SearchShopItem(startLocalDate, endLocalDate, startAdress, endAddress);
            initData();

        }





    }

    @FXML
    private void addShopItem(ActionEvent event)
    {
        changeView("AddShopItem", event);
    }
}
