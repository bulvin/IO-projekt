package restauracja.aplikacja_gui;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;


public class SceneController {


    private Stage stage;

    public SceneController(Stage stage) {
        this.stage = stage;
    }

    public SceneController(){

    }
   protected void zmienNode(String fxml,Event event){
       Parent pane;
       try {
           pane = FXMLLoader.load(
                   getClass().getResource(fxml));
           Scene scene = new Scene( pane );
           stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
           stage.setScene(scene);
           stage.show();
           Rectangle2D wymiaryMonitora = Screen.getPrimary().getVisualBounds();
           stage.setX((wymiaryMonitora.getWidth() - stage.getWidth())/2);
           stage.setY((wymiaryMonitora.getHeight() - stage.getHeight())/2);
       } catch (IOException e) {
           e.printStackTrace();
       }

   }

   protected void zmienScene(String fxml){

        Parent pane;
        try {
            pane = FXMLLoader.load(
                    getClass().getResource(fxml));
            Scene scene = new Scene( pane );
            pokazStage(scene);
            Rectangle2D wymiaryMonitora = Screen.getPrimary().getVisualBounds();
            stage.setX((wymiaryMonitora.getWidth() - stage.getWidth())/2);
            stage.setY((wymiaryMonitora.getHeight() - stage.getHeight())/2);
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
    public void pokazStage(Scene scene){
        stage = new Stage();
        stage.setScene(scene);

        stage.setResizable(false);
        stage.setTitle("Restauracja");
        stage.show();
        Rectangle2D wymiaryMonitora = Screen.getPrimary().getVisualBounds();
        stage.setX((wymiaryMonitora.getWidth() - stage.getWidth())/2);
        stage.setY((wymiaryMonitora.getHeight() - stage.getHeight())/2);
    }
}
