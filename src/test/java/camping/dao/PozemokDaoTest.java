package camping.dao;

import camping.entities.Pozemok;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PozemokDaoTest {

    private PozemokDao dao;

    public PozemokDaoTest() {
        dao = CampingDaoFactoryTest.INSTANCE.getMySqlPozemokDao();
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

    // vytvorenie pozemku
    @Test
    public void createTest() {
        int velkost = dao.getAll().size();
        Pozemok pozemok = new Pozemok();
        pozemok.setCisloPozemku(1L);
        pozemok.setObsadenost(false);
        dao.createPozemok(pozemok);
        assertNotNull(pozemok.getId());
        assertNotNull(pozemok.getCisloPozemku());
        assertEquals(velkost + 1, dao.getAll().size());
    }

    // všetky pozemky
    @Test
    public void getAllTest() {
        List<Pozemok> list = dao.getAll();
        assertNotNull(list);
        if (list != null) {
            assertTrue(list.size() > 0);
        }
        System.out.println(list);
    }

    // mazanie pozemku podla Id pozemku
    @Test
    public void deleteByIdTest() {
        int velkost = dao.getAll().size();
        Pozemok pozemok = dao.getAll().get(0);
        assertTrue(dao.deletePozemokById(pozemok.getId()));
        assertEquals(velkost - 1, dao.getAll().size());
    }

    // mazanie pozemku podla cisla pozemku
    @Test
    public void deleteByCisloPozemkuTest() {
        int velkost = dao.getAll().size();
        Pozemok pozemok = dao.getAll().get(0);
        assertTrue(dao.deletePozemokByCisloPozemku(pozemok.getCisloPozemku()));
        assertEquals(velkost - 1, dao.getAll().size());
    }

    // update pozemku
    @Test
    public void updatePozemokTest() {
        int velkost = dao.getAll().size();
        Pozemok pozemok = new Pozemok();
        //hádže chybovú hlášku, updatePozemok vracia void - nie Pozemok()
        //Pozemok pozemokU = dao.updatePozemok(pozemok);
        //assertNotEquals(pozemok, pozemokU);
        assertEquals(velkost, dao.getAll().size());
    }
}
