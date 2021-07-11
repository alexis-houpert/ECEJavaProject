package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateAccountController implements Initializable {

    @FXML private Pane header;

    @FXML private TextField emailText;
    @FXML private TextField passwordText;
    @FXML private TextField firstNameText;
    @FXML private TextField lastNameText;
    @FXML private TextField adressText;

    @FXML private Label errorEmail;
    @FXML private Label errorPasswd;
    @FXML private Label errorFirstName;
    @FXML private Label errorLastName;
    @FXML private Label errorAdress;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
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
    public void createAccount(ActionEvent event)
    {
        System.out.println(emailText.getText());
        if (emailText.getText() == null | emailText.getText() == "" )
        {
            errorEmail.setText("Email is mandatory");
            errorEmail.setStyle("-fx-text-fill: #f62828");
        }
        if (passwordText == null && passwordText.getText() == "")
        {
            errorPasswd.setText("Password is mandatory");
            errorPasswd.setStyle("-fx-text-fill: #f62828");

        }
        if (firstNameText == null && firstNameText.getText() == "")
        {
            errorFirstName.setText("FIrst Name is mandatory");
            errorFirstName.setStyle("-fx-text-fill: #f62828");

        }
        if (lastNameText == null && lastNameText.getText() == "")
        {
            errorLastName.setText("Last Name is mandatory");
            errorLastName.setStyle("-fx-text-fill: #f62828");

        }
        if (adressText == null && adressText.getText() == "")
        {
            errorAdress.setText("Adress is mandatory");
            errorAdress.setStyle("-fx-text-fill: #f62828");

        }



    }
}
