package si.fri.rso.sk19.services.beans;

import org.eclipse.microprofile.metrics.Counter;
import org.eclipse.microprofile.metrics.annotation.Metric;
import si.fri.rso.sk19.models.entities.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class AdminBean {
    @PersistenceContext
    private EntityManager em;

    @Inject
    @Metric(name="usersInDB")
    private Counter userCounter;

    @Inject
    @Metric(name="usersDbCall")
    private Counter dbCounter;

    private Logger log = Logger.getLogger(AdminBean.class.getName());

    public List<User> getAllUsers(){
        dbCounter.inc();
        TypedQuery<User> query = em.createNamedQuery("User.getAll", User.class);
        return query.getResultList();
    }

    public boolean deleteUser(Integer user_id){

        User user = em.find(User.class, user_id);
        dbCounter.inc();
        if (user != null){
            try{
                beginTx();
                em.remove(user);
                commitTx();
            } catch(Exception e){
                rollbackTx();
            }
        } else{
            return false;
        }
        userCounter.dec();
        return true;
    }

    private void beginTx() {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
    }

    private void commitTx() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().commit();
        }
    }

    private void rollbackTx() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
    }
}
