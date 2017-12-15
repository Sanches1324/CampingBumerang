package camping.dao;

import camping.design.KategoriaFxModel;
import camping.entities.Kategoria;
import java.util.List;

public interface KategoriaDao {

    public void createKategoria(KategoriaFxModel kategoria);

    public List<KategoriaFxModel> getAll();

    public void updateKategoriu(KategoriaFxModel kategoria);

    public boolean deleteKategoriaById(long id);

    public KategoriaFxModel findById(long id);

    public KategoriaFxModel findByNazov(String kategoria);
}
