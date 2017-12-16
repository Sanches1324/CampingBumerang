package camping.dao;

import camping.design.ObjednavkaFxModel;
import java.time.LocalDate;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ObjednavkaDaoTest {

    private ObjednavkaDao dao;
    private ObjednavkaFxModel objednavkaModel = new ObjednavkaFxModel();

    public ObjednavkaDaoTest() {
        dao = CampingDaoFactoryTest.INSTANCE.getMySqlObjednavkaDao();
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

    // vytvorenie objednávky, pocet vsetkych musi byt o 1 vacsi
    // musi obsahovat datumy a idcka pozemku a pouzivatela
    @Test
    public void createTest() {
        int velkost = dao.getAll().size();
        objednavkaModel.setDatumObjednavky(LocalDate.now());
        objednavkaModel.setDatumPrichodu(LocalDate.now());
        objednavkaModel.setDatumOdchodu(LocalDate.now());
        objednavkaModel.setPocetDni(1L);
        objednavkaModel.setPozemokId(1L);
        objednavkaModel.setPouzivatelId(1L);
        objednavkaModel.setPlatba(false);
        dao.createObjednavku(objednavkaModel);
        assertNotNull(objednavkaModel.getDatumObjednavky());
        assertNotNull(objednavkaModel.getDatumPrichodu());
        assertNotNull(objednavkaModel.getDatumOdchodu());
        assertNotNull(objednavkaModel.getPozemokId());
        assertNotNull(objednavkaModel.getPouzivatelId());
        assertEquals(velkost + 1, dao.getAll().size());
    }

    // všetky objednávky
    @Test
    public void getAllTest() {
        List<ObjednavkaFxModel> list = dao.getAll();
        assertNotNull(list);
        if (list != null) {
            assertTrue(list.size() > 0);
        }
        System.out.println(list);
    }

    // mazanie objednávky podla id, pocet vsetkych musi byt o 1 mensi
    @Test
    public void deleteByIdTest() {
        int velkost = dao.getAll().size();
        dao.deleteObjednavku(objednavkaModel.getId());
        assertEquals(velkost - 1, dao.getAll().size());
    }

    // update objednávky - ako funguje? 
    // pocet musí byť rovnaký
    @Test
    public void updateTest() {
        int velkost = dao.getAll().size();
        dao.updateObjednavku(objednavkaModel);
        assertEquals(velkost, dao.getAll().size());
    }
}
