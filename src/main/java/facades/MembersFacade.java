package facades;

import dto.MembersDTO;
import entities.Members;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class MembersFacade {

    private static MembersFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private MembersFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static MembersFacade getMembersFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MembersFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    //TODO Remove/Change this before use
    public long getMembersCount() {
        EntityManager em = emf.createEntityManager();
        try {
            long membersCount = (long) em.createQuery("SELECT COUNT(m) FROM Members m").getSingleResult();
            return membersCount;
        } finally {
            em.close();
        }

    }

    public void populateDB() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(new Members("Mia de Fries", 291, "Blue"));
            em.persist(new Members("Rasmus Ditlev Hansen", 191, "Pantone Blue"));
            em.persist(new Members("Malthe Stefan Woschek", 202, "Burgundy Red"));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<MembersDTO> getAllMembers() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Members> query = em.createQuery("Select m from Members m order by m.name asc", Members.class);
            List<Members> members = query.getResultList();
            List<MembersDTO> membersdto = new ArrayList<>();
            for (Members m : members) {
                membersdto.add(new MembersDTO(m));
            }
            return membersdto;
        } finally {
            em.close();
        }
    }

}
