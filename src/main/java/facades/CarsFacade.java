package facades;

import dto.CarsDTO;
import entities.Cars;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class CarsFacade {

    private static CarsFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private CarsFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static CarsFacade getCarsFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CarsFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    //TODO Remove/Change this before use
    public long getCarsCount() {
        EntityManager em = emf.createEntityManager();
        try {
            long carsCount = (long) em.createQuery("SELECT COUNT(c) FROM Cars c").getSingleResult();
            return carsCount;
        } finally {
            em.close();
        }

    }

    public void populateDB() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Cars.deleteAllRows").executeUpdate();
            em.persist(new Cars("Tesla", 2020, "Model X", 779800, 28));
            em.persist(new Cars("BMW", 2014, "i8", 857000, 5));
            em.persist(new Cars("Audi", 2020, "R8 4,2 FSi Spyder quattro", 1279900, 1));
            em.persist(new Cars("Audi", 2010, "A8", 1655662, 15));
            em.persist(new Cars("Sedan", 2020, "Camry", 158750, 6));
            em.persist(new Cars("Volvo", 2020, "V70", 1152285, 5));
            em.persist(new Cars("Volvo", 2020, "XC90", 1152285, 70));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    public List<CarsDTO> getCarsByManufactor(String manufacturer) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Cars> query
                    = em.createQuery("Select c from Cars c where c.manufacturer = :manufacturer", Cars.class);
            query.setParameter("manufacturer", manufacturer);
            List<Cars> cars = query.getResultList();
            List<CarsDTO> carsdto = new ArrayList<>();
            for (Cars c : cars) {
                carsdto.add(new CarsDTO(c));
            }
            return carsdto;
        } finally {
            em.close();
        }
    }

    public List<CarsDTO> getAllCars() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Cars> query = em.createQuery("Select c from Cars c order by c.manufacturer asc", Cars.class);
            List<Cars> cars = query.getResultList();
            List<CarsDTO> carsdto = new ArrayList<>();
            for (Cars c : cars) {
                carsdto.add(new CarsDTO(c));
            }
            return carsdto;
        } finally {
            em.close();
        }
    }
    
    public void addCar(Cars cars) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(cars);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

}
