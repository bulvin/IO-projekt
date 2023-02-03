package restauracja.baza_danych_zarzadzanie;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import restauracja.klasy.Pracownik;

import java.util.List;

public class PracownikDAO {

    private final HibernateFactory hibernateFactory;

    public PracownikDAO(){
        this.hibernateFactory = new HibernateFactory();
    }

    public boolean czyPracownikIstnieje(String nazwa, String haslo){

        boolean czyPracownikIstnieje = false;
        List<Pracownik> pracownikList;
        Session session = hibernateFactory.getSessionFactory().openSession();
        session.beginTransaction();
        try{

            Query query = session.createQuery("from Pracownik WHERE nazwa_uzytkownika = :nazwa_uzytkownika and haslo = :haslo");
            query.setParameter("nazwa_uzytkownika",nazwa);
            query.setParameter("haslo",haslo);

            Pracownik pracownik = (Pracownik) query.uniqueResult();

            if(query.uniqueResult()!=null){
                czyPracownikIstnieje= true;
            }


            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();

        } finally {
            session.close();
        }
    return czyPracownikIstnieje;

    }

    public boolean czyManagerIstnieje(String nazwa, String haslo){

        boolean czyManagerIstnieje = false;

        Session session = hibernateFactory.getSessionFactory().openSession();
        session.beginTransaction();
        try{

            Query query = session.createQuery("from Pracownik WHERE nazwa_uzytkownika = :nazwa_uzytkownika and haslo = :haslo and typ_pracownika = :typ_pracownika" );
            query.setParameter("nazwa_uzytkownika",nazwa);
            query.setParameter("haslo",haslo);
            query.setParameter("typ_pracownika","Manager");

            query.uniqueResult();

            if(query.uniqueResult()!=null){
                czyManagerIstnieje = true;
            }

            session.getTransaction().commit();

        } catch (Exception e) {

            e.printStackTrace();
            session.getTransaction().rollback();

        } finally {
            session.close();
        }
        return czyManagerIstnieje;

    }

    public void dodajPracownika(Pracownik pracownik){
       Session session =  hibernateFactory.getSessionFactory().openSession();
       Transaction transaction = session.beginTransaction();
        try  {

            session.save(pracownik);
           transaction.commit();

        } catch (Exception e) {

            transaction.rollback();
            e.printStackTrace();

        }


    }
    public void usunPracownika(int id){
        Session session =  hibernateFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try  {
            Pracownik pracownik = session.find(Pracownik.class,id);
            session.delete(pracownik);
            transaction.commit();

        } catch (Exception e) {

            transaction.rollback();
            e.printStackTrace();

        }


    }
    public void update(int id,Pracownik nowyPracownik){
        Session session = hibernateFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try{

            Pracownik pracownik = session.find(Pracownik.class,id);
            pracownik.setNazwa_uzytkownika(nowyPracownik.getNazwa_uzytkownika());
            pracownik.setHaslo(nowyPracownik.getHaslo());
            pracownik.setImie(nowyPracownik.getImie());
            pracownik.setNazwisko(nowyPracownik.getNazwisko());
            pracownik.setTyp_pracownika(nowyPracownik.getTyp_pracownika());
            pracownik.setTelefon(nowyPracownik.getTelefon());
            pracownik.setDataOstatniegoLogowania(nowyPracownik.getDataOstatniegoLogowania());
            transaction.commit();
        }catch (Exception e){

            transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }


    }

    public Pracownik czytaj(String nazwaUzytkownika,String haslo){
        Session session =  hibernateFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try  {
            Query query = session.createQuery("from Pracownik WHERE nazwa_uzytkownika = :nazwa_uzytkownika and haslo = :haslo" );
            query.setParameter("nazwa_uzytkownika",nazwaUzytkownika);
            query.setParameter("haslo",haslo);

            Pracownik pracownik = (Pracownik) query.uniqueResult();
            return pracownik;

        } catch (Exception e) {

            transaction.rollback();
            e.printStackTrace();
            return null;

        }finally {
            session.close();
        }


    }
    public Pracownik get(String nazwaUzytkownika){
        Session session =  hibernateFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try  {
            Query query = session.createQuery("from Pracownik WHERE nazwa_uzytkownika = :nazwa_uzytkownika" );
            query.setParameter("nazwa_uzytkownika",nazwaUzytkownika);

            return (Pracownik) query.uniqueResult();

        } catch (Exception e) {

            transaction.rollback();
            e.printStackTrace();
            return null;

        }finally {
            session.close();
        }


    }
    public ObservableList<Pracownik> zaladujDanePracownikow(){

        ObservableList<Pracownik> pracownicy = FXCollections.observableArrayList();

        Session session = hibernateFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try  {
            Query query = session.createQuery("from Pracownik ");
            List<Pracownik> listaWyniku =  (List<Pracownik>) query.getResultList();
            for(Pracownik id:listaWyniku)
            {
                pracownicy.add(id);

            }
            return pracownicy;

        } catch (Exception e) {

            transaction.rollback();
            e.printStackTrace();
            return null;

        }finally {
            session.close();
        }




    }

}
