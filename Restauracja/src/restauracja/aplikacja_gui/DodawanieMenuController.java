package restauracja.aplikacja_gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import restauracja.baza_danych_zarzadzanie.MenuDAO;
import restauracja.klasy.Menu;

import java.net.URL;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

public class DodawanieMenuController implements Initializable {
    @FXML
    private Button btnZapisz;
    @FXML
    private Button btnZamknij;
    @FXML
    private TextField przedmiotField;
    @FXML
    private TextField cenaField;
    @FXML
    private ComboBox<String> comboKategoria;

    private ModelMenu modelMenu;
    private Menu przedmiot;
    private Stage dialogStage;
    private ObservableList<String> kategorie = FXCollections.observableArrayList();
    private MenuDAO menuDAO = new MenuDAO();
    private boolean isOKclick = false;


    @FXML
    private void zapisz(MouseEvent event){
            String nazwa = przedmiotField.getText();

            String kategoria = comboKategoria.getValue();

            if(nazwa == null || nazwa.length()==0 || kategoria==null || !walidacjaCeny(cenaField.getText())){

                Alert alert = new Alert(Alert.AlertType.ERROR,"Proszę poprawnie wpisac dane");
                alert.setHeaderText("Nieudane dodanie przedmiotu");
                alert.showAndWait();
            }
            else if(menuDAO.przedmiotPoNazwie(nazwa) != null && uniqueId()){
            Alert alert = new Alert(Alert.AlertType.WARNING,"To danie już znajduje się na liście");
            alert.setHeaderText("Nieudane dodanie przedmiotu");
            alert.showAndWait();
        }
            else {
                double cena = Double.parseDouble(cenaField.getText());
                przedmiot.setNazwa(nazwa);
                przedmiot.setCena(cena);
                przedmiot.setKategoria(kategoria);
                isOKclick = true;
                dialogStage.close();
            }

    }
    @FXML
    private void zamknij(MouseEvent event){
            dialogStage.close();
    }

    public void setModelMenu(ModelMenu modelMenu){
        this.modelMenu = modelMenu;
    }
    public void setMenu(Menu przedmiot){
        this.przedmiot = przedmiot;

        przedmiotField.setText(przedmiot.getNazwa());
        if(przedmiot.getCena()!=0){
            cenaField.setText(String.format(Locale.ROOT,"%.2f",przedmiot.getCena()));

        }

        comboKategoria.setValue(przedmiot.getKategoria());

    }
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isOKclick() {

        return isOKclick;
    }
    private boolean walidacjaCeny(String cena){
        return  cena.matches("\\d+(\\.\\d{1,2})?");
    }

    private boolean uniqueId(){
        for(Menu menu : menuDAO.zaladujMenu()){
            if(przedmiot.getId_przedmiotu() == menu.getId_przedmiotu()){
                return false;
            }
        }
        return true;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
            kategorie.addAll(menuDAO.listaKategorii());
            comboKategoria.setItems(kategorie);
    }
}
