package restauracja.klasy;


import javax.persistence.*;

@Entity
@Table(name="zamowieniamenu")
public class Zamowienia  {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        @ManyToOne
        @JoinColumn(name="id_zamowienia")
        private ZamowieniaInformacje zamowieniaInformacje;

        @ManyToOne
        @JoinColumn(name="id_przedmiotu")
        private Menu menu;
        @Column(name="ilosc")
        private int ilosc;


        public Zamowienia(ZamowieniaInformacje zamowieniaInformacje, Menu menu, int ilosc) {
                this.zamowieniaInformacje = zamowieniaInformacje;
                this.menu = menu;
                this.ilosc = ilosc;
        }
        public Zamowienia(Menu menu,int ilosc){
                this.menu = menu;
                this.ilosc = ilosc;
        }
        public Zamowienia(){

        }

        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
        }

        public ZamowieniaInformacje getZamowieniaInformacje() {
                return zamowieniaInformacje;
        }

        public void setZamowieniaInformacje(ZamowieniaInformacje zamowieniaInformacje) {
                this.zamowieniaInformacje = zamowieniaInformacje;
        }

        public Menu getMenu() {
                return menu;
        }

        public void setMenu(Menu menu) {
                this.menu = menu;
        }

        public int getIlosc() {
                return ilosc;
        }

        public void setIlosc(int ilosc) {
                this.ilosc = ilosc;
        }

        @Override
        public String toString() {
                return "Zamowienia{" +
                        "id=" + id +
                        ", zamowieniaInformacje=" + zamowieniaInformacje +
                        ", menu=" + menu +
                        ", ilosc=" + ilosc +
                        '}';
        }
}
