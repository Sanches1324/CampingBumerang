package camping.dao;

import camping.design.HesloFxModel;
import camping.entities.Heslo;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class HesloDaoTest {

    private HesloDao dao;
    private HesloFxModel hesloModel = new HesloFxModel();

    public HesloDaoTest() {
        dao = CampingDaoFactoryTest.INSTANCE.getMySqlHesloDao();
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

    // vytvorenie hesla - chcem aby celkovy pocet hesiel bol vacsi o jedna
    // heslo a meno uzivatela nesmie byť prazdne
    @Test
    public void createTest() {
        int velkost = dao.getAll().size();
        HesloFxModel heslo = new HesloFxModel();
        heslo.setHeslo("x");
        heslo.setUzivatel("Jozef");
        dao.createHeslo(hesloModel);
        assertNotNull(hesloModel.getHeslo());
        assertNotNull(hesloModel.getUzivatel());
        assertEquals(velkost + 1, dao.getAll().size());
    }

    // všetky hesla
    @Test
    public void getAllTest() {
        List<HesloFxModel> list = dao.getAll();
        assertNotNull(list);
        if (list != null) {
            assertTrue(list.size() > 0);
        }
        System.out.println(list);
    }

    // TODO: update hesla-ako funguje?  
    // počet musí byť rovnaký
    @Test
    public void updateHesloTest() {
        int velkost = dao.getAll().size();
        dao.updateHeslo(hesloModel);
        assertEquals(velkost, dao.getAll().size());
    }
}
