package persistencia;

import java.util.List;
import java.util.ArrayList;
import logica.Beneficiario;
import logica.Servicio;
import logica.AsignacionServicio;
import javax.persistence.EntityManager;

public class ControladoraPersistencia {
    
    BeneficiarioJpaController BeneficiaioJpa = new BeneficiarioJpaController();
    ServicioJpaController ServicioJpa = new ServicioJpaController();
    
    public void guardar(Beneficiario ben) {
        BeneficiaioJpa.create(ben);
    }

    public List<Beneficiario> traerBeneficiarios() {
        return BeneficiaioJpa.findBeneficiarioEntities();
    }

    public void borrarBeneficiario(long num_Beneficiario) {
        try {
        BeneficiaioJpa.destroy(num_Beneficiario);
        } catch (Exception e) {
            System.out.println("Error al eliminar el beneficiario: " + e.getMessage());            
        }
    }

    public Beneficiario traerBeneficiario(long num_Beneficiario) {
        return BeneficiaioJpa.findBeneficiario(num_Beneficiario);  
    }

    public void modificarBeneficiario(Beneficiario beneficiario) {
        //2:18:54
        BeneficiaioJpa.edit(beneficiario); 
    }

    public List<Servicio> traerServicios() {
        return ServicioJpa.findServicioEntities();
    }

    public void guardarServicio(Servicio ser) throws Exception {
        ServicioJpa.create(ser);
    }

    public void borrarServicio(long num_Servicio) {
        try {
        ServicioJpa.destroy(num_Servicio);
        } catch (Exception e) {
            System.out.println("Error al eliminar el servicio: " + e.getMessage());            
        }
    }

    public Servicio traerServicio(long num_Servicio) {
        return ServicioJpa.findServicio(num_Servicio);   
    }

    public void modificarServicio(Servicio servicio) throws Exception {
        ServicioJpa.edit(servicio); 
    }
    
        public boolean guardarAsignacionServicio(AsignacionServicio asignacion) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        boolean exito = false;

        try {
            em.getTransaction().begin();

            Beneficiario beneficiario = em.find(Beneficiario.class, asignacion.getBeneficiario().getId());
            Servicio servicio = em.find(Servicio.class, asignacion.getServicio().getId());

            if (beneficiario != null && servicio != null) {
                // Establece la relación bidireccional
                servicio.setBeneficiario(beneficiario);

                if (beneficiario.getServicios() == null) {
                    beneficiario.setServicios(new ArrayList<>());
                }
                beneficiario.getServicios().add(servicio); 

                em.persist(servicio); // Persistir el servicio
                em.persist(beneficiario); // Persistir el beneficiario
                exito = true;
            } else {
                System.out.println("Beneficiario o servicio no encontrado.");
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Error al guardar la asignación: " + e.getMessage());
        } finally {
            em.close();
        }
        return exito;
    }

}


