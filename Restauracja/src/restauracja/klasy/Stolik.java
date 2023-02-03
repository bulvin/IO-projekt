package restauracja.klasy;

import javafx.scene.Scene;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "stolik")
public class Stolik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_stolika;
    @Column(name = "status")
    private  boolean status;

    public void setId_stolika(Integer id_stolika) {
        this.id_stolika = id_stolika;
    }


    public int getId_stolika() {
        return id_stolika;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return
                  Integer.toString(id_stolika);

    }
}
