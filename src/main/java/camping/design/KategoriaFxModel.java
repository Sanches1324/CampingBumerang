package camping.design;

import camping.dao.CampingDaoFactory;
import camping.dao.KategoriaDao;
import camping.entities.Kategoria;
import java.util.List;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class KategoriaFxModel {

    private LongProperty id;
    private StringProperty nazov;
    private ObservableList<String> nazvy;

    public KategoriaFxModel() {
        id = new SimpleLongProperty();
        nazov = new SimpleStringProperty();
        nazvy = FXCollections.observableArrayList();
    }

    public LongProperty idProperty() {
        return id;
    }

    public Long getId() {
        return id.get();
    }

    public void setId(Long id) {
        this.id.set(id);
    }

    public String getNazov() {
        return nazov.get();
    }

    public void setNazov(String nazov) {
        this.nazov.set(nazov);
    }

    public StringProperty NazovProperty() {
        return nazov;
    }

    public ObservableList<String> getNazvy() {
        readAll();
        return nazvy;
    }

    public void setNazvy(ObservableList<String> nazvy) {
        this.nazvy = nazvy;
    }

    private void readAll() {
        KategoriaDao kategoriaDao = CampingDaoFactory.INSTANCE.getMySqlKategoriaDao();
        List<KategoriaFxModel> listKategorii = kategoriaDao.getAll();
        for (KategoriaFxModel kategoria : listKategorii) {
            nazvy.add(kategoria.toString());
        }
    }

    @Override
    public String toString() {
        return getNazov(); //To change body of generated methods, choose Tools | Templates.
    }

}
