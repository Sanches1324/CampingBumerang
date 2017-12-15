package camping.design;

import camping.dao.CampingDaoFactory;
import camping.dao.PozemokDao;
import camping.entities.Pozemok;
import java.util.List;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PozemokFxModel {

    private LongProperty cisloPozemku;
    private LongProperty kategoriaId;
    private KategoriaFxModel kategoriaModel;
    private StringProperty kategoriaString;
    private IntegerProperty cena;
    private BooleanProperty obsadenost;
    private ObservableList<PozemokFxModel> pozemky;
    private StringProperty obsadenostBoolean;
//    private ObservableList<PozemokFxModel> pozemkyTab = FXCollections.observableArrayList();

    public PozemokFxModel() {
        this.cisloPozemku = new SimpleLongProperty();
        this.kategoriaId = new SimpleLongProperty();
        this.kategoriaString = new SimpleStringProperty();
        this.cena = new SimpleIntegerProperty();
        this.obsadenost = new SimpleBooleanProperty();
        this.pozemky = FXCollections.observableArrayList();
        this.obsadenostBoolean = new SimpleStringProperty();
    }
//
//    public KategoriaFxModel getKategoriaModel() {
//        return kategoriaModel;
//    }
//
//    public void setKategoriaModel(KategoriaFxModel kategoriaModel) {
//        this.kategoriaModel = kategoriaModel;
//    }

    public StringProperty kategoriaStringProperty() {
        return kategoriaString;
    }

    public String getKategoriaString() {
        return kategoriaString.get();
    }

    public void setKategoriaString(String kategoriaString) {
        this.kategoriaString.set(kategoriaString);
    }

    
    public LongProperty cisloPozemkuProperty() {
        return cisloPozemku;
    }

    public Long getCisloPozemku() {
        return cisloPozemku.get();
    }

    public void setCisloPozemku(Long cisloPozemku) {
        this.cisloPozemku.set(cisloPozemku);
    }

    public LongProperty kategoriaIdProperty() {
        return kategoriaId;
    }

    public Long getKategoriaId() {
        return kategoriaId.get();
    }

    public void setKategoriaId(Long kategoriaId) {
        this.kategoriaId.set(kategoriaId);
    }

    public IntegerProperty cenaProperty() {
        return cena;
    }

    public Integer getCena() {
        return cena.get();
    }

    public void setCena(Integer cena) {
        this.cena.set(cena);
    }

    public BooleanProperty obsadenostProperty() {
        return obsadenost;
    }

    public Boolean getObsadenost() {
        return obsadenost.get();
    }

    public void setObsadenost(Boolean obsadenost) {
        this.obsadenost.set(obsadenost);
    }

    public ObservableList<PozemokFxModel> getPozemky() {
        readAll();
        return pozemky;

    }

    public void setPozemky(ObservableList<PozemokFxModel> pozemky) {
        this.pozemky = pozemky;
    }

    public StringProperty obsadenostBooleanProperty() {
        return obsadenostBoolean;
    }

    public String getObsadenostBoolean() {
        return obsadenostBoolean.get();
    }

    public void setObsadenostBoolean(String obsadenostBoolean) {
        this.obsadenostBoolean.set(obsadenostBoolean);
    }

    private void readAll() {
        PozemokDao pozemokDao = CampingDaoFactory.INSTANCE.getMySqlPozemokDao();
        List<PozemokFxModel> listPozemkov = pozemokDao.getAll();
        for (PozemokFxModel pozemok : listPozemkov) {
            pozemky.add(pozemok);
        }
    }

    @Override
    public String toString() {
        return cisloPozemku + "";
    }

}
