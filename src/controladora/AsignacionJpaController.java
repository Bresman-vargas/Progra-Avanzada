package controladora;
import javax.persistence.*;
import java.util.List;
import logica.Asignacion;
public class AsignacionJpaController {

    private EntityManagerFactory emf;

    public AsignacionJpaController() {
        // Inicializar el EntityManagerFactory (ajusta el nombre de tu unidad de persistencia)
        this.emf = Persistence.createEntityManagerFactory("SistemaDiscapacidadPU");
    }

    // Método para crear una nueva asignación
    public void create(Asignacion asignacion) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(asignacion); // Persistir la asignación
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback(); // Deshacer en caso de error
            }
            throw e; // Lanzar excepción
        } finally {
            em.close(); // Cerrar el EntityManager
        }
    }

    // Método para encontrar una asignación por su ID
    public Asignacion find(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Asignacion.class, id); // Buscar asignación por ID
        } finally {
            em.close(); // Cerrar el EntityManager
        }
    }

    // Método para obtener todas las asignaciones
    public List<Asignacion> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT a FROM Asignacion a", Asignacion.class).getResultList(); // Obtener todas las asignaciones
        } finally {
            em.close(); // Cerrar el EntityManager
        }
    }

    // Método para editar una asignación existente
    public void edit(Asignacion asignacion) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(asignacion); // Actualizar la asignación
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback(); // Deshacer en caso de error
            }
            throw e; // Lanzar excepción
        } finally {
            em.close(); // Cerrar el EntityManager
        }
    }

    // Método para eliminar una asignación por su ID
    public void destroy(Long id) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Asignacion asignacion = em.find(Asignacion.class, id); // Buscar asignación por ID
            if (asignacion != null) {
                em.remove(asignacion); // Eliminar la asignación
            } else {
                throw new Exception("Asignación no encontrada.");
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback(); // Deshacer en caso de error
            }
            throw e; // Lanzar excepción
        } finally {
            em.close(); // Cerrar el EntityManager
        }
    }
    
    public List<Asignacion> findAsignacionesPorBeneficiario(Long beneficiarioId) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT a FROM Asignacion a WHERE a.beneficiario.id = :beneficiarioId", Asignacion.class)
                     .setParameter("beneficiarioId", beneficiarioId)
                     .getResultList();
        } finally {
            em.close(); // Cerrar el EntityManager
        }
    }
    
    public List<Asignacion> obtenerAsignacionesPorServicio(long servicioId) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT a FROM Asignacion a JOIN a.servicios s WHERE s.id = :servicioId", Asignacion.class)
                     .setParameter("servicioId", servicioId)
                     .getResultList();
        } finally {
            em.close();
        }
    }
}
