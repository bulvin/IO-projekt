package restauracja.aplikacja_gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import restauracja.baza_danych_zarzadzanie.PracownikDAO;
import restauracja.klasy.Pracownik;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DodawaniePracownikaController implements Initializable {


   @FXML
   private TextField nazwaField;
    @FXML
    private TextField hasloField;
    @FXML
    private TextField imieField;
    @FXML
    private TextField nazwiskoField;

    @FXML
    private TextField telefonField;
    @FXML
    private ComboBox<String> comboTyp;
    @FXML
    private Button buttonZapisz;
    @FXML
    private Button btn;
    @FXML
    private Text text;

   private Stage dialogStage;
    private Pracownik pracownik;
    private  boolean okClick = false;



    private ObservableList<String> typPracownika = FXCollections.observableArrayList();{

        typPracownika.add("Pracownik");
        typPracownika.add("Manager");


    }
    public boolean isOkClick(){
        return okClick;
    }

    @FXML
    private void zamknij(MouseEvent mouseEvent){
        dialogStage.close();
    }
    @FXML
    public void zapisz(MouseEvent event) throws IOException {

        String nazwaUzytkownika = nazwaField.getText();
        String haslo = hasloField.getText();
        String imie = imieField.getText();
        String nazwisko = nazwiskoField.getText();
        String typ = comboTyp.getValue();
        String telefon = telefonField.getText();
        PracownikDAO pracownikDAO = new PracownikDAO();
        Pracownik pracownik1 =   pracownikDAO.get(nazwaUzytkownika);

        if(nazwaField.getText() ==null || nazwaField.getText().length() ==0 || hasloField.getText() == null ||hasloField.getText().length()==0||
                imieField.getText() == null || nazwiskoField.getText() ==null || imieField.getText().length() == 0 || nazwiskoField.getText().length() == 0
       )
        {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Nie udało się dodać pracownika");
            alert.setHeaderText("Prosze wypełnić pola formularza");
            alert.showAndWait();

        }
       else if(!sprawdzTel(telefon)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Nie udało się dodać pracownika");
            alert.setHeaderText("Numer telefonu jest niepoprawny");
            alert.showAndWait();
        }
        else if(!sprawdzName(imie)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Nie udało się dodać pracownika");
            alert.setHeaderText("Imię jest niepoprawne.");
            alert.showAndWait();
        }
        else if(pracownik1 != null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Nie udało się dodać pracownika");
            alert.setHeaderText("Pracownik z taką nazwą użytkownika już istnieje");
            alert.showAndWait();
        }

        else{
            pracownik.setNazwa_uzytkownika(nazwaUzytkownika);
            pracownik.setHaslo(haslo);
            pracownik.setImie(imie);
            pracownik.setNazwisko(nazwisko);
            pracownik.setTyp_pracownika(typ);
            pracownik.setTelefon(telefon);

            okClick = true;
            dialogStage.close();

        }
    }



    public void setModelDanych(ModelPracownika modelPracownika) {

    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setPracownik(Pracownik pracownik) {
        this.pracownik = pracownik;
        if(this.pracownik.getNazwa_uzytkownika() == null){
            text.setText("Dodaj pracownika");

        }
        else{
            text.setText("Edycja pracownika");
            nazwaField.setText(pracownik.getNazwa_uzytkownika());
            hasloField.setText(pracownik.getHaslo());
            imieField.setText(pracownik.getImie());
            nazwiskoField.setText(pracownik.getNazwisko());
            telefonField.setText(pracownik.getTelefon());
            comboTyp.setValue(pracownik.getTyp_pracownika());

        }

    }
    private  boolean sprawdzTel(String tel){
        return tel.matches("^\\d{9}$");
    }

    private  boolean sprawdzName(String text){
        return text.matches("^([A-Za-z])+$");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboTyp.setItems(typPracownika);
    }


}
