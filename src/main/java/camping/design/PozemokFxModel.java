package camping.design;

import camping.dao.CampingDaoFactory;
import camping.dao.PozemokDao;
import camping.entities.Pozemok;
import java.time.LocalDate;
import java.util.List;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
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
    private StringProperty obsadenostString;
    private ObjectProperty<LocalDate> datumPrichodu;
    private ObjectProperty<LocalDate> datumOdchodu;
    private ObservableList<PozemokFxModel> pozemky;

    public PozemokFxModel() {
        this.cisloPozemku = new SimpleLongProperty();
        this.kategoriaId = new SimpleLongProperty();
        this.kategoriaString = new SimpleStringProperty();
        this.cena = new SimpleIntegerProperty();
        this.obsadenost = new SimpleBooleanProperty();
        datumPrichodu = new SimpleObjectProperty<>();
        datumOdchodu = new SimpleObjectProperty<>();

        this.pozemky = FXCollections.observableArrayList();
        this.obsadenostString = new SimpleStringProperty();
    }

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
        return obsadenostString;
    }

    public String getObsadenostBoolean() {
        return obsadenostString.get();
    }

    public void setObsadenostBoolean(String obsadenostBoolean) {
        this.obsadenostString.set(obsadenostBoolean);
    }

    public ObjectProperty<LocalDate> datumPrichoduProperty() {
        return datumPrichodu;
    }

    public LocalDate getDatumPrichodu() {
        return datumPrichodu.get();
    }

    public void setDatumPrichodu(LocalDate datumPrichodu) {
        this.datumPrichodu.set(datumPrichodu);
    }

    public ObjectProperty<LocalDate> datumOdchoduProperty() {
        return datumOdchodu;
    }

    public LocalDate getDatumOdchodu() {
        return datumOdchodu.get();
    }

    public void setDatumOdchodu(LocalDate datumOdchodu) {
        this.datumOdchodu.set(datumOdchodu);
    }

    private void readAll() {
        PozemokDao pozemokDao = CampingDaoFactory.INSTANCE.getMySqlPozemokDao();
        List<PozemokFxModel> listPozemkov = pozemokDao.getAll();
        for (PozemokFxModel pozemok : listPozemkov) {
//            if (pozemok.getDatumOdchodu() != null && pozemok.getDatumPrichodu() != null) {
//                if (pozemok.getDatumOdchodu().isAfter(LocalDate.now()) && pozemok.getDatumPrichodu().isBefore(LocalDate.now())) {
//                    pozemok.setObsadenost(true);
//                    pozemok.setObsadenostBoolean("obsadený");
//                } else {
//                    pozemok.setObsadenost(false);
//                    pozemok.setObsadenostBoolean("voľný");
//                }
//            }
            pozemky.add(pozemok);
        }
    }

    @Override
    public String toString() {
        return cisloPozemku + "";
    }

}
