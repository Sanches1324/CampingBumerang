package camping.dao;

import camping.design.KategoriaFxModel;
import camping.entities.Kategoria;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class KategoriaDaoTest {

    private KategoriaDao dao;
    private KategoriaFxModel kategoriaModel = new KategoriaFxModel();

    public KategoriaDaoTest() {
        dao = CampingDaoFactoryTest.INSTANCE.getMySqlKategoriaDao();
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    // vytvorenie kategorie, pocet vsetkych ma byt o 1 vacsi
    // musi obsahovat nazov
    //PRESLO - nesmie byt rovnaka kategoria aku uz mas v db!!! inak je chybne
    @Test
    public void createTest() {
        int velkost = dao.getAll().size();
        kategoriaModel.setNazov("VI");
        dao.createKategoria(kategoriaModel);
        assertEquals(velkost + 1, dao.getAll().size());
    }

    // všetky kategorie
    //PRESLO
    @Test
    public void getAllTest() {
        List<KategoriaFxModel> list = dao.getAll();
        if (list != null) {
            assertTrue(list.size() > 0);
        }
    }

    // update kategorie - ako funguje? 
    // pocet musí byť rovnaký
    //PRESLO
    @Test
    public void updateTest() {
        int velkost = dao.getAll().size();
        dao.updateKategoriu(kategoriaModel);
        assertEquals(velkost, dao.getAll().size());
    }

    // mazanie kategorie podla id, pocet vsetkych musi byt mensi o 1
    //PRESLO - musis mat id v db - inak nezbehne! 
    @Test
    public void deleteByIdTest() {
        int velkost = dao.getAll().size();
        dao.deleteKategoriaById(8L);
        assertEquals(velkost - 1, dao.getAll().size());
    }
}
