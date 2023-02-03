package restauracja.aplikacja_gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import restauracja.baza_danych_zarzadzanie.PracownikDAO;
import restauracja.klasy.Pracownik;

public class ModelPracownika {

    private final ObservableList<Pracownik> dane;


    public ModelPracownika(){

        this.dane = FXCollections.observableArrayList();
        PracownikDAO pracownikDAO = new PracownikDAO();
        dane.addAll(pracownikDAO.zaladujDanePracownikow());


    }

    public ObservableList<Pracownik> getDane() {
        return dane;
    }

    public void dodaj(Pracownik pracownik){
        PracownikDAO pracownikDAO = new PracownikDAO();
        pracownikDAO.dodajPracownika(pracownik);
        dane.add(pracownik);

    }



}
