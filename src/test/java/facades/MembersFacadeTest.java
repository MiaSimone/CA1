package facades;

import utils.EMF_Creator;
import entities.Members;
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
public class MembersFacadeTest {

    private static EntityManagerFactory emf;
    private static MembersFacade facade;

    public MembersFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
       emf = EMF_Creator.createEntityManagerFactoryForTest();
       facade = MembersFacade.getMembersFacade(emf);
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
            em.createNamedQuery("Members.deleteAllRows").executeUpdate();
            em.persist(new Members("Mia de Fries", 291, "Blue"));
            em.persist(new Members("Klaus Pedersen", 666, "Pink"));

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    // Testing if there is 2 members in the database:
    @Test
    public void testAFacadeMethod() {
        assertEquals(2, facade.getMembersCount(), "Expects two rows in the database");
    }
    
    // Testing if we get 2 members from getAllMembers method:
    @Test
    public void getAllMembers() {
        int exp = 2;
        int result = facade.getAllMembers().size();
        
        assertEquals(exp, result);
    }

}
