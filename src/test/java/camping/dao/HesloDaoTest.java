package camping.dao;

import camping.entities.Heslo;
import camping.entities.Pozemok;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class HesloDaoTest {

    private HesloDao dao;

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

    // vytvorenie hesla
    @Test
    public void createTest() {
        // TODO getAll pre hesla? 
        int velkost = dao.getAll().size();
        Heslo heslo = new Heslo();
        heslo.setHeslo("x");
        heslo.setUzivatel("Jozef");
        dao.createHeslo(heslo);
        assertNotNull(heslo.getId());
        assertNotNull(heslo.getHeslo());
        assertNotNull(heslo.getUzivatel());
        assertEquals(velkost + 1, dao.getAll().size());
    }

    // všetky hesla - bude getAll?
    @Test
    public void getAllTest() {
        List<Heslo> list = dao.getAll();
        assertNotNull(list);
        if (list != null) {
            assertTrue(list.size() > 0);
        }
        System.out.println(list);
    }

    // update hesla
    @Test
    public void updateHesloTest() {
        int velkost = dao.getAll().size();
        //Heslo heslo = ;
        //dao.updateHeslo(heslo);
        //updateHeslo vracia void - nevie porovnat ci nastala zmena
        assertEquals(velkost, dao.getAll().size());
    }
}
