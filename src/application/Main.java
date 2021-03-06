package application;

import controller.MainMenuController;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {

    static MainMenuController mainMenuController = new MainMenuController();
    static DbConnector dbConnector = new DbConnector();

        @Override
        public void start(Stage primaryStage) {
            try {
                AnchorPane root = FXMLLoader.load(getClass().getResource("../view/departmentView.fxml"));
                Scene scene = new Scene(root,1000,800);
                scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

    public static void main(String[] args) {
        launch(args);
    }
}

