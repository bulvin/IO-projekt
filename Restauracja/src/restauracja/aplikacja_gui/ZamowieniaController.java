package restauracja.aplikacja_gui;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import restauracja.baza_danych_zarzadzanie.ZamowieniaDAO;
import restauracja.klasy.Pracownik;
import restauracja.klasy.Zamowienia;
import restauracja.klasy.ZamowieniaInformacje;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class ZamowieniaController implements Initializable {


    @FXML
    private Button buttonDodaj;
    @FXML
    private Button buttonInformacje;
    @FXML
    private Button buttonWroc;
    @FXML
    private Button buttonEdytuj;
    @FXML
    private Button buttonUsun;
    @FXML
    private TextField szukajField;
    @FXML
    private TableView<ZamowieniaInformacje> zamowieniaInformacjeTableView;
    @FXML
    private TableColumn<ZamowieniaInformacje,String> idCol;
    @FXML
    private TableColumn<ZamowieniaInformacje, Integer> nrStolikaCol;
    @FXML
    private TableColumn<ZamowieniaInformacje, String> dataZamowieniaCol;
    @FXML
    private TableColumn<ZamowieniaInformacje,String> status;


    private ModelZamowienia modelZamowienia;
    private Pracownik pracownik;
    private ZamowieniaInformacje zamowieniaInformacje;
    private final ZamowieniaDAO zamowieniaDAO = new ZamowieniaDAO();
    private final SceneController sceneController = new SceneController();
    private final ModelZamowienia modelZamowienia2 = new ModelZamowienia();


    @FXML
    public void dodajZamowienie(ActionEvent event){

        FXMLLoader loader = new FXMLLoader(getClass().getResource("dodajZamowienie.fxml"));
        try {
            AnchorPane pane = loader.load();
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

            stage.setTitle("Restauracja");

            Scene scene = new Scene(pane);
            stage.setScene(scene);
            DodajZamowienieController dodajZamowienieController = loader.getController();

            dodajZamowienieController.setModelZamowienia(modelZamowienia);
            dodajZamowienieController.setPracownik(pracownik);

            stage.show();
        }catch(Exception e){
            e.printStackTrace();
        }

    }
    @FXML
    private void wroc(ActionEvent event){
        if(LoginController.pracownik.getTyp_pracownika().equals("Manager"))
        {
            sceneController.zmienNode("managerHome.fxml",event);
        }
        else     sceneController.zmienNode("pracownikHome.fxml",event);
    }
    @FXML
    private void edytuj(ActionEvent event){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("dodajZamowienie.fxml"));

        try {

            ZamowieniaInformacje zamowienie = zamowieniaInformacjeTableView.getSelectionModel().getSelectedItem();

            if(zamowienie!=null){
                if(zamowienie.isStatus_zamowienia())
                {
                    AnchorPane pane = loader.load();
                    Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

                    stage.setTitle("Restauracja");

                    Scene scene = new Scene(pane);
                    stage.setScene(scene);
                    DodajZamowienieController dodajZamowienieController = loader.getController();
                    pracownik = LoginController.pracownik;
                    List<Zamowienia> przedmioty  = zamowienie.getPrzedmioty();
                    dodajZamowienieController.setPrzedmiotyTemp(przedmioty);
                    dodajZamowienieController.setPracownik(pracownik);
                    dodajZamowienieController.setZamowieniaInformacje(zamowienie);


                    stage.show();
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.ERROR,"To zamówienie ma status zakończony");
                    alert.setHeaderText("Tego zamówienia nie możesz edytować");
                    alert.showAndWait();
                }

            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR,"Wybierz zamowienie");
                alert.setHeaderText("Nie wybrano zamowienia do edycji");
                alert.showAndWait();
            }


        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    private void usunZamowienie(ActionEvent event){
        ZamowieniaInformacje zamowienie = zamowieniaInformacjeTableView.getSelectionModel().getSelectedItem();

        if(zamowienie!=null){
            int idZamowienie = zamowienie.getId_zamowienia();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Usuwanie zamówienia");
            alert.setContentText("Czy chcesz na pewno chcesz usunąć zamowienie?");
            Optional<ButtonType> wynik = alert.showAndWait();
            if(wynik.get()==ButtonType.OK){
                zamowieniaDAO.usunZamowienie(idZamowienie);
                zamowieniaInformacjeTableView.getItems().remove(zamowienie);

            }

        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING,"Nie wybrano zamówienia do usunięcia");
            alert.showAndWait();
        }

    }

    @FXML
    private void pokazInformacje(ActionEvent event) throws IOException {

        ZamowieniaInformacje zamowienie = zamowieniaInformacjeTableView.getSelectionModel().getSelectedItem();


        if(zamowienie!=null){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("szczegoly.fxml"));
            AnchorPane pane = loader.load();
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(pane));

            List<Zamowienia> przedmioty = zamowienie.getPrzedmioty();
            ObservableList<Zamowienia> listaPrzedmiotow = FXCollections.observableArrayList();
            listaPrzedmiotow.addAll(przedmioty);
            SzczegolyController controller = loader.getController();
            controller.setZamowieniaInformacje(zamowienie);
            controller.setListaPrzedmiotow(listaPrzedmiotow);
            pracownik = LoginController.pracownik;


            stage.show();

        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR,"Wybierz zamowienie");
            alert.setHeaderText("Nie wybrano zamowienia");
            alert.showAndWait();
        }

    }
    @FXML
    private void szukanie(KeyEvent event){

        FilteredList<ZamowieniaInformacje> filteredList = new FilteredList<>(modelZamowienia2.getListaZamowieniaInformacje(),b->true);
            szukajField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredList.setPredicate(zamowieniaInformacje1 -> {
                    if (newValue.isEmpty()) {
                        return true;
                    }
                    String szukanie = newValue.toLowerCase();
                    if (zamowieniaInformacje1.getData_zamowienia().toString().contains(szukanie)) {
                        return true;
                    }
                    if (zamowieniaInformacje1.getStolik().toString().contains(szukanie)) {
                        return true;
                    }
                    if(zamowieniaInformacje1.isStatus_zamowienia() && (newValue.contains("W trakcie") || newValue.contains("w trakcie")) ){

                        return true;
                    }
                    return false;
                });
            });
        SortedList<ZamowieniaInformacje> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(zamowieniaInformacjeTableView.comparatorProperty());

        zamowieniaInformacjeTableView.setItems(sortedList);
    }




    public void setModelZamowienia(ModelZamowienia modelZamowienia) {
        this.modelZamowienia = modelZamowienia;

    }

    public void setPracownik(Pracownik pracownik) {
        this.pracownik = pracownik;
        System.out.println(pracownik);
    }
    private void ladujKolumny(){

        idCol.setCellFactory(col -> new TableCell<ZamowieniaInformacje,String>() {
            @Override
            public void updateIndex(int index) {

                super.updateIndex(index);
                if (isEmpty() || index < 0) {
                    setText(null);
                } else {
                    setText(Integer.toString(++index));
                }
            }
        });
        nrStolikaCol.setCellValueFactory(new PropertyValueFactory<>("stolik"));
        dataZamowieniaCol.setCellValueFactory(
                param -> {
                    SimpleStringProperty property = new SimpleStringProperty();
                    DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
                    if(param.getValue().getData_zamowienia() != null)
                    {
                        property.setValue(dateFormat.format(param.getValue().getData_zamowienia()));
                    }

                    return property;
                }
        );
        status.setCellValueFactory(param -> {

            boolean status = param.getValue().isStatus_zamowienia();
            String statusString;
            if(status){
                statusString = "W trakcie";
            }
            else{
                statusString = "Ukończony";
            }
            return new ReadOnlyStringWrapper(statusString);
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ModelZamowienia modelZamowienia = new ModelZamowienia();
        zamowieniaInformacjeTableView.setItems(modelZamowienia.getListaZamowieniaInformacje());

        ladujKolumny();

    }
}
