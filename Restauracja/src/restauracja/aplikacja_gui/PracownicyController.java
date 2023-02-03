package restauracja.aplikacja_gui;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import restauracja.baza_danych_zarzadzanie.PracownikDAO;
import restauracja.klasy.Pracownik;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


public class PracownicyController implements Initializable {

    @FXML
    private Button buttonWroc;
    @FXML
    private Label textLabel;
    @FXML
    private TableView<Pracownik> pracownikTableView;
    @FXML
    private TableColumn<Pracownik,String> idCol;
    @FXML
    private TableColumn<Pracownik,String> nazwaUzytkownikaCol;
    @FXML
    private TableColumn<Pracownik,String> hasloCol;
    @FXML
    private TableColumn<Pracownik,String> imieCol;
    @FXML
    private TableColumn<Pracownik,String> nazwiskoCol;
    @FXML
    private TableColumn<Pracownik,String> typCol;

    @FXML
    private TableColumn<Pracownik, String> telefonCol;

    @FXML
    private TableColumn<Pracownik, String> ostatnieLogCol;

    @FXML
    private Button buttonDodaj;
    @FXML
    private Button buttonUsun;
    @FXML
    private Button buttonEdytuj;

    @FXML
    private Button btnDodajPracownika;




    private final PracownikDAO pracownikDAO = new PracownikDAO();
    private ModelPracownika modelPracownika;
    private ObservableList<Pracownik> dane;
    private Pracownik pracownik;


    public PracownikDAO getPracownikDAO() {
        return pracownikDAO;
    }

    public ObservableList<Pracownik> getDane() {
        return dane;
    }

    SceneController sceneController = new SceneController();


    @FXML
    private void wroc(MouseEvent event){


        sceneController.zmienNode("managerHome.fxml",event);


    }



    @FXML
    private void usunPracownika(ActionEvent event){
       Pracownik pracownik = pracownikTableView.getSelectionModel().getSelectedItem();
       if(pracownik!=null){
           int wybranyIdPracownik = pracownik.getId_pracownika();

           if(pracownik.getNazwa_uzytkownika().equals("admin")){
               Alert alert = new Alert(Alert.AlertType.WARNING);
               alert.setHeaderText("Proba usuniecia admina");
               alert.setContentText("Nie mozna usunac admina");
               alert.showAndWait();


           }
           else {
               Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
               alert.setHeaderText("Usuwanie pracownika");
               alert.setContentText("Czy chcesz na pewno usunąć pracownika o nazwie użytkownika:  "+pracownik.getNazwa_uzytkownika());
               Optional<ButtonType> wynik = alert.showAndWait();
               if(wynik.get()==ButtonType.OK){
                   pracownikDAO.usunPracownika(wybranyIdPracownik);
                   pracownikTableView.getItems().remove(pracownik);

               }

           }


       }
       else {
           Alert alert = new Alert(Alert.AlertType.WARNING);
           alert.setHeaderText("Nieudane usunięcie pracownika");
           alert.setContentText("Potrzeba wybrac pracownika do usunięcia");
           alert.showAndWait();
       }



    }
    @FXML
    private void dodajPracownika(ActionEvent event) throws IOException {

            Pracownik pracownik = new Pracownik();
            boolean okclick = pokazDodawanie(pracownik);
            if(okclick){
                pracownikDAO.dodajPracownika(pracownik);
                modelPracownika.getDane().add(pracownik);


            }
    }
    @FXML
    public void edytujPracownika(ActionEvent event)
    {
        int select = pracownikTableView.getSelectionModel().getSelectedIndex();
        Pracownik pracownik = pracownikTableView.getSelectionModel().getSelectedItem();


        if(pracownik!=null){
            boolean okclick = pokazDodawanie(pracownik);
            if(okclick){
                int idPracownika = pracownik.getId_pracownika();
                modelPracownika.getDane().set(select,pracownik);
                pracownikDAO.update(idPracownika,pracownik);
                System.out.println(pracownik.toString());


            }

        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING,"Nie wybrano pracownika do edycji");
            alert.showAndWait();
        }


    }


    public void setModelDanych(ModelPracownika modelPracownika) {
        this.modelPracownika = modelPracownika;
        pracownikTableView.setItems(modelPracownika.getDane());
    }

    public boolean pokazDodawanie(Pracownik pracownik)
    {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("dodawaniePracownika.fxml"));
            AnchorPane pane = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Restauracja");
            dialogStage.initModality(Modality.WINDOW_MODAL);

            Scene scene = new Scene(pane);
            dialogStage.setScene(scene);
            dialogStage.setResizable(false);
            DodawaniePracownikaController controller = loader.getController();
            controller.setPracownik(pracownik);
            controller.setModelDanych(modelPracownika);


            controller.setDialogStage(dialogStage);


            dialogStage.showAndWait();

            return controller.isOkClick();


        }catch (IOException e ){
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {



        nazwaUzytkownikaCol.setCellValueFactory(new PropertyValueFactory<>("nazwa_uzytkownika"));

        hasloCol.setCellValueFactory(new PropertyValueFactory<>("haslo"));

        imieCol.setCellValueFactory(new PropertyValueFactory<>("imie"));

        nazwiskoCol.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));

        typCol.setCellValueFactory(new PropertyValueFactory<>("typ_pracownika"));

        telefonCol.setCellValueFactory(param -> {
            String telefon = param.getValue().getTelefon();
            if (telefon != null){
                return new ReadOnlyStringWrapper("+48"+telefon);
            }else{
                return new ReadOnlyStringWrapper("");
            }

        });

        ostatnieLogCol.setCellValueFactory(new PropertyValueFactory<>("dataOstatniegoLogowania"));

//        idCol.setCellFactory(col -> new TableCell<Pracownik,String>() {
//            @Override
//            public void updateIndex(int index) {
//
//                super.updateIndex(index);
//                if (isEmpty() || index < 0) {
//                    setText(null);
//                } else {
//                    setText(Integer.toString(++index));
//                }
//            }
//        });


    }


}


