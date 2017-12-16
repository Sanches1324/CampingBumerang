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
    @Test
    public void createTest() {
        int velkost = dao.getAll().size();
        kategoriaModel.setNazov("A");
        dao.createKategoria(kategoriaModel);
        assertNotNull(kategoriaModel.getNazov());
        assertEquals(velkost + 1, dao.getAll().size());
    }

    // všetky kategorie
    @Test
    public void getAllTest() {
        List<KategoriaFxModel> list = dao.getAll();
        assertNotNull(list);
        if (list != null) {
            assertTrue(list.size() > 0);
        }
        System.out.println(list);
    }
    
    // update kategorie - ako funguje? 
    // pocet musí byť rovnaký
    @Test
    public void updateTest() {
        int velkost = dao.getAll().size();
        dao.updateKategoriu(kategoriaModel);
        assertEquals(velkost, dao.getAll().size());
    }

    // mazanie kategorie podla id, pocet vsetkych musi byt mensi o 1
    @Test
    public void deleteByIdTest() {
        int velkost = dao.getAll().size();
        dao.deleteKategoriaById(kategoriaModel.getId());
        assertEquals(velkost - 1, dao.getAll().size());
    }
}
