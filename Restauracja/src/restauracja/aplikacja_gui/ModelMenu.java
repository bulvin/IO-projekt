package restauracja.aplikacja_gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import restauracja.baza_danych_zarzadzanie.MenuDAO;
import restauracja.klasy.Menu;

public class ModelMenu {
    private final ObservableList<Menu> dane;

    public ModelMenu(){
        this.dane = FXCollections.observableArrayList();
        MenuDAO menuDAO = new MenuDAO();
        dane.addAll(menuDAO.zaladujMenu());
    }

    public ObservableList<Menu> getDane() {
        return dane;
    }
}
