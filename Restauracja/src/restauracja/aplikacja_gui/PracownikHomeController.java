package restauracja.aplikacja_gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import restauracja.klasy.Pracownik;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PracownikHomeController implements Initializable {


    @FXML
    private Button buttonWylogowania;
    @FXML
    private Button buttonMenu;
    @FXML
    private Button buttonZamowienia;
    @FXML
    private Label labelPracownik;




    private final SceneController sceneController = new SceneController();
    private final Pracownik pracownik1 = LoginController.pracownik;


    @FXML
    public void wyloguj(ActionEvent event){

        sceneController.zmienScene("logowanie.fxml");
        Stage stageZamknij = (Stage) buttonWylogowania.getScene().getWindow();
        stageZamknij.close();

    }

    @FXML
    public void zmienNaMenu(MouseEvent event)  {

        ModelMenu modelMenu = new ModelMenu();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));
        try {
            AnchorPane pane = loader.load();
            MenuController controller = loader.getController();
            controller.setModelMenu(modelMenu);
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(pane));
            stage.show();
            Rectangle2D wymiaryMonitora = Screen.getPrimary().getVisualBounds();
            stage.setX((wymiaryMonitora.getWidth() - stage.getWidth())/2);
            stage.setY((wymiaryMonitora.getHeight() - stage.getHeight())/2);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @FXML
    public void zmienNaZamowienia(MouseEvent event){

        ModelZamowienia modelZamowienia = new ModelZamowienia();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("zamowienia.fxml"));
        try {
            AnchorPane pane = loader.load();


            ZamowieniaController zamowieniaController = loader.getController();
            zamowieniaController.setModelZamowienia(modelZamowienia);
            zamowieniaController.setPracownik(pracownik1);

            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(pane));
            stage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }


    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        labelPracownik.setText(pracownik1.getImie()+" "+pracownik1.getNazwisko());

    }
}
