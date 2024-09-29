package logica;

import persistencia.ControladoraPersistencia;
import persistencia.EntityManagerUtil; // Importación de EntityManagerUtil
import java.util.List;
import javax.persistence.EntityManager; // Importación de EntityManager
import javax.persistence.EntityManagerFactory; // Importación de EntityManagerFactory
import javax.swing.JOptionPane;


public class Controladora {

    ControladoraPersistencia controlPersis = new ControladoraPersistencia();

    public void agregar(String nombre, Integer edad, List<String> discapacidades, String detalles) {
        Beneficiario ben = new Beneficiario();
        ben.setNombre(nombre);
        ben.setEdad(edad);
        ben.setDiscapacidades(discapacidades);
        ben.setDetallesAdicionales(detalles);
        controlPersis.guardar(ben);
    }

    public List<Beneficiario> traerBeneficiarios() {
        return controlPersis.traerBeneficiarios();
    }

    public void borrarBeneficiario(long num_Beneficiario) {
        controlPersis.borrarBeneficiario(num_Beneficiario);
    }

    public Beneficiario traerBeneficiario(long num_Beneficiario) {
        return controlPersis.traerBeneficiario(num_Beneficiario);
    }

    public void modificarBeneficiario(Beneficiario beneficiario, String nombre, Integer edad, List<String> discapacidades, String detalles) {
        beneficiario.setNombre(nombre);
        beneficiario.setEdad(edad);
        beneficiario.setDiscapacidades(discapacidades);
        beneficiario.setDetallesAdicionales(detalles);
        
        controlPersis.modificarBeneficiario(beneficiario);
        
    } 

    public void agregarServicio(String servicio, String responsable, String detalles_servicio) throws Exception {
        Servicio ser = new Servicio();
        ser.setNombre(servicio);
        ser.setResponsable(responsable);
        ser.setDescripcion(detalles_servicio);
        controlPersis.guardarServicio(ser);
    }

    public void borrarServicio(long num_Servicio) {
        controlPersis.borrarServicio(num_Servicio);
    }

    public Servicio traerServicio(long num_Servicio) {
        return controlPersis.traerServicio(num_Servicio);
    }

    public void modificarServicio(Servicio servicio, String servicioNombre, String responsable, String detalles) throws Exception {
        try {
            // Actualizamos los atributos del objeto 'Servicio'
            servicio.setNombre(servicioNombre);
            servicio.setResponsable(responsable);
            servicio.setDescripcion(detalles);

            // Llamada al controlador de persistencia para modificar el servicio en la base de datos
            controlPersis.modificarServicio(servicio);

        } catch (Exception e) {
            // Captura de cualquier excepción durante la actualización
            System.out.println("Error al modificar el servicio: " + e.getMessage());

            // Propaga la excepción hacia arriba con un mensaje más descriptivo si es necesario
            throw new Exception("Error al modificar el servicio con ID: " + servicio.getId(), e);
        }
    }

    public List<Servicio> traerServicios() {
        return controlPersis.traerServicios();
    }
    
    // Método para asignar un servicio a un beneficiario
    public boolean asignarServicioABeneficiario(Beneficiario beneficiario, Servicio servicio) {
    AsignacionServicio asignacion = new AsignacionServicio(beneficiario, servicio);
    
    boolean resultado = controlPersis.guardarAsignacionServicio(asignacion); // Llamada al método de persistencia

    if (resultado) {
        JOptionPane.showMessageDialog(null, "El servicio se ha asignado correctamente al beneficiario.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    } else {
        JOptionPane.showMessageDialog(null, "Error al asignar el servicio.", "Error", JOptionPane.ERROR_MESSAGE);
    }

    return resultado; // Retornar el resultado de la operación
}
    public List<AsignacionServicio> traerRelaciones() {
    // Lógica para traer todas las relaciones de la base de datos
    EntityManager em = EntityManagerUtil.getEntityManager();
    List<AsignacionServicio> relaciones = em.createQuery("SELECT a FROM AsignacionServicio a", AsignacionServicio.class).getResultList();
    em.close();
    return relaciones;//sdsdasd
}


    
    
    
    
}