package camping.dao;

import camping.design.ObjednavkaFxModel;
import camping.entities.Objednavka;
import java.time.LocalDate;
import java.util.List;

public interface ObjednavkaDao {

    public void createObjednavku(ObjednavkaFxModel objednavka);

    public List<ObjednavkaFxModel> getAll();

    public void updateObjednavku(ObjednavkaFxModel objednavka);

    public boolean deleteObjednavku(Long id);

    public List<ObjednavkaFxModel> findByPozemokId(Long pozemok_id);

    public List<ObjednavkaFxModel> findByPouzivatelId(Long pouzivatel_id);

    public List<ObjednavkaFxModel> findByDatumObjednavky(LocalDate datumObjednavky);

    public List<ObjednavkaFxModel> findByDatumPrichodu(LocalDate datumPrichodu);

    public List<ObjednavkaFxModel> findByDatumOdchodu(LocalDate datumOdchodu);

    public List<ObjednavkaFxModel> findByPocetDni(int pocetDni);

    public List<ObjednavkaFxModel> findByPlatba(boolean platba);

}
