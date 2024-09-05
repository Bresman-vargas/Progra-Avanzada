package systemdisability;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ServicioBeneficiariosManager {
    // Reutiliza la misma instancia de Scanner
    private static Scanner scanner = new Scanner(System.in);

    // Mapa que asocia el ID del beneficiario con el ID del servicio asignado
    private static final Map<String, String> asignaciones = new HashMap<>();

    // Método para asignar un servicio a un beneficiario
    public static void asignarServicio(String beneficiarioId, String servicioId) {
        if (asignaciones.containsKey(beneficiarioId)) {
            System.out.println("El beneficiario con ID " + beneficiarioId + " ya tiene un servicio asignado.");
        } else {
            asignaciones.put(beneficiarioId, servicioId);
            System.out.println("Servicio asignado exitosamente.");
        }
    }

    // Método para obtener el servicio asignado a un beneficiario
    public static String obtenerServicioAsignado(String beneficiarioId) {
        return asignaciones.get(beneficiarioId);
    }

    // Método para eliminar la asignación de un servicio a un beneficiario
    public static void eliminarAsignacion(String beneficiarioId) {
        if (asignaciones.remove(beneficiarioId) != null) {
            System.out.println("Asignación eliminada exitosamente.");
        } else {
            System.out.println("No se encontró una asignación para el beneficiario con ID " + beneficiarioId + ".");
        }
    }

    // Método para mostrar todos los servicios asignados a beneficiarios
    public static void mostrarServiciosAsignados() {
        Utilidades.limpiarPantalla();
        System.out.println("===============================================");
        System.out.println("         SERVICIOS ASIGNADOS A BENEFICIARIOS  ");
        System.out.println("===============================================");

        if (asignaciones.isEmpty()) {
            System.out.println("No hay servicios asignados.");
        } else {
            // Encabezado de la tabla
            System.out.printf("+--------------+------------------------------+%n");
            System.out.printf("| Beneficiario | Servicio                     |%n");
            System.out.printf("+--------------+------------------------------+%n");

            // Filas de la tabla
            for (Map.Entry<String, String> entry : asignaciones.entrySet()) {
                String beneficiarioId = entry.getKey();
                String servicioId = entry.getValue();

                // Obtener detalles del beneficiario y del servicio
                Beneficiario beneficiario = BeneficiarioManager.obtenerBeneficiarioPorId(servicioId);
                Servicio servicio = ServicioManager.obtenerServicioPorCodigo(servicioId);

                if (beneficiario != null && servicio != null) {
                    System.out.printf("| %-12s | %-28s |%n", beneficiario.getNombre(), servicio.getNombre());
                } else {
                    System.out.printf("| %-12s | %-28s |%n", beneficiarioId, "Servicio no disponible");
                }
            }

            // Línea final de la tabla
            System.out.printf("+--------------+------------------------------+%n");
        }

        System.out.println("===============================================");
        System.out.println("Presione Enter para continuar...");
        scanner.nextLine(); // Esperar que el usuario presione Enter
    }
    
    public static Map<String, String> obtenerAsignaciones() {
    return new HashMap<>(asignaciones); // Devuelve una copia del mapa de asignaciones
    }
}