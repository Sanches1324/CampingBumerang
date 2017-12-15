package camping.dao;

import camping.design.HesloFxModel;
import camping.entities.Heslo;
import java.util.List;

public interface HesloDao {

    public void createHeslo(HesloFxModel heslo);

    public void updateHeslo(HesloFxModel heslo);

    public List<HesloFxModel> getAll();

    public HesloFxModel findByUzivatel(String uzivatel);

    public boolean deleteHesloById(long id);

}
