package camping.dao;

import camping.design.KategoriaFxModel;
import camping.design.PozemokFxModel;
import java.util.List;

public interface PozemokDao {

    public void createPozemok(PozemokFxModel pozemky);

    public List<PozemokFxModel> getAll();

    public void updatePozemok(PozemokFxModel pozemok);

    public boolean deletePozemokByCisloPozemku(long cisloPozemku);

    public boolean deletePozemokById(long id);

    public PozemokFxModel findById(long id);

    public PozemokFxModel findByCisloPozemku(long cisloPozemku);

    public List<PozemokFxModel> findByCena(int cena);

    public List<PozemokFxModel> findByObsadenost(String obsadenost);

}
