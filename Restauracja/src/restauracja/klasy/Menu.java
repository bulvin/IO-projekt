package restauracja.klasy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "menu")
public class Menu implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_przedmiotu;


    private String nazwa;

    @Column(precision = 9,scale = 2)
    private double cena;

    private String kategoria;



    public Menu(int id_przedmiotu, String nazwa, double cena, String kategoria) {
        this.id_przedmiotu = id_przedmiotu;
        this.nazwa = nazwa;
        this.cena = cena;
        this.kategoria = kategoria;
    }

    public Menu() {

    }

    public void setId_przedmiotu(Integer id_przedmiotu) {
        this.id_przedmiotu = id_przedmiotu;
    }


    public int getId_przedmiotu() {
        return id_przedmiotu;
    }

    public void setId_przedmiotu(int id_przedmiotu) {
        this.id_przedmiotu = id_przedmiotu;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public String getKategoria() {
        return kategoria;
    }

    public void setKategoria(String kategoria) {
        this.kategoria = kategoria;
    }

    @Override
    public String toString() {
        return nazwa + "   " + String.format("%.2f ",cena)+ " zł";
    }

}
