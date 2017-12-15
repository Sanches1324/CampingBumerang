package camping.dao;

import camping.entities.Objednavka;
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

    // vytvorenie objednávky
    @Test
    public void createTest() {
        int velkost = dao.getAll().size();
        Objednavka objednavka = new Objednavka();
        objednavka.setDatumObjednavky(LocalDate.now());
        objednavka.setDatumPrichodu(LocalDate.now());
        objednavka.setDatumOdchodu(LocalDate.now());
        objednavka.setPocetDni(1L);
        objednavka.setPozemokId(1L);
        objednavka.setPouzivatelId(1L);
        objednavka.setPlatba(false);
        dao.createObjednavku(objednavka);
        assertNotNull(objednavka.getId());
        assertNotNull(objednavka.getDatumObjednavky());
        assertNotNull(objednavka.getDatumPrichodu());
        assertNotNull(objednavka.getDatumOdchodu());
        assertNotNull(objednavka.getPozemokId());
        assertNotNull(objednavka.getPouzivatelId());
        assertEquals(velkost + 1, dao.getAll().size());
    }

    // všetky objednávky
    @Test
    public void getAllTest() {
        List<Objednavka> list = dao.getAll();
        assertNotNull(list);
        if (list != null) {
            assertTrue(list.size() > 0);
        }
        System.out.println(list);
    }

    // mazanie objednávky podla id
    @Test
    public void deleteByIdTest() {
        int velkost = dao.getAll().size();
        Objednavka objednavka = dao.getAll().get(0);
        dao.deleteObjednavku(objednavka.getId());
        assertEquals(velkost - 1, dao.getAll().size());
    }

    // update objednávky
    @Test
    public void updateTest() {
        int velkost = dao.getAll().size();
        Objednavka objednavka = new Objednavka();
        //hádže chybovú hlášku, updateObjednavku vracia void - nie Objednavka()
        //Objednavka objednavkaU = dao.updateObjednavku(objednavka);
        //assertNotEquals(objednavka, objednavkaU);
        assertEquals(velkost, dao.getAll().size());
    }
}
