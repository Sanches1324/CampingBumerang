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
    //PRESLO
    @Test
    public void createTest() {
        int velkost = dao.getAll().size();
        hesloModel = new HesloFxModel();
        hesloModel.setUzivatel("Jozef");
        hesloModel.setHeslo("heslo");
        dao.createHeslo(hesloModel);
        assertNotNull(hesloModel.getUzivatel());
        assertNotNull(hesloModel.getHeslo());
        assertEquals(velkost + 1, dao.getAll().size());
    }

    // všetky hesla
    //PRESLO
    @Test
    public void getAllTest() {
        List<HesloFxModel> list = dao.getAll();
        if (list != null) {
            assertTrue(list.size() > 0);
        }
    }

    // TODO: update hesla-ako funguje?  
    // počet musí byť rovnaký
    //NEPRESLO
    @Test
    public void updateHesloTest() {
        int velkost = dao.getAll().size();
        HesloFxModel noveHeslo = new HesloFxModel();
        noveHeslo.setUzivatel(hesloModel.getUzivatel());
        noveHeslo.setHeslo("noveHeslo");
        dao.updateHeslo(noveHeslo);
        assertEquals(velkost, dao.getAll().size());
    }
}
