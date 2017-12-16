package camping.design;

import camping.dao.CampingDaoFactory;
import camping.dao.PouzivatelDao;
import camping.entities.Pouzivatel;
import java.util.List;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PouzivatelFxModel {

    private StringProperty pozicia;
    private StringProperty meno;
    private IntegerProperty odrobeneHodiny;
    private IntegerProperty vyplata;
    private ObservableList<PouzivatelFxModel> pouzivatelia;

    public PouzivatelFxModel() {
        this.pozicia = new SimpleStringProperty();
        this.meno = new SimpleStringProperty();
        this.odrobeneHodiny = new SimpleIntegerProperty();
        this.vyplata = new SimpleIntegerProperty();
        this.pouzivatelia = FXCollections.observableArrayList();
    }

    public StringProperty poziciaProperty() {
        return pozicia;
    }

    public String getPozicia() {
        return pozicia.get();
    }

    public void setPozicia(String pozicia) {
        this.pozicia.set(pozicia);
    }

    public StringProperty menoProperty() {
        return meno;
    }

    public String getMeno() {
        return meno.get();
    }

    public void setMeno(String meno) {
        this.meno.set(meno);
    }

    public IntegerProperty odrobeneHodinyProperty() {
        return odrobeneHodiny;
    }

    public Integer getOdrobeneHodiny() {
        return odrobeneHodiny.get();
    }

    public void setOdrobeneHodiny(Integer odrobeneHodiny) {
        this.odrobeneHodiny.set(odrobeneHodiny);
    }

    public IntegerProperty vyplataProperty() {
        return vyplata;
    }

    public Integer getVyplata() {
        return vyplata.get();
    }

    public void setVyplata(Integer vyplata) {
        this.vyplata.set(vyplata);
    }

    public ObservableList<PouzivatelFxModel> getPouzivatelov() {
        readAll();
        return pouzivatelia;
    }

    public void setPouzivatelov(ObservableList<PouzivatelFxModel> pouzivatelia) {
        this.pouzivatelia = pouzivatelia;
    }

    private void readAll() {
        PouzivatelDao pouzivatelDao = CampingDaoFactory.INSTANCE.getMySqlPouzivatelDao();
        List<PouzivatelFxModel> listPouzivatelov = pouzivatelDao.getAll();
        for (PouzivatelFxModel pouzivatel : listPouzivatelov) {
            pouzivatelia.add(pouzivatel);
        }
    }

}
