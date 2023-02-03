package restauracja.aplikacja_gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import javafx.stage.Screen;
import javafx.stage.Stage;

import restauracja.baza_danych_zarzadzanie.PracownikDAO;
import restauracja.klasy.Pracownik;

import java.io.IOException;
import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;


public class LoginController   {
        @FXML
        private TextField nazwaUzytkownikaField;

        @FXML
        private TextField hasloField;

        @FXML
        private Button buttonLog;

        @FXML
        private Label label;

       public static Pracownik pracownik;

        private final PracownikDAO pracownikDAO = new PracownikDAO();


    @FXML
        private void login(ActionEvent Event){
                String nazwaUzytkownika = nazwaUzytkownikaField.getText();
                String haslo = hasloField.getText();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
                String data = dtf.format(LocalDateTime.now());

        if(pracownikDAO.czyManagerIstnieje(nazwaUzytkownika,haslo)){

                    pracownik = pracownikDAO.czytaj(nazwaUzytkownika,haslo);
                    pracownik.setDataOstatniegoLogowania(data);
                    pracownikDAO.update(pracownik.getId_pracownika(), pracownik);
            System.out.println(pracownik.toString());
                     FXMLLoader loader = new FXMLLoader(getClass().getResource("managerHome.fxml"));
                     try {
                         Stage stageZamknij = (Stage) buttonLog.getScene().getWindow();
                         stageZamknij.close();
                         AnchorPane pane = loader.load();
                         ManagerHomeController managerHomeController = loader.getController();
                         managerHomeController.setPracownik(pracownik);
                         Stage stage = (Stage) ((Node)Event.getSource()).getScene().getWindow();
                         stage.setScene(new Scene(pane));
                         stage.show();
                         Rectangle2D wymiaryMonitora = Screen.getPrimary().getVisualBounds();
                         stage.setX((wymiaryMonitora.getWidth() - stage.getWidth())/2);
                         stage.setY((wymiaryMonitora.getHeight() - stage.getHeight())/2);


                     } catch (IOException e) {
                         e.printStackTrace();
                     }



                 }else if(pracownikDAO.czyPracownikIstnieje(nazwaUzytkownika,haslo)){

                     pracownik = pracownikDAO.czytaj(nazwaUzytkownika,haslo);
            pracownik.setDataOstatniegoLogowania(data);
                    pracownikDAO.update(pracownik.getId_pracownika(), pracownik);
                     FXMLLoader loader = new FXMLLoader(getClass().getResource("pracownikHome.fxml"));
                     try {
                         Stage stageZamknij = (Stage) buttonLog.getScene().getWindow();
                         stageZamknij.close();
                         AnchorPane pane = loader.load();

                         Stage stage = (Stage) ((Node)Event.getSource()).getScene().getWindow();
                         stage.setScene(new Scene(pane));
                         stage.show();
                         Rectangle2D wymiaryMonitora = Screen.getPrimary().getVisualBounds();
                         stage.setX((wymiaryMonitora.getWidth() - stage.getWidth())/2);
                         stage.setY((wymiaryMonitora.getHeight() - stage.getHeight())/2);


                     } catch (IOException e) {
                         e.printStackTrace();
                     }

                 }
                 else {
                     Alert alert = new Alert(Alert.AlertType.WARNING);


                     alert.setTitle("WARNING");
                     alert.setHeaderText("\n" +
                             "Nieprawidłowe dane logowania - Logowanie nieudane");
                     alert.setContentText("Proszę wprowadzic poprawny login i haslo");

                     alert.setX(620);
                     alert.setY(400);
                     alert.showAndWait();

                 }

         }

}
