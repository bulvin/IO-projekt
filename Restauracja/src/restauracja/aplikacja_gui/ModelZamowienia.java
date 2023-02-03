package restauracja.aplikacja_gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import restauracja.baza_danych_zarzadzanie.ZamowieniaDAO;
import restauracja.klasy.ZamowieniaInformacje;

public class ModelZamowienia {

    private final ObservableList<ZamowieniaInformacje> ListaZamowieniaInformacje;
    private ZamowieniaDAO zamowieniaDAO = new ZamowieniaDAO();

    public ModelZamowienia() {
        this.ListaZamowieniaInformacje = FXCollections.observableArrayList();
        ListaZamowieniaInformacje.addAll(zamowieniaDAO.zaladujZamowienia());
    }

    public ObservableList<ZamowieniaInformacje> getListaZamowieniaInformacje() {
        return ListaZamowieniaInformacje;
    }
}
