package controladora;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import logica.Servicio;

public class ServicioJpaController implements Serializable {

    private EntityManagerFactory emf = null;

    public ServicioJpaController() {
        this.emf = Persistence.createEntityManagerFactory("SistemaDiscapacidadPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Método para crear (insertar) un nuevo Servicio
    public void create(Servicio servicio) throws Exception {
        EntityManager em = getEntityManager();
        try { 
            em.getTransaction().begin();
            em.persist(servicio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Método para actualizar un Servicio existente
    public void edit(Servicio servicio) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            servicio = em.merge(servicio);
            em.getTransaction().commit();
        } catch (Exception ex) {
            throw new Exception("Error al actualizar el servicio: " + ex.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Método para eliminar un Servicio por ID
    public void destroy(Long id) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Servicio servicio;
            try {
                servicio = em.getReference(Servicio.class, id);
                em.remove(servicio);
            } finally {
                em.getTransaction().commit();
            }
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Método para buscar un Servicio por su ID
    public Servicio findServicio(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Servicio.class, id);
        } finally {
            em.close();
        }
    }

    // Método para obtener todos los registros de Servicio
    public List<Servicio> findServicioEntities() {
        return findServicioEntities(true, -1, -1);
    }

    public List<Servicio> findServicioEntities(int maxResults, int firstResult) {
        return findServicioEntities(false, maxResults, firstResult);
    }

    private List<Servicio> findServicioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Servicio.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
    
    // Método para obtener la cantidad total de registros en la tabla Servicio
    public int getServicioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Servicio> rt = cq.from(Servicio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
