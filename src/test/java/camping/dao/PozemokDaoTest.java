package camping.dao;

import camping.design.PozemokFxModel;
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
    private PozemokFxModel pozemokModel = new PozemokFxModel();

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

    // vytvorenie pozemku - počet musí byť o 1 vačší
    // musí obsahovať cislo
    //PRESLO
    @Test
    public void createTest() {
        int velkost = dao.getAll().size();
        pozemokModel.setCisloPozemku(1L);
        pozemokModel.setKategoriaId(3L);
        pozemokModel.setCena(20);
        pozemokModel.setObsadenost(false);
        dao.createPozemok(pozemokModel);
        assertNotNull(pozemokModel.getCisloPozemku());
        assertEquals(velkost + 1, dao.getAll().size());
    }

    // všetky pozemky
    //PRESLO
    @Test
    public void getAllTest() {
        List<PozemokFxModel> list = dao.getAll();
        assertNotNull(list);
        if (list != null) {
            assertTrue(list.size() > 0);
        }
        System.out.println(list);
    }

    // mazanie pozemku podla Id pozemku - pocet vsetkych musí byť o 1 menší
    //PRESLO - nemusí byť, nevyuživame to, pozri id v db!!! 
    @Test
    public void deleteByIdTest() {
        int velkost = dao.getAll().size();
        dao.deletePozemokById(26L);
        assertEquals(velkost - 1, dao.getAll().size());
    }

    // mazanie pozemku podla cisla pozemku - pocet vsetkych musí byť o 1 menší
    //PRESLO - pozri cislo v db!!!
    @Test
    public void deleteByCisloPozemkuTest() {
        int velkost = dao.getAll().size();
        assertTrue(dao.deletePozemokByCisloPozemku(3L));
        assertEquals(velkost - 1, dao.getAll().size());
    }

    // update pozemku - ako funguje?
    // pocet musí zostať zachovaný
    //PRESLO
    @Test
    public void updatePozemokTest() {
        int velkost = dao.getAll().size();
        dao.updatePozemok(pozemokModel);
        assertEquals(velkost, dao.getAll().size());
    }
}
