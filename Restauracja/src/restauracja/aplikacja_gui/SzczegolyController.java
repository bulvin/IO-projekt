package restauracja.aplikacja_gui;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import restauracja.klasy.Pracownik;
import restauracja.klasy.Stolik;
import restauracja.klasy.Zamowienia;
import restauracja.klasy.ZamowieniaInformacje;

import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SzczegolyController implements Initializable {

    @FXML
    private TableView<Zamowienia> szczegolyTableView;
    @FXML
    private TableColumn<Zamowienia,String> przedmiotCol;
    @FXML
    private TableColumn<Zamowienia,String> cenaCol;
    @FXML
    private TableColumn<Zamowienia,String> iloscCol;
    @FXML
    private Label cenaZamowienia;
    @FXML
    private Label labelPracownik;
    @FXML
    private Label labelData;
    @FXML
    private Label labelStolik;




    private void ladujKolumny(){
        przedmiotCol.setCellValueFactory(param ->
            new SimpleStringProperty(param.getValue().getMenu().getNazwa()));
        cenaCol.setCellValueFactory(param -> new SimpleStringProperty(String.format("%.2f  zł",param.getValue().getMenu().getCena())));
        iloscCol.setCellValueFactory(new PropertyValueFactory<>("ilosc"));

    }

    public void setListaPrzedmiotow(ObservableList<Zamowienia> listaPrzedmiotow) {
        szczegolyTableView.setItems(listaPrzedmiotow);
        System.out.println(obliczanieCeny(listaPrzedmiotow));
        cenaZamowienia.setText(String.format("%.2f zł",obliczanieCeny(listaPrzedmiotow)));

    }

    public void setZamowieniaInformacje(ZamowieniaInformacje zamowieniaInformacje) {

        labelStolik.setText(String.valueOf(zamowieniaInformacje.getStolik().getId_stolika()));
        String data = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(zamowieniaInformacje.getData_zamowienia());
        labelData.setText(data);
        labelPracownik.setText(zamowieniaInformacje.getPracownik().getImie()+" " + zamowieniaInformacje.getPracownik().getNazwisko());

    }



    protected BigDecimal obliczanieCeny(ObservableList<Zamowienia> listaPrzedmiotow){

        List<BigDecimal> cenyPrzedmiotow = new ArrayList<>();

        if(!szczegolyTableView.getItems().isEmpty()) {

            for (Zamowienia przedmiot : listaPrzedmiotow) {
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
    @FXML
    private void wroc(MouseEvent event){
        SceneController sceneController = new SceneController();
        sceneController.zmienNode("zamowienia.fxml",event);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

            ladujKolumny();

    }
}
