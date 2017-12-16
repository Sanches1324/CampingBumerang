package camping.dao;

import camping.entities.Pouzivatel;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PouzivatelDaoTest {

    private PouzivatelDao dao;
    private Pouzivatel pouzivatel = new Pouzivatel();

    public PouzivatelDaoTest() {
        dao = CampingDaoFactoryTest.INSTANCE.getMySqlPouzivatelDao();
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

    // vytvorenie pouzivatela - pocet vsetkych o 1 vacsi
    // musi obsahovat poziciu
    @Test
    public void createTest() {
        int velkost = dao.getAll().size();
        pouzivatel.setMeno("Zamestnanec1");
        pouzivatel.setPozicia("Zamestnanec");
        dao.createPouzivatela(pouzivatel);
        assertNotNull(pouzivatel.getPozicia());
        assertEquals(velkost + 1, dao.getAll().size());
    }

    // všetci používatelia
    @Test
    public void getAllTest() {
        List<Pouzivatel> list = dao.getAll();
        assertNotNull(list);
        if (list != null) {
            assertTrue(list.size() > 0);
        }
        System.out.println(list);
    }

    // mazanie používateľa podla id - pocet vsetkych musi byt o 1 mensi
    @Test
    public void deleteByIdTest() {
        int velkost = dao.getAll().size();
        dao.deletePouzivatela(pouzivatel.getId());
        assertEquals(velkost - 1, dao.getAll().size());
    }

    // update používateľa - ako funguje? 
    // pocet musi byt zachovany
    @Test
    public void updateTest() {
        int velkost = dao.getAll().size();
        dao.updatePouzivatela(pouzivatel);
        assertEquals(velkost, dao.getAll().size());
    }
}
