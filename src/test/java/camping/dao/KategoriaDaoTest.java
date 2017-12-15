package camping.dao;

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

    // vytvorenie kategorie
    @Test
    public void createTest() {
        int velkost = dao.getAll().size();
        Kategoria kategoria = new Kategoria();
        kategoria.setNazov("A");
        dao.createKategoria(kategoria);
        assertNotNull(kategoria.getId());
        assertNotNull(kategoria.getNazov());
        assertEquals(velkost + 1, dao.getAll().size());
    }

    // všetky kategorie
    @Test
    public void getAllTest() {
        List<Kategoria> list = dao.getAll();
        assertNotNull(list);
        if (list != null) {
            assertTrue(list.size() > 0);
        }
        System.out.println(list);
    }

    // mazanie kategorie podla id
    @Test
    public void deleteByIdTest() {
        int velkost = dao.getAll().size();
        Kategoria kategoria = dao.getAll().get(0);
        dao.deleteKategoriaById(kategoria.getId());
        assertEquals(velkost - 1, dao.getAll().size());
    }
}
