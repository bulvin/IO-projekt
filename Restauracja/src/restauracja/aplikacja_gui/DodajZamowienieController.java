package restauracja.aplikacja_gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import restauracja.baza_danych_zarzadzanie.MenuDAO;
import restauracja.baza_danych_zarzadzanie.ZamowieniaDAO;
import restauracja.klasy.Menu;
import restauracja.klasy.*;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DodajZamowienieController implements Initializable {

    @FXML
    private TableView<Zamowienia> zamowienieTableView;
    @FXML
    private TableColumn<Zamowienia, String> przedmiotCol;
    @FXML
    private TableColumn<Zamowienia, String> cenaCol;
    @FXML
    private TableColumn<Zamowienia, Integer> iloscCol;
    @FXML
    private ComboBox<String> comboPrzedmiot;
    @FXML
    private TextField iloscField;
    @FXML
    private ComboBox<Integer> comboStolik;

    @FXML
    private Button btnDodajPrzedmiot;
    @FXML
    private Button wroc;
    @FXML
    private Button zlozZamowienie;
    @FXML
    private Button btnUsunProdukt;
    @FXML
    private Button btnCena;
    @FXML
    private Button zapisz;
    @FXML
    private Button zakoncz;
    @FXML
    private Label labelCenaZamowienia;

    private Pracownik pracownik;
    private ModelZamowienia modelZamowienia;
    private Menu menu;





    private ObservableList<String> nazwyPrzedmiotow = FXCollections.observableArrayList();
    private ObservableList<Integer> numerStolika = FXCollections.observableArrayList();

    private ZamowieniaDAO zamowieniaDAO = new ZamowieniaDAO();
    private ObservableList<Zamowienia> zamowienias = zamowieniaDAO.zamowieniaPrzedmioty();
    private MenuDAO menuDAO = new MenuDAO();
    private   List<Zamowienia> przedmioty = new ArrayList<>();
    private List<Zamowienia> przedmiotyTemp;


    private ZamowieniaInformacje zamowieniaInformacje;



    @FXML
    private void dodajPrzedmiotDoZamowienia(MouseEvent event){

        String nazwa = comboPrzedmiot.getValue();
        Menu menu = menuDAO.przedmiotPoNazwie(nazwa);

        int ilosc = 0;
        if(walidacjaIlosci(iloscField.getText())
                &&
                (iloscField.getText().length() == 2 || iloscField.getText().length() == 1)
                && comboPrzedmiot.getValue()!=null)
        {
            ilosc = Integer.parseInt(iloscField.getText());
            Zamowienia zamowienia = new Zamowienia();
            zamowienia.setMenu(menu);
            zamowienia.setIlosc(ilosc);
            zamowienias.add(zamowienia);
            przedmioty.add(zamowienia);


        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR,"Nie udało się dodać prrzedmiotu");
            alert.setHeaderText("Nieudane dodanie przedmiotu - ilosc max 99");
            alert.showAndWait();
        }


    }
    @FXML
    private void wroc(MouseEvent mouseEvent){
       SceneController sceneController = new SceneController();
       sceneController.zmienNode("zamowienia.fxml",mouseEvent);

    }
    @FXML
    private void okZamowienie(MouseEvent mouseEvent){

        if(zamowienieTableView.getItems().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Nie wybrano żadnych produktów");
            alert.setHeaderText("Nieudane dodanie zamowienia");
            alert.showAndWait();
        }
        else{

            ZamowieniaInformacje zamowienie = new ZamowieniaInformacje();
            int select = comboStolik.getSelectionModel().getSelectedItem();
            Stolik stolik = zamowieniaDAO.getStolik(select);
            stolik.setStatus(true);
            zamowienie.setStolik(stolik);
            zamowienie.setData_zamowienia(new Timestamp(System.currentTimeMillis()));
            pracownik = LoginController.pracownik;
            zamowienie.setPracownik(pracownik);
            zamowienie.setPrzedmioty(przedmioty);
            zamowienie.setStatus_zamowienia(true);

            zamowieniaDAO.updateStolik(stolik);

            zamowieniaDAO.dodajZamowienie(zamowienie);



            SceneController sceneController = new SceneController();
            sceneController.zmienNode("zamowienia.fxml",mouseEvent);
        }

    }
    @FXML
    private void usunProdukt(ActionEvent event){

    Zamowienia zamowienia = zamowienieTableView.getSelectionModel().getSelectedItem();
    int idzamowienie = zamowienieTableView.getSelectionModel().getSelectedIndex();



        if(zamowienia!=null){

            zamowienieTableView.getItems().remove(idzamowienie);
            przedmioty.remove(zamowienia);
            if(sprawdzPrzedmiot(zamowienia.getId()))
            {
                zamowieniaDAO.usunPrzedmiotyZamowienie(zamowienia.getId());
            }


        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR,"Nie wybrano produktu");
            alert.setHeaderText("Nieudane usunięcie produktu");
            alert.showAndWait();
        }
    }
    @FXML
    private void edycjaZamowienia(ActionEvent event){
       if(zamowieniaInformacje!=null){
           // przedmiotyTemp.addAll(przedmioty);

           przedmiotyTemp.addAll(przedmioty);
           zamowieniaInformacje.setPracownik(pracownik);
           int select = comboStolik.getSelectionModel().getSelectedItem();
           Stolik stolik = zamowieniaDAO.getStolik(select);
          zamowieniaInformacje.setStolik(stolik);
          zamowieniaInformacje.setData_zamowienia(new Timestamp(System.currentTimeMillis()));
          zamowieniaInformacje.setPrzedmioty(przedmiotyTemp);
          zamowieniaInformacje.setStatus_zamowienia(true);
          zamowieniaDAO.modyfikuj(zamowieniaInformacje);
           SceneController sceneController = new SceneController();
           sceneController.zmienNode("zamowienia.fxml",event);

       }else{
           System.out.println("jest puste");
       }

    }
    @FXML
    private void zakonczZamowienie(ActionEvent event){

        if(zamowieniaInformacje!=null){
            zamowieniaInformacje.setStatus_zamowienia(false);
            zamowieniaInformacje.setPrzedmioty(przedmiotyTemp);
            zamowieniaInformacje.getStolik().setStatus(false);
            zamowieniaDAO.updateStolik(zamowieniaInformacje.getStolik());
            zamowieniaDAO.modyfikuj(zamowieniaInformacje);
            SceneController sceneController = new SceneController();
            sceneController.zmienNode("zamowienia.fxml",event);
        }

    }

    @FXML
    private void liczCenaZamowienia(ActionEvent event){

        labelCenaZamowienia.setText(String.format("%.2f zł", obliczanieCeny(przedmiotyTemp)));

    }

    @FXML
    private void comboStolikListener(ActionEvent event){
            if (comboStolik.getValue() > 0 && zamowieniaInformacje==null) {
                zlozZamowienie.setDisable(false);
        }
    }

    private boolean walidacjaIlosci(String ilosc){
        return ilosc.matches("(0*[1-9][0-9]*)");
    }

    public BigDecimal obliczanieCeny(List<Zamowienia> przedmiotyTemp){

        List<BigDecimal> cenyPrzedmiotow = new ArrayList<>();


        if(przedmiotyTemp!=null)
        {
            przedmiotyTemp = zamowieniaDAO.przedmiotyDoZamowienia(zamowieniaInformacje);
            for(Zamowienia przedmiot: przedmioty){
                cenyPrzedmiotow.add(BigDecimal.valueOf(przedmiot.getMenu().getCena()*przedmiot.getIlosc()));
            }
        }
        else{
            przedmiotyTemp = przedmioty;
        }

        if(!zamowienieTableView.getItems().isEmpty()) {

            for (Zamowienia przedmiot : przedmiotyTemp) {
                cenyPrzedmiotow.add(BigDecimal.valueOf(przedmiot.getMenu().getCena()*przedmiot.getIlosc()));
            }

            BigDecimal suma = BigDecimal.ZERO;
            for (BigDecimal cena : cenyPrzedmiotow) {
                suma = suma.add(cena);
            }
            System.out.println("Suma  = " + suma);
            return suma;
        }
        else return BigDecimal.ZERO;
    }
    private boolean sprawdzPrzedmiot(int id){
      Zamowienia zamowienia =   zamowieniaDAO.getPrzedmiotZamowienia(id);
        return zamowienia != null;

    }

    public void setZamowieniaInformacje(ZamowieniaInformacje zamowieniaInformacje) {
        this.zamowieniaInformacje = zamowieniaInformacje;
        if(zamowieniaInformacje!=null)
        {
            zapisz.setDisable(false);
            zlozZamowienie.setDisable(true);
            zakoncz.setDisable(false);
            comboStolik.setValue(zamowieniaInformacje.getStolik().getId_stolika());
            zamowienias = zamowieniaDAO.przedmiotyDoZamowienia(zamowieniaInformacje);
            zamowienieTableView.setItems(zamowienias);
        }


    }

    public void setPrzedmiotyTemp(List<Zamowienia> przedmiotyTemp) {
        this.przedmiotyTemp = zamowieniaDAO.przedmiotyDoZamowienia(zamowieniaInformacje);
    }

    public void setPracownik(Pracownik pracownik) {
        this.pracownik = pracownik;
    }

    public void setModelZamowienia(ModelZamowienia modelZamowienia) {

        this.modelZamowienia = modelZamowienia;

    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        zamowienieTableView.setItems(zamowienias);
        zamowienieTableView.getItems().clear();
        przedmiotCol.setCellValueFactory(new PropertyValueFactory<>("menu"));
        iloscCol.setCellValueFactory(new PropertyValueFactory<>("ilosc"));
        nazwyPrzedmiotow.addAll(menuDAO.listaPrzedmiotowNazwa());

        comboPrzedmiot.setItems(nazwyPrzedmiotow);

        List<Stolik> temp = zamowieniaDAO.wolneStoliki();
        for(Stolik stolik: temp){
            numerStolika.add(stolik.getId_stolika());
        }
      comboStolik.setItems(numerStolika);

    }
}
