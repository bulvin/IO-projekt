
package restauracja.aplikacja_gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class Program extends Application {


    public static void main(String[] args) {
       launch(args);
    }



    @Override
    public void start(Stage primaryStage)  {
        try{

            Parent root = FXMLLoader.load(getClass().getResource("logowanie.fxml"));
            Scene scene = new Scene(root);

            primaryStage.setTitle("Restauracja");
            primaryStage.centerOnScreen();
            primaryStage.setResizable(false);
            primaryStage.setScene(scene);
            primaryStage.show();
            Rectangle2D wymiaryMonitora = Screen.getPrimary().getVisualBounds();
            primaryStage.setX((wymiaryMonitora.getWidth() - primaryStage.getWidth())/2);
            primaryStage.setY((wymiaryMonitora.getHeight() - primaryStage.getHeight())/2);
        }catch (Exception e){
            e.printStackTrace();
        }

            
    }

}
