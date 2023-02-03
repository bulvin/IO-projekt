package restauracja.aplikacja_gui;

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
import restauracja.baza_danych_zarzadzanie.MenuDAO;
import restauracja.klasy.Menu;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    @FXML
    private TableView<Menu> menuTableView;
    @FXML
    private TableColumn<Menu,String> nazwaCol;
    @FXML
    private TableColumn<Menu,Number> cenaCol;
    @FXML
    private TableColumn<Menu,String> kategoriaCol;

    @FXML
    private Button btnDodaj;
    @FXML
    private Button btnEdytuj;
    @FXML
    private Button btnUsun;
    @FXML
    private Button btnWroc;

    private ModelMenu modelMenu;
    private final SceneController sceneController = new SceneController();
    private final MenuDAO menuDAO = new MenuDAO();


    @FXML
    private void wroc(MouseEvent event){

        if(LoginController.pracownik.getTyp_pracownika().equals("Manager"))
        {
            sceneController.zmienNode("managerHome.fxml",event);
        }
        else     sceneController.zmienNode("pracownikHome.fxml",event);


    }
    @FXML
    private void dodajPrzedmiot(ActionEvent event){
        Menu przedmiot = new Menu();
        boolean isOK = pokazDodawanie(przedmiot);
        if(isOK){
            menuDAO.dodajPrzedmiot(przedmiot);
            modelMenu.getDane().add(przedmiot);
        }
    }
    @FXML
    private void usunPrzedmiot(ActionEvent event){
        Menu przedmiot = menuTableView.getSelectionModel().getSelectedItem();
        if(przedmiot!=null){
            int id = przedmiot.getId_przedmiotu();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Usuwanie przedmiotu");
            alert.setContentText("Czy chcesz usunąć przedmiot :"+przedmiot.getNazwa());
            Optional<ButtonType> wynik = alert.showAndWait();
            if(wynik.get()==ButtonType.OK){
                menuDAO.usunPrzedmiot(id);
                menuTableView.getItems().remove(przedmiot);

            }

        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING,"Nie wybrano przedmiotu do usuniecia");
            alert.showAndWait();
        }




    }
    @FXML
    private void edytujPrzedmiot(ActionEvent event){

        int index = menuTableView.getSelectionModel().getSelectedIndex();
        Menu przedmiot = menuTableView.getSelectionModel().getSelectedItem();

        if(przedmiot!=null){
            boolean isOK = pokazDodawanie(przedmiot);
            int id = przedmiot.getId_przedmiotu();
            if(isOK){

                menuDAO.update(id,przedmiot);
                modelMenu.getDane().set(index,przedmiot);
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING,"Nie wybrano przedmiotu");
            alert.showAndWait();
        }




    }
    public boolean pokazDodawanie(Menu menu)
    {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("dodawanieMenu.fxml"));
            AnchorPane pane = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Restauracja");
            dialogStage.initModality(Modality.WINDOW_MODAL);

            Scene scene = new Scene(pane);
            dialogStage.setScene(scene);

            DodawanieMenuController controller = loader.getController();
            controller.setModelMenu(modelMenu);
            controller.setMenu(menu);
            controller.setDialogStage(dialogStage);


            dialogStage.showAndWait();

            return controller.isOKclick();


        }catch (IOException e ){
            e.printStackTrace();
            return false;
        }
    }
    private void ladujKolumny(){
        nazwaCol.setCellValueFactory(new PropertyValueFactory<>("nazwa"));
        cenaCol.setCellValueFactory(new PropertyValueFactory<>("cena"));
       cenaCol.setCellFactory(col -> new TableCell<Menu,Number>() {
           @Override
           protected void updateItem(Number cena, boolean empty) {
               super.updateItem(cena, empty);
               if (empty) {
                   setText(null);
               } else {
                   setText(String.format("%.2f zł", cena.doubleValue()));
               }
           }
       });

        kategoriaCol.setCellValueFactory(new PropertyValueFactory<>("kategoria"));
    }

    public void setModelMenu(ModelMenu modelMenu){
            this.modelMenu = modelMenu;
            menuTableView.setItems(modelMenu.getDane());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ladujKolumny();

    }
}
