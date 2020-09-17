package facades;

import dto.CarsDTO;
import entities.Cars;
import utils.EMF_Creator;
import entities.Members;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class CarsFacadeTest {

    private static EntityManagerFactory emf;
    private static CarsFacade facade;

    public CarsFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
       emf = EMF_Creator.createEntityManagerFactoryForTest();
       facade = CarsFacade.getCarsFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the script below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Cars.deleteAllRows").executeUpdate();
            
            em.persist(new Cars("Tesla", 2020, "Model X", 779800, 28));
            em.persist(new Cars("BMW", 2014, "i8", 857000, 5));
            em.persist(new Cars("Audi", 2020, "R8 4,2 FSi Spyder quattro", 1279900, 1));
            
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    // TODO: Delete or change this method 
    @Test
    public void testAFacadeMethod() {
        assertEquals(3, facade.getCarsCount(), "Expects 3 rows in the database");
    }

    
    @Test
    public void getCarsByManufactor() {
        String m = "BMW";
        String exp = "BMW";
        List<CarsDTO> resultList = facade.getCarsByManufactor(m);
        String result = resultList.get(0).getManufacturer();
        assertEquals(exp, result);
    }
    
    // Testing if we get 3 cars from getAllCars method:
    @Test
    public void getAllCars() {
        int exp = 3;
        int result = facade.getAllCars().size();
        
        assertEquals(exp, result);
    }
    
    @Test
    public void addCar() {
        facade.addCar(new Cars("Sedan", 2020, "Camry", 158750, 6));
        
        int exp = 4;
        int result = facade.getAllCars().size();
        
        assertEquals(exp, result);
    }
}
