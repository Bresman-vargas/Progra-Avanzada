package logica;
import persistencia.ControladoraPersistencia;
import java.util.List;


public class Controladora {

    ControladoraPersistencia controlPersis = new ControladoraPersistencia();
    

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

    public void agregarAsignacion(Asignacion nuevaAsignacion) {
        controlPersis.agregarAsignacion(nuevaAsignacion);
    }

    public List<Asignacion> traerAsignaciones() {
        return controlPersis.traerAsignaciones();
    }

    public void agregar(String nombre, Integer edad, List<String> discapacidades, String detalles) {
        Beneficiario ben = new Beneficiario();
        ben.setNombre(nombre);
        ben.setEdad(edad);
        ben.setDiscapacidades(discapacidades);
        ben.setDetallesAdicionales(detalles);
        
        controlPersis.agregar(ben);
     
    }
    
    public void borrarAsig(long num_Assig) throws Exception {
        controlPersis.borrarAsig(num_Assig);
    }

    
    public Asignacion traerAsing(long num_Asig) {
        return controlPersis.traerAsing(num_Asig);
    }

    public void modificarAsig(Asignacion asignacion, Integer NumeroProgreso) throws Exception {
        asignacion.setProgreso(NumeroProgreso);
        controlPersis.modificarAsig(asignacion);
    }
}