package restauracja.klasy;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;


@Entity
@Table(name = "zamowienia_informacje")
public class ZamowieniaInformacje implements Serializable {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id_zamowienia;

        @ManyToOne
        @JoinColumn(name = "id_stolika")
        private Stolik stolik;

        @Column(name="data_zamowienia")
        private Timestamp data_zamowienia;

        @ManyToOne
        @JoinColumn(name ="id_pracownika")
        private Pracownik pracownik;

        @Column(name = "status_zamowienia")
        private boolean status_zamowienia;

        @OneToMany(mappedBy="zamowieniaInformacje",fetch = FetchType.LAZY)
        private List<Zamowienia> przedmioty;

        public ZamowieniaInformacje(){

        }

        public List<Zamowienia> getPrzedmioty() {
                return przedmioty;
        }

        public void setPrzedmioty(List<Zamowienia> przedmioty) {
                this.przedmioty = przedmioty;
        }

        public Integer getId_zamowienia() {
                return id_zamowienia;
        }

        public void setId_zamowienia(Integer id_zamowienia) {
                this.id_zamowienia = id_zamowienia;
        }

        public Stolik getStolik() {
                return stolik;
        }

        public void setStolik(Stolik stolik) {
                this.stolik = stolik;
        }

        public Timestamp getData_zamowienia() {
                return data_zamowienia;
        }

        public void setData_zamowienia(Timestamp data_zamowienia) {
                this.data_zamowienia = data_zamowienia;
        }

        public Pracownik getPracownik() {
                return pracownik;
        }

        public void setPracownik(Pracownik pracownik) {
                this.pracownik = pracownik;
        }

        public boolean isStatus_zamowienia() {
                return status_zamowienia;
        }

        public void setStatus_zamowienia(boolean status_zamowienia) {
                this.status_zamowienia = status_zamowienia;
        }

        @Override
        public String toString() {
                return "ZamowieniaInformacje{" +
                        "id_zamowienia=" + id_zamowienia +
                        ", stolik=" + stolik +
                        ", data_zamowienia=" + data_zamowienia +
                        ", pracownik=" + pracownik +
                        ", status_zamowienia=" + status_zamowienia +
                        ", przedmioty=" + przedmioty +
                        '}';
        }
}
