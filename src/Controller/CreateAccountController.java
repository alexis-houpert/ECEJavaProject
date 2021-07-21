package Controller;

import Model.DataAccessLayer.DalUser;
import Model.User.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

public class CreateAccountController implements Initializable {

    @FXML private Pane header;

    @FXML private Pane textFields;

    @FXML private TextField emailText;
    @FXML private TextField passwordText;
    @FXML private TextField firstNameText;
    @FXML private TextField lastNameText;
    @FXML private TextField adressText;

    @FXML private Pane errorsMessage;
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
        for (Node node : errorsMessage.getChildren())
        {
            ((Label) node).setText("");
        }

        boolean flag = false;

        if (emailText.getText().isEmpty())
        {
            Node node = errorsMessage.getChildren().get(0);
            errorEmail.setText("Email is mandatory");
            errorEmail.setStyle("-fx-text-fill: #f62828");
            flag = true;
        }
        if (passwordText.getText().isEmpty())
        {
            errorPasswd.setText("Password is mandatory");
            errorPasswd.setStyle("-fx-text-fill: #f62828");
            flag = true;
        }
        if (firstNameText.getText().isEmpty())
        {
            errorFirstName.setText("First Name is mandatory");
            errorFirstName.setStyle("-fx-text-fill: #f62828");
            flag = true;
        }
        if (lastNameText.getText().isEmpty())
        {
            errorLastName.setText("Last Name is mandatory");
            errorLastName.setStyle("-fx-text-fill: #f62828");
            flag = true;
        }
        if (adressText.getText().isEmpty())
        {
            errorAdress.setText("Adress is mandatory");
            errorAdress.setStyle("-fx-text-fill: #f62828");
            flag = true;
        }

        if (flag)
        {
            return;
        }

        String password = passwordText.getText();
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(password.getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }


        try {
            Customer.UpdateNbCustomer();
            DalUser.AddUser(new Customer(emailText.getText().toLowerCase(Locale.ROOT), generatedPassword, Customer.GetNewNumCustomer(),
                    firstNameText.getText(),
                    lastNameText.getText(), adressText.getText()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        this.changeView("Login", event);

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
}
