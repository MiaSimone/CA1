package facades;

import dto.JokeDTO;
import entities.Joke;
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
    public long getJokeCount() {
        EntityManager em = emf.createEntityManager();
        try {
            long jokeCount = (long) em.createQuery("SELECT COUNT(j) FROM Joke j").getSingleResult();
            return jokeCount;
        }
        finally {
            em.close();
        }
    }
    
    public void populateDB() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(new Joke("whats the best thing about switzerland? I dont know but the flag is a big plus","short","switzerland"));
            em.persist(new Joke("Why do we tell actors to break a leg? Because every play has a cast","short","Actors"));
            em.persist(new Joke("What do you call it when a russian emperor mocks his people with irony? Tsarcasm","Irony","Russia"));
            em.persist(new Joke("Irony is getting pregnant on a pull out couch","Irony","Pregnacy"));
            em.persist(new Joke("Why is a bear big, brown and hairy? because if it was small, smooth and white it would be an egg","Egg","Bear"));
            em.persist(new Joke("How does a hen leave the house? Through the egg-sit","Egg","Hen"));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    public List<JokeDTO> getAllJoke() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Joke> query = em.createQuery("Select j from Joke j order by j.theJoke asc", Joke.class);
            List<Joke> joke = query.getResultList();
            List<JokeDTO> jokeDTO = new ArrayList<>();
            for (Joke j : joke) {
                jokeDTO.add(new JokeDTO(j));
            }
            return jokeDTO;
        } finally {
            em.close();
        }
    }
    public Joke getJokeById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Joke> query = em.createQuery("Select j from Joke j where j.id = :id", Joke.class);
            query.setParameter("id", id);
            Joke joke = query.getSingleResult();
            return joke;
        }
        finally {
            em.close();
        }
        
        
    }
    public Joke getRandomJoke() {
        EntityManager em = emf.createEntityManager();
        Random random = new Random();
        try {
            
            int randomRow = random.nextInt()%6;
            TypedQuery<Joke> query = em.createQuery("Select j from Joke j", Joke.class);
            query.setFirstResult(randomRow);
            query.setMaxResults(1);
            Joke joke = query.getSingleResult();
            return joke;
            
        }
        finally {
            em.close();
        }
        
    }

    
}
