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

public class LoginController implements Initializable {

    @FXML private Button loginButton;
    @FXML private Button quitButton;
    @FXML private Button createAccountButton;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {}

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
     * Connexion à l'application
     * @param event
     * @throws Exception
     */
    @FXML
    private void actionLogin(ActionEvent event) throws Exception {
        this.changeView("SearchCar", event);
    }

    /**
     * Créer un compte
     * @param event
     * @throws Exception
     */
    @FXML
    private void actionCreateAccount(ActionEvent event) throws Exception {
        this.changeView("CreateAccount", event);
    }

    /**
     * Quitte l'application
     * @param event
     */
    @FXML
    private void actionQuit(ActionEvent event) {
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.close();
    }


}
