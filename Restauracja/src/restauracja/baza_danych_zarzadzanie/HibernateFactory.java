package restauracja.baza_danych_zarzadzanie;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import restauracja.klasy.*;

public class HibernateFactory {
    public  SessionFactory getSessionFactory(){

        Configuration configuration = new Configuration().configure("restauracja/cfg/hibernate.cfg.xml");
        configuration.addAnnotatedClass(Pracownik.class);
        configuration.addAnnotatedClass(Menu.class);
        configuration.addAnnotatedClass(Stolik.class);
        configuration.addAnnotatedClass(ZamowieniaInformacje.class);
        configuration.addAnnotatedClass(Zamowienia.class);


        StandardServiceRegistryBuilder serviceRegistry =
                new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());

        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry.build());

        return sessionFactory;

    }
}
