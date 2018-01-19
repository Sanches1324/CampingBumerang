package camping.dao;

import camping.design.PouzivatelFxModel;
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
    //PRESLO
    @Test
    public void createTest() {
        int velkost = dao.getAll().size();
        pouzivatel.setMeno("Zamestnanec2");
        pouzivatel.setPozicia("Zamestnanec");
        pouzivatel.setVyplata(60);
        pouzivatel.setOdrobeneHodiny(7);
        dao.createPouzivatela(pouzivatel);
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

    // mazanie používateľa podla mena- pocet vsetkych musi byt o 1 mensi
    //PRESLO
    @Test
    public void deleteByNameTest() {
        int velkost = dao.getAll().size();
        dao.deletePouzivatela("Zamestnanec2");
        assertEquals(velkost - 1, dao.getAll().size());
    }

    // update používateľa - ako funguje? 
    // pocet musi byt zachovany
    //NEPRESLO - nemusí byť kedže update ani nikde v app nerobíme
    @Test
    public void updateTest() {
        int velkost = dao.getAll().size();
        pouzivatel.setMeno("Zamestnanec2");
        pouzivatel.setPozicia("Zamestnanec");
        pouzivatel.setVyplata(70);
        pouzivatel.setOdrobeneHodiny(7);
        dao.updatePouzivatela(pouzivatel);
        assertEquals(velkost, dao.getAll().size());
    }
}
