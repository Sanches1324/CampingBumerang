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
    //PRESLO
    @Test
    public void createTest() {
        int velkost = dao.getAll().size();
        objednavkaModel.setDatumObjednavky(LocalDate.now());
        objednavkaModel.setDatumOdchodu(LocalDate.now());
        objednavkaModel.setDatumPrichodu(LocalDate.now());
        objednavkaModel.setPocetDni(1L);
        objednavkaModel.setMenoPouziatela("Kyblik");
        objednavkaModel.setMenoZakaznika("Anton Hlavička");
        objednavkaModel.setPlatba(false);
        objednavkaModel.setTelCisloZakaznika("0941654522");
        objednavkaModel.setPozemokId(8L);
        objednavkaModel.setPouzivatelId(5L);
        dao.createObjednavku(objednavkaModel);
        assertEquals(velkost + 1, dao.getAll().size());
    }

    // všetky objednávky
    //PRESLO
    @Test
    public void getAllTest() {
        List<ObjednavkaFxModel> list = dao.getAll();
        if (list != null) {
            assertTrue(list.size() > 0);
        }
        System.out.println(list);
    }

    // mazanie objednávky podla id, pocet vsetkych musi byt o 1 mensi
    //PRESLO - pozri ake id mas este v db, ak tam nie je bude chybne!!!
    @Test
    public void deleteByIdTest() {
        int velkost = dao.getAll().size();
        dao.deleteObjednavku(8L);
        assertEquals(velkost - 1, dao.getAll().size());
    }

    // update objednávky - ako funguje? 
    // pocet musí byť rovnaký
    //NEPRESLO
    @Test
    public void updateTest() {
        int velkost = dao.getAll().size();
        dao.updateObjednavku(objednavkaModel);
        assertEquals(velkost, dao.getAll().size());
    }
}
