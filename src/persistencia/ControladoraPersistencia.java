package persistencia;
import java.util.List;
import logica.Asignacion;
import logica.Beneficiario;
import logica.Servicio;

public class ControladoraPersistencia {
    
    BeneficiarioJpaController BeneficiaioJpa = new BeneficiarioJpaController();
    ServicioJpaController ServicioJpa = new ServicioJpaController();
    AsignacionJpaController  AsignacionJpa = new AsignacionJpaController();
    
    

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

    public void agregarAsignacion(Asignacion asig) {
        AsignacionJpa.create(asig);
    }

    public List<Asignacion> traerAsignaciones() {
        return AsignacionJpa.findAll();
    }

    public void agregar(Beneficiario ben) {
        BeneficiaioJpa.create(ben);
    }

    public void borrarAsig(long num_Assig) throws Exception {
        AsignacionJpa.destroy(num_Assig);
    }

    public Asignacion traerAsing(long num_Asig) {
        return AsignacionJpa.find(num_Asig);
    }

    public void modificarAsig(Asignacion Asig) throws Exception {
        AsignacionJpa.edit(Asig);
    }


    public List<Asignacion> findAsignacionesPorBeneficiario(long num_Beneficiario) {
        return AsignacionJpa.findAsignacionesPorBeneficiario(num_Beneficiario);
    }


    public List<Asignacion> obtenerAsignacionesPorServicio(long num_Servicio) {
        return AsignacionJpa.obtenerAsignacionesPorServicio(num_Servicio);
    }
}
