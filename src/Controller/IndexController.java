package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class IndexController implements Initializable {

    @FXML private Pane header;

    @FXML private VBox conteneur;


    @Override
    public void initialize(URL arg0, ResourceBundle arg1)
    {
        try {
            AnchorPane headerAnchor = FXMLLoader.load(getClass().getResource("/View/Header.fxml"));
            header.getChildren().add(headerAnchor);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //create model object
        //Load Compnents here
        AnchorPane item = null;
        AnchorPane item2 = null;
        try {
            item = FXMLLoader.load(getClass().getResource("/View/Item.fxml"));
            item2 = FXMLLoader.load(getClass().getResource("/View/Item.fxml"));

            conteneur.getChildren().add(item);
            conteneur.getChildren().add(item2);
            Label name = (Label) item.lookup("name");
            //name.setText("Tesla Model 3");

        } catch (IOException e) {
            e.printStackTrace();
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

    /**
     * Change vue to the detail for a item in the list
     * @param event
     * @throws Exception
     */
    @FXML
    private void actionDisplayItem(ActionEvent event) throws Exception {
        this.changeView("ShopItem", event);
    }

    /**
     * Change the vue to display user informations
     * @param event
     * @throws Exception
     */
    @FXML
    private void actionAccountInfo(ActionEvent event) throws Exception {
        this.changeView("AccountInfo", event);
    }
}
