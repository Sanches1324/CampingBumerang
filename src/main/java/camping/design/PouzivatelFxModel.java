package camping.design;

import camping.dao.CampingDaoFactory;
import camping.dao.PouzivatelDao;
import camping.entities.Pouzivatel;
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

public class PouzivatelFxModel {

    private LongProperty id;
    private StringProperty pozicia;
    private StringProperty meno;
    private StringProperty prihl_meno;
    private ObjectProperty<LocalDate> datumNarodenia;
    private StringProperty adresa;
    private StringProperty tel_cislo;
    private StringProperty e_mail;
    private StringProperty povoleny;
    private ObservableList<PouzivatelFxModel> pouzivatelia;
    private StringProperty heslo;
    private BooleanProperty prihlasenie;

    public PouzivatelFxModel() {
        this.id = new SimpleLongProperty();
        this.pozicia = new SimpleStringProperty();
        this.meno = new SimpleStringProperty();
        this.prihl_meno = new SimpleStringProperty();
        this.datumNarodenia = new SimpleObjectProperty<>();
        this.adresa = new SimpleStringProperty();
        this.tel_cislo = new SimpleStringProperty();
        this.e_mail = new SimpleStringProperty();
        this.povoleny = new SimpleStringProperty();
        this.pouzivatelia = FXCollections.observableArrayList();
        this.heslo = new SimpleStringProperty();
        this.prihlasenie = new SimpleBooleanProperty();
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

    public StringProperty prihl_menoProperty() {
        return prihl_meno;
    }

    public String getPrihl_Meno() {
        return prihl_meno.get();
    }

    public void setPrihl_Meno(String prihl_meno) {
        this.prihl_meno.set(prihl_meno);
    }

    public ObjectProperty<LocalDate> datumNarodeniaProperty() {
        return datumNarodenia;
    }

    public LocalDate getDatumNarodenia() {
        return datumNarodenia.get();
    }

    public void setDatumNarodenia(LocalDate datumNarodenia) {
        this.datumNarodenia.set(datumNarodenia);
    }

    public StringProperty adresaProperty() {
        return adresa;
    }

    public String getAdresa() {
        return adresa.get();
    }

    public void setAdresa(String adresa) {
        this.adresa.set(adresa);
    }

    public StringProperty tel_cisloProperty() {
        return tel_cislo;
    }

    public String getTel_cislo() {
        return tel_cislo.get();
    }

    public void setTel_cislo(String tel_cislo) {
        this.tel_cislo.set(tel_cislo);
    }

    public StringProperty e_mailProperty() {
        return e_mail;
    }

    public String getE_mail() {
        return e_mail.get();
    }

    public void setE_mail(String e_mail) {
        this.e_mail.set(e_mail);
    }

    public StringProperty povolenyProperty() {
        return povoleny;
    }

    public String getPovoleny() {
        return povoleny.get();
    }

    public void setPovoleny(String povoleny) {
        this.povoleny.set(povoleny);
    }

    public ObservableList<PouzivatelFxModel> getPouzivatelov() {
        readAll();
        return pouzivatelia;
    }

    public void setPouzivatelov(ObservableList<PouzivatelFxModel> pouzivatelia) {
        this.pouzivatelia = pouzivatelia;
    }

    public StringProperty hesloProperty() {
        return heslo;
    }

    public String getHeslo() {
        return heslo.get();
    }

    public void setHeslo(String heslo) {
        this.heslo.set(heslo);
    }

    public BooleanProperty prihlasenieProperty() {
        return prihlasenie;
    }

    public Boolean getPrihlasenie() {
        return prihlasenie.get();
    }

    public void setPrihlasenie(Boolean prihlasenie) {
        this.prihlasenie.set(prihlasenie);
    }

    private void readAll() {
        PouzivatelDao pouzivatelDao = CampingDaoFactory.INSTANCE.getMySqlPouzivatelDao();
        List<PouzivatelFxModel> listPouzivatelov = pouzivatelDao.getAll();
        for (PouzivatelFxModel pouzivatel : listPouzivatelov) {
            pouzivatelia.add(pouzivatel);
        }
    }

    @Override
    public String toString() {
        return meno + ""; //To change body of generated methods, choose Tools | Templates.
    }

}
