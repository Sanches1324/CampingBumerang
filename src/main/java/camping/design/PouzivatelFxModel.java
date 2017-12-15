package camping.design;

import camping.entities.Pouzivatel;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PouzivatelFxModel {

    //id som nedávala
    private StringProperty pozicia = new SimpleStringProperty();
    private StringProperty meno = new SimpleStringProperty();
    private IntegerProperty odrobeneHodiny = new SimpleIntegerProperty();
    private IntegerProperty vyplata = new SimpleIntegerProperty();

    public PouzivatelFxModel() {
   
    }

    public Pouzivatel gePouzivatel() {
        Pouzivatel pouzivatel = new Pouzivatel();
        pouzivatel.setPozicia(getPozicia());
        pouzivatel.setMeno(getMeno());
        pouzivatel.setPocet_odrobenych_hodin(getOdrobeneHodiny());
        pouzivatel.setVyplata(getVyplata());
        return pouzivatel;
    }

    //getery, setery a property
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

}
