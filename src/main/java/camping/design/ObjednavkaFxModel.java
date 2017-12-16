package camping.design;

import camping.dao.CampingDaoFactory;
import camping.dao.ObjednavkaDao;
import java.time.LocalDate;
import java.util.List;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ObjednavkaFxModel {

    private LongProperty id;
    private LongProperty pozemokId;
    private LongProperty pouzivatelId;
    private StringProperty menoZakaznika;
    private StringProperty telCisloZakaznika;
    private ObjectProperty<LocalDate> datumObjednavky;
    private ObjectProperty<LocalDate> datumPrichodu;
    private ObjectProperty<LocalDate> datumOdchodu;
    private LongProperty pocetDni;
    private BooleanProperty platba;
    private StringProperty platbaString;
    private ObservableList<ObjednavkaFxModel> objednavky;

    public ObjednavkaFxModel() {
        id = new SimpleLongProperty();
        pozemokId = new SimpleLongProperty();
        pouzivatelId = new SimpleLongProperty();
        menoZakaznika = new SimpleStringProperty();
        telCisloZakaznika = new SimpleStringProperty();
        datumObjednavky = new SimpleObjectProperty<>(LocalDate.now());
        datumPrichodu = new SimpleObjectProperty<>();
        datumOdchodu = new SimpleObjectProperty<>();
        pocetDni = new SimpleLongProperty();
        platba = new SimpleBooleanProperty();
        platbaString = new SimpleStringProperty();
        objednavky = FXCollections.observableArrayList();
    }

    public ObservableList<ObjednavkaFxModel> getObjednavky() {
        readAll();
        return objednavky;
    }

    public void setObjednavky(ObservableList<ObjednavkaFxModel> objednavky) {
        this.objednavky = objednavky;
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

    public LongProperty pozemokIdProperty() {
        return pozemokId;
    }

    public Long getPozemokId() {
        return pozemokId.get();
    }

    public void setPozemokId(Long pozemokId) {
        this.pozemokId.set(pozemokId);
    }

    public LongProperty pouzivatelIdProperty() {
        return pouzivatelId;
    }

    public Long getPouzivatelId() {
        return pouzivatelId.get();
    }

    public void setPouzivatelId(Long pouzivatelId) {
        this.pouzivatelId.set(pouzivatelId);
    }

    public ObjectProperty<LocalDate> datumObjednavkyProperty() {
        return datumObjednavky;
    }

    public LocalDate getDatumObjednavky() {
        return datumObjednavky.get();
    }

    public void setDatumObjednavky(LocalDate datumObjednavky) {
        this.datumObjednavky.set(datumObjednavky);
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

    public LongProperty pocetDniProperty() {
        return pocetDni;
    }

    public Long getPocetDni() {
        return pocetDni.get();
    }

    public void setPocetDni(Long pocetDni) {
        this.pocetDni.set(pocetDni);
    }

    public BooleanProperty platbaProperty() {
        return platba;
    }

    public Boolean getPlatba() {
        return platba.get();
    }

    public void setPlatba(Boolean platba) {
        this.platba.set(platba);
    }

    public StringProperty menoZakaznikaProperty() {
        return menoZakaznika;
    }

    public String getMenoZakaznika() {
        return menoZakaznika.get();
    }

    public void setMenoZakaznika(String menoZakaznika) {
        this.menoZakaznika.set(menoZakaznika);
    }

    public StringProperty telCisloZakaznikaProperty() {
        return telCisloZakaznika;
    }

    public String getTelCisloZakaznika() {
        return telCisloZakaznika.get();
    }

    public void setTelCisloZakaznika(String telCislo) {
        this.telCisloZakaznika.set(telCislo);
    }

    public StringProperty platbaStringProperty() {
        return platbaString;
    }

    public String getPlatbaString() {
        return platbaString.get();
    }

    public void setPlatbaString(String platbaString) {
        this.platbaString.set(platbaString);
    }

    private void readAll() {
        ObjednavkaDao objednavkaDao = CampingDaoFactory.INSTANCE.getMySqlObjednavkaDao();
        List<ObjednavkaFxModel> listObjednavok = objednavkaDao.getAll();
        for (ObjednavkaFxModel objednavka : listObjednavok) {
            objednavky.add(objednavka);

        }
    }
}
