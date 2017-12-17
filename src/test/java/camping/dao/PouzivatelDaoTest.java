package camping.dao;

import camping.design.PouzivatelFxModel;
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
    private PouzivatelFxModel pouzivatel = new PouzivatelFxModel();

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
    //NEPRESLO - realne ho v db vytvori
    @Test
    public void createTest() {
        int velkost = dao.getAll().size();
        PouzivatelFxModel novy = new PouzivatelFxModel();
        novy.setMeno("Zamestnanec2");
        novy.setPozicia("Zamestnanec");
        novy.setVyplata(60);
        novy.setOdrobeneHodiny(7);
        dao.createPouzivatela(novy);
        assertEquals(velkost + 1, dao.getAll().size());
    }

    // všetci používatelia
    //PRESLO
    @Test
    public void getAllTest() {
        List<PouzivatelFxModel> list = dao.getAll();
        assertNotNull(list);
        if (list != null) {
            assertTrue(list.size() > 0);
        }
        System.out.println(list);
    }

    /*// mazanie používateľa podla id - pocet vsetkych musi byt o 1 mensi
    @Test
    public void deleteByIdTest() {
        int velkost = dao.getAll().size();
        dao.deletePouzivatela(pouzivatel);
        assertEquals(velkost - 1, dao.getAll().size());
    }*/
    // update používateľa - ako funguje? 
    // pocet musi byt zachovany
    //NEPRESLO
    @Test
    public void updateTest() {
        int velkost = dao.getAll().size();
        PouzivatelFxModel novy = new PouzivatelFxModel();
        novy.setMeno("Kris");
        novy.setPozicia("boss");
        novy.setVyplata(60);
        novy.setOdrobeneHodiny(7);
        dao.updatePouzivatela(novy);
        assertEquals(velkost, dao.getAll().size());
    }
}
