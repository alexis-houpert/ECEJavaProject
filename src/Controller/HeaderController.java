package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Alexis HOUPERT
 * @author Louis DUTTIER
 * This controller is used to handle all actions from Header view
 */
public class HeaderController implements Initializable {

    @Override
    public void initialize(URL arg0, ResourceBundle arg1)
    { }

    @FXML
    public void disconnect(ActionEvent event)
    {
        try {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("/View/Login.fxml"));
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
    public void actionDisplayIndex(ActionEvent event)
    {
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

    @FXML
    public void GetAccount(ActionEvent event)
    {
        try {
            FXMLLoader fxmlLoader =  new FXMLLoader(getClass().getResource("/View/AccountInfo.fxml"));
            Parent tableViewParent = (Parent)fxmlLoader.load();
            Scene tableViewScene = new Scene(tableViewParent);
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            window.setScene(tableViewScene);
            window.centerOnScreen();
            AccountInfoController controller = fxmlLoader.<AccountInfoController>getController();
            controller.initHeader();
            controller.initData();
            window.show();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
