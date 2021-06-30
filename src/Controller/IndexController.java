package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class IndexController implements Initializable {


    @FXML private Button QuitButton;
    @FXML private Button AccountButton;




    @Override
    public void initialize(URL arg0, ResourceBundle arg1)
    {
        //create model object
        //Load Compnents here


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
