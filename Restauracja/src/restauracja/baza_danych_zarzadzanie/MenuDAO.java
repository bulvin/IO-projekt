package restauracja.baza_danych_zarzadzanie;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import restauracja.klasy.Menu;

import java.util.List;

public class MenuDAO {

    private final HibernateFactory hibernateFactory;

    public MenuDAO() {
        hibernateFactory = new HibernateFactory();
    }
    public void dodajPrzedmiot(Menu przedmiot)
    {
        Session session = hibernateFactory.getSessionFactory().openSession();
       session.beginTransaction();
        try{

            session.save(przedmiot);
           session.getTransaction().commit();
        }catch (Exception e){

            session.getTransaction().rollback();
            e.printStackTrace();

        }finally {
            session.close();
        }

    }
    public void usunPrzedmiot(int idPrzedmiot){

        Session session = hibernateFactory.getSessionFactory().openSession();
        session.beginTransaction();
        try  {

            Menu przedmiot = session.find(Menu.class, idPrzedmiot);
            session.delete(przedmiot);
          session.getTransaction().commit();

        } catch (Exception e) {
           session.getTransaction().rollback();
            e.printStackTrace();

        }finally {
            session.close();
        }
    }

    public Menu get(int id){
        Session session = hibernateFactory.getSessionFactory().openSession();
        session.beginTransaction();
        try{
            Menu przedmiot = session.get(Menu.class,id);
            return przedmiot;

        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
            throw new RuntimeException(e);

        }finally {
            session.close();
        }

    }

    public void update(int id, Menu nowyPrzedmiot){
        Session session = hibernateFactory.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Menu przedmiot = session.find(Menu.class,id);
            przedmiot.setNazwa(nowyPrzedmiot.getNazwa());
            przedmiot.setCena(nowyPrzedmiot.getCena());
            przedmiot.setKategoria(nowyPrzedmiot.getKategoria());
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
        }finally {
            session.close();
        }


    }

    public ObservableList<Menu> zaladujMenu(){
        ObservableList<Menu> listaPrzedmiotow = FXCollections.observableArrayList();
        Session session = hibernateFactory.getSessionFactory().openSession();
        session.beginTransaction();
        try{
            Query query = session.createQuery("from Menu ");
            List<Menu> wynikList = query.getResultList();
            listaPrzedmiotow.addAll(wynikList);
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        return listaPrzedmiotow;
    }
    public ObservableList<String> listaKategorii(){

        ObservableList<String> kategorie = FXCollections.observableArrayList();
        Session session = hibernateFactory.getSessionFactory().openSession();
        session.beginTransaction();
        try{
            Query query = session.createQuery("SELECT DISTINCT kategoria from Menu");
            List<String> wynikList = query.getResultList();
            kategorie.addAll(wynikList);

            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        return kategorie;
    }
    public ObservableList<String> listaNazw() {

        ObservableList<String> kategorie = FXCollections.observableArrayList();
        Session session = hibernateFactory.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Query query = session.createQuery("SELECT DISTINCT kategoria from Menu");
            List<String> wynikList = query.getResultList();
            kategorie.addAll(wynikList);

            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        return kategorie;
    }
    public Menu przedmiotPoNazwie(String nazwa){

        Session session = hibernateFactory.getSessionFactory().openSession();
        session.beginTransaction();
        try{
            Query query = session.createQuery("from Menu WHERE nazwa like :nazwa");
            query.setParameter("nazwa",nazwa);
            Menu menu = (Menu) query.uniqueResult();
            return menu;

        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        }finally {
            session.close();
        }

    }
    public List<String> listaPrzedmiotowNazwa() {
        ObservableList<String> nazwy = FXCollections.observableArrayList();
        Session session= hibernateFactory.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("SELECT nazwa FROM Menu  ");
        List<String> wynikList = query.getResultList();
        nazwy.addAll(wynikList);
        session.getTransaction().commit();
        session.close();
       return nazwy;
    }





}


