package facades;

import dto.JokeDTO;
import entities.Jokes;
import static java.lang.Math.random;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;




/**
 *
 * @author Rasmus
 */
public class JokeFacade {
    
    private static JokeFacade instance;
    private static EntityManagerFactory emf;
    
    private JokeFacade() {
    }
    
    public static JokeFacade getJokeFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new JokeFacade();
        }
        return instance;
    }
    
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    
    public void populateDBJokes() {
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
    
    public List<JokeDTO> getAllJokes() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Jokes> query = em.createQuery("Select j from Jokes j order by j.theJoke asc", Jokes.class);
            List<Jokes> jokes = query.getResultList();
            List<JokeDTO> jokesdto = new ArrayList<>();
            for (Jokes j : jokes) {
                jokesdto.add(new JokeDTO(j));
            }
            return jokesdto;
        } finally {
            em.close();
        }
    }
    
    public Jokes getJokeById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Jokes> query = em.createQuery("Select j from Jokes j where j.id = :id", Jokes.class);
            query.setParameter("id", id);
            Jokes joke = query.getSingleResult();
            return joke;
        }
        finally {
            em.close();
        }
        
        
    }
    public Jokes getRandomJoke() {
        EntityManager em = emf.createEntityManager();
        Random random = new Random();
        try {
            
            int randomRow = random.nextInt()%6;
            TypedQuery<Jokes> query = em.createQuery("Select j from Jokes j", Jokes.class);
            query.setFirstResult(randomRow);
            query.setMaxResults(1);
            Jokes joke = query.getSingleResult();
            return joke;
            
        }
        finally {
            em.close();
        }
        
    }

    
}
