package camping.dao;

import camping.design.PouzivatelFxModel;
import camping.entities.Pouzivatel;
import java.util.List;

public interface PouzivatelDao {

    public void createPouzivatela(PouzivatelFxModel pouzivatel);

    public List<PouzivatelFxModel> getAll();

    public void updatePouzivatela(PouzivatelFxModel pouzivatel);

    public boolean deletePouzivatela(Long id);

    public List<PouzivatelFxModel> findById(Long id);

    public List<PouzivatelFxModel> findByPozicia(String pozicia);

    public List<PouzivatelFxModel> findByMeno(String meno);

}
