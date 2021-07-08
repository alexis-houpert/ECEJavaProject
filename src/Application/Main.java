package Application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage appStage) throws Exception {

        Parent menu = FXMLLoader.load(getClass().getResource("/View/Login.fxml"));
        Scene scene = new Scene(menu);
        appStage.setTitle("Rent a car");
        //appStage.getIcons().add(new Image("/images/app-logo.png"));
        appStage.setResizable(false);
        appStage.setScene(scene);
        appStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}