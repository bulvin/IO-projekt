package restauracja.klasy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Pracownik")
public class Pracownik implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_pracownika;


    private String nazwa_uzytkownika;


    private String haslo;

    private String imie;

    private String nazwisko;

    private String telefon;

    private String typ_pracownika;

    private String dataOstatniegoLogowania;



    public Pracownik(String nazwa_uzytkownika, String haslo, String imie, String nazwisko, String typ_pracownika) {

        this.nazwa_uzytkownika = nazwa_uzytkownika;
        this.haslo = haslo;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.typ_pracownika = typ_pracownika;
    }

    public Pracownik() {

    }

    public int getId_pracownika() {
        return id_pracownika;
    }

    public void setId_pracownika(int id_pracownika) {
        this.id_pracownika = id_pracownika;
    }

    public String getNazwa_uzytkownika() {
        return nazwa_uzytkownika;
    }

    public void setNazwa_uzytkownika(String nazwa_uzytkownika) {
        this.nazwa_uzytkownika = nazwa_uzytkownika;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getTyp_pracownika() {
        return typ_pracownika;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public void setDataOstatniegoLogowania(String dataOstatniegoLogowania) {
        this.dataOstatniegoLogowania = dataOstatniegoLogowania;
    }

    public String getDataOstatniegoLogowania() {
        return dataOstatniegoLogowania;
    }

    public void setTyp_pracownika(String typ_pracownika) {
        this.typ_pracownika = typ_pracownika;
    }



    @Override
    public String toString() {
        return "Pracownik{" +
                "id_pracownika=" + id_pracownika +
                ", nazwa_uzytkownika='" + nazwa_uzytkownika + '\'' +
                ", haslo='" + haslo + '\'' +
                ", imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", typ_pracownika='" + typ_pracownika + '\'' +
                ", telefon = '" + telefon +
        ", dataOst='" + dataOstatniegoLogowania;

    }
}
