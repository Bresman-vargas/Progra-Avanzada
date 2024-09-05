package systemdisability;
import java.util.HashMap;
import java.util.Map;

public class ServicioManager {

    // Mapa que almacena los servicios por su código
    private static final Map<String, Servicio> servicios = new HashMap<>();

    // Método para agregar un nuevo servicio
    public static void agregarServicio(Servicio servicio) {
        if (servicios.containsKey(servicio.getId())) {
        System.out.println("El servicio con el código " + servicio.getId() + " ya existe.");
        } else {
            servicios.put(servicio.getId(), servicio);
        }
    }

    // Método para obtener un servicio por su código
    public static Servicio obtenerServicioPorCodigo(String codigo) {
        return servicios.get(codigo);
    }

    // Método para actualizar la información de un servicio existente
    public static void actualizarServicio(Servicio servicio) {
        if (servicios.containsKey(servicio.getId())) {
            servicios.put(servicio.getId(), servicio);
        } else {
            System.out.println("El servicio no existe y no se puede actualizar.");
        }
    }

    // Método para eliminar un servicio por su código
    public static void eliminarServicio(String codigo) {
        if (servicios.containsKey(codigo)) {
            servicios.remove(codigo);
        } else {
            System.out.println("El servicio con el código proporcionado no existe.");
        }
    }

    // Método para mostrar todos los servicios en formato tabular
    public static void mostrarServiciosEnTabla() {
        if (servicios.isEmpty()) {
            System.out.println("No hay servicios disponibles.");
            return;
        }

        System.out.println("===============================================");
        System.out.println("               LISTA DE SERVICIOS              ");
        System.out.println("===============================================");
        System.out.printf("| %-15s | %-30s | %-50s |%n", "Código", "Nombre", "Descripción");
        System.out.println("---------------------------------------------------------------------------");
        for (Servicio servicio : servicios.values()) {
            System.out.printf("| %-15s | %-30s | %-50s |%n", servicio.getId(), servicio.getNombre(), servicio.getDescripcion());
        }
        System.out.println("===============================================");
    }

    public static void inicializarDatosSE() {
    agregarServicio(new Servicio("Fisioterapia", "Sesiones de fisioterapia para mejorar la movilidad"));
    agregarServicio(new Servicio("Educación Especial", "Clases adaptadas para personas con discapacidades"));
    agregarServicio(new Servicio("Inserción Laboral", "Programa de inserción laboral para personas con discapacidades"));
    // Puedes agregar más servicios de prueba aquí con IDs únicos
}

}
