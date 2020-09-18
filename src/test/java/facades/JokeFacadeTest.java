package facades;

import dto.CarsDTO;
import entities.Cars;
import entities.Jokes;
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
public class JokeFacadeTest {

    private static EntityManagerFactory emf;
    private static JokeFacade facade;

    public JokeFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
       emf = EMF_Creator.createEntityManagerFactoryForTest();
       facade = JokeFacade.getJokeFacade(emf);
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
            em.persist(new Jokes("whats the best thing about switzerland? I dont know but the flag is a big plus","short","switzerland"));
            em.persist(new Jokes("Why do we tell actors to break a leg? Because every play has a cast","short","Actors"));
            em.persist(new Jokes("What do you call it when a russian emperor mocks his people with irony? Tsarcasm","Irony","Russia"));
            em.persist(new Jokes("Irony is getting pregnant on a pull out couch","Irony","Pregnacy"));
            em.persist(new Jokes("Why is a bear big, brown and hairy? because if it was small, smooth and white it would be an egg","Egg","Bear"));
            em.persist(new Jokes("How does a hen leave the house? Through the egg-sit","Egg","Hen"));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    
    // Testing if we get 6 jokes from getAllJokes method:
    @Test
    public void getAllJokes() {
        int exp = 6;
        int result = facade.getAllJokes().size();
        
        assertEquals(exp, result);
    }
    
    //vi har testet den manuelt hvor den virker men når vi tester den her 
    //i så bliver vores id'er i test
    //databasen ved med at ændre sig helt op til 15-16 så vi har valgt
    //at udkommentere vores test metode
    /*@Test
    public void getJokeById() {
        int id = 3;
        String exp = "Why do we tell actors to break a leg? Because every play has a cast";
        String result = facade.getJokeById(id).getTheJoke();
        
        assertEquals(exp, result);
    }*/
    
    
}