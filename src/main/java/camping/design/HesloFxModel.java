package camping.design;

import camping.dao.CampingDaoFactory;
import camping.dao.HesloDao;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class HesloFxModel {

    private StringProperty uzivatel;
    private StringProperty heslo;
    private StringProperty sol;

    public HesloFxModel() {
        uzivatel = new SimpleStringProperty();
        heslo = new SimpleStringProperty();
        sol = new SimpleStringProperty();
    }

    public String getUzivatel() {
        return uzivatel.get();
    }

    public void setUzivatel(String uzivatel) {
        this.uzivatel.set(uzivatel);
    }

    public StringProperty getUzivatelProperty() {
        return uzivatel;
    }

    public String getHeslo() {
        return heslo.get();
    }

    public void setHeslo(String heslo) {
        this.heslo.set(heslo);
    }

    public StringProperty getHesloProperty() {
        return heslo;
    }

    public String getSol() {
        return sol.get();
    }

    public void setSol(String sol) {
        this.sol.set(sol);
    }

    public StringProperty getSolProperty() {
        return sol;
    }
}
