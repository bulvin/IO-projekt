package restauracja.baza_danych_zarzadzanie;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import restauracja.klasy.*;

import java.util.List;

public class ZamowieniaDAO {
    private final HibernateFactory hibernateFactory;

    public ZamowieniaDAO(){
        hibernateFactory = new HibernateFactory();
    }

    public void dodajZamowienie(ZamowieniaInformacje zamowieniaInformacje)
    {
        Session session = hibernateFactory.getSessionFactory().openSession();
        session.beginTransaction();
        try{
          List<Zamowienia> przedmioty =   zamowieniaInformacje.getPrzedmioty();
            session.save(zamowieniaInformacje);
            for(Zamowienia przedmiot: przedmioty) {
                session.save(new Zamowienia(zamowieniaInformacje, przedmiot.getMenu(), przedmiot.getIlosc()));
            }
            session.getTransaction().commit();
        }catch (Exception e){

            session.getTransaction().rollback();
            e.printStackTrace();

        }finally {
            session.close();
        }

    }

    public void usunZamowienie(int idZamowienieInformacje){

        Session session = hibernateFactory.getSessionFactory().openSession();
        session.beginTransaction();
        try  {

          ZamowieniaInformacje zamowieniaInformacje= session.find(ZamowieniaInformacje.class, idZamowienieInformacje);
            session.delete(zamowieniaInformacje);
            session.getTransaction().commit();

        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();

        }finally {
            session.close();
        }
    }
    public void usunPrzedmiotyZamowienie(int idZamowienie){

        Session session = hibernateFactory.getSessionFactory().openSession();
        session.beginTransaction();
        try  {

            Zamowienia zamowienia= session.find(Zamowienia.class, idZamowienie);
            session.delete(zamowienia);
            session.getTransaction().commit();

        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();

        }finally {
            session.close();
        }
    }

    public ZamowieniaInformacje get(int id){
        Session session = hibernateFactory.getSessionFactory().openSession();
        session.beginTransaction();
        try{
           ZamowieniaInformacje zamowieniaInformacje = session.get(ZamowieniaInformacje.class,id);
            return zamowieniaInformacje;

        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
            throw new RuntimeException(e);

        }finally {
            session.close();
        }

    }
    public Zamowienia getPrzedmiotZamowienia(int id){
        try {
            Session session = hibernateFactory.getSessionFactory().openSession();
            session.beginTransaction();
            Zamowienia zamowienia = session.get(Zamowienia.class,id);
            return zamowienia;

        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }
    public void modyfikuj(ZamowieniaInformacje noweZamowienie){

        Session session = hibernateFactory.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(noweZamowienie);

        List<Zamowienia> przedmioty = noweZamowienie.getPrzedmioty();

        for(Zamowienia przedmiot: przedmioty){
            session.saveOrUpdate(new Zamowienia(noweZamowienie,przedmiot.getMenu(),przedmiot.getIlosc()));
        }

        session.getTransaction().commit();
        session.close();

    }

    public ObservableList<ZamowieniaInformacje> zaladujZamowienia(){
        ObservableList<ZamowieniaInformacje> lista = FXCollections.observableArrayList();
        Session session = hibernateFactory.getSessionFactory().openSession();
        session.beginTransaction();
        try{
            Query query = session.createQuery("from ZamowieniaInformacje ORDER BY data_zamowienia DESC ");
            List<ZamowieniaInformacje> wynikList = query.getResultList();
            lista.addAll(wynikList);
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        return lista;
    }

    public ObservableList<Zamowienia> przedmiotyDoZamowienia(ZamowieniaInformacje zamowienie) {
        ObservableList<Zamowienia> zamowionePrzedmioty = FXCollections.observableArrayList();
        Session session = hibernateFactory.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Query query = session.createQuery("FROM Zamowienia WHERE zamowieniaInformacje =:zamowienie");
           query.setParameter("zamowienie",zamowienie);

            List<Zamowienia> wynikList = query.getResultList();
            zamowionePrzedmioty.addAll(wynikList);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return zamowionePrzedmioty;
    }



    public ObservableList<Zamowienia> zamowieniaPrzedmioty(){
        ObservableList<Zamowienia> listaNazw = FXCollections.observableArrayList();
        Session session = hibernateFactory.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Query query = session.createQuery("from Zamowienia ");
            List<Zamowienia> wynikList = query.getResultList();
            listaNazw.addAll(wynikList);
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return  listaNazw;
    }

    public Stolik getStolik(int id){
        Session session = hibernateFactory.getSessionFactory().openSession();
        session.beginTransaction();
        try{
            Stolik stolik = session.get(Stolik.class,id);
            return stolik;

        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
            throw new RuntimeException(e);

        }finally {
            session.close();
        }
    }
    public void updateStolik(Stolik stolik){
        Session session = hibernateFactory.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(stolik);
        session.getTransaction().commit();
        session.close();
    }
    public List<Stolik> wolneStoliki(){
        Session session = hibernateFactory.getSessionFactory().openSession();
        session.beginTransaction();

        Query query = session.createQuery("FROM Stolik where status = false");
        List<Stolik> wolne = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return wolne;
    }
    public ObservableList<ZamowieniaInformacje> zamowieniaStatus(){
        ObservableList<ZamowieniaInformacje> lista = FXCollections.observableArrayList();
        Session session = hibernateFactory.getSessionFactory().openSession();
        session.beginTransaction();
        try{
            Query query = session.createQuery("from ZamowieniaInformacje WHERE status_zamowienia = true ");
            List<ZamowieniaInformacje> wynik = query.getResultList();
            lista.addAll(wynik);
            session.getTransaction().commit();
        }catch (HibernateException e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }

        return lista;

    }




}
