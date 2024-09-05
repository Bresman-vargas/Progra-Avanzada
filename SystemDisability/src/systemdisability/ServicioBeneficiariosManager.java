package systemdisability;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServicioBeneficiariosManager {
    // Reutiliza la misma instancia de Scanner

    // Mapa que asocia el ID del beneficiario con una lista de IDs de servicios asignados
    private static final Map<String, List<String>> asignaciones = new HashMap<>();

    // Método para asignar un servicio a un beneficiario
    public static void asignarServicio(String beneficiarioId, String servicioId) {
        // Obtiene la lista actual de servicios asignados para el beneficiario
        List<String> servicios = asignaciones.get(beneficiarioId);
        
        if (servicios == null) {
            // Si no hay servicios asignados, crea una nueva lista
            servicios = new ArrayList<>();
            asignaciones.put(beneficiarioId, servicios);
        }
        
        if (servicios.contains(servicioId)) {
            System.out.println("El servicio con ID " + servicioId + " ya está asignado al beneficiario con ID " + beneficiarioId + ".");
        } else {
            servicios.add(servicioId);
            System.out.println("Servicio asignado exitosamente.");
        }
    }

    // Método para obtener la lista de servicios asignados a un beneficiario
    public static List<String> obtenerServiciosAsignados(String beneficiarioId) {
        return asignaciones.getOrDefault(beneficiarioId, Collections.emptyList());
    }

    // Método para eliminar un servicio específico asignado a un beneficiario
    public static void eliminarAsignacion(String beneficiarioId, String servicioId) {
        List<String> servicios = asignaciones.get(beneficiarioId);
        
        if (servicios != null && servicios.remove(servicioId)) {
            if (servicios.isEmpty()) {
                asignaciones.remove(beneficiarioId); // Elimina al beneficiario si no tiene más servicios
            }
            System.out.println("Asignación eliminada exitosamente.");
        } else {
            System.out.println("No se encontró el servicio con ID " + servicioId + " para el beneficiario con ID " + beneficiarioId + ".");
        }
    }

    // Método para mostrar todos los servicios asignados a beneficiarios
    public static void mostrarServiciosAsignados() {
        Utilidades.limpiarPantalla();
        System.out.println("===============================================");
        System.out.println("           Ver Servicios Asignados            ");
        System.out.println("===============================================");

        // Obtener asignaciones
        Map<String, List<String>> asignaciones = ServicioBeneficiariosManager.obtenerAsignaciones();

        if (asignaciones.isEmpty()) {
            System.out.println("No hay servicios asignados a beneficiarios.");
        } else {
            // Encabezado de la tabla
            System.out.printf("| %-18s | %-28s | %-28s |%n", "Beneficiario", "Servicio", "Detalles");
            System.out.printf("+------------------------------------------------------------------------------------%n");

            // Imprimir detalles de las asignaciones
            for (Map.Entry<String, List<String>> entry : asignaciones.entrySet()) {
                String beneficiarioId = entry.getKey();
                List<String> serviciosIds = entry.getValue();

                // Obtener detalles del beneficiario
                Beneficiario beneficiario = BeneficiarioManager.obtenerBeneficiarioPorId(beneficiarioId);

                if (beneficiario != null) {
                    for (String servicioId : serviciosIds) {
                        // Obtener detalles del servicio
                        Servicio servicio = ServicioManager.obtenerServicioPorCodigo(servicioId);

                        if (servicio != null) {
                            System.out.printf("| %-18s | %-28s | %-28s |%n",
                                    beneficiario.getNombre(),
                                    servicio.getNombre(),
                                    servicio.getDescripcion());
                        } else {
                            System.out.printf("| %-18s | %-28s | %-28s |%n",
                                    beneficiarioId,
                                    "Servicio no disponible",
                                    "Detalles no disponibles");
                        }
                    }
                } else {
                    System.out.printf("| %-18s | %-28s | %-28s |%n",
                            beneficiarioId,
                            "Servicio no disponible",
                            "Detalles no disponibles"); 
                }
            }

            // Línea final de la tabla
            System.out.printf("+------------------------------------------------------------------------------------%n");
        }
    }
    
    // Método para obtener una copia del mapa de asignaciones
    public static Map<String, List<String>> obtenerAsignaciones() {
        return new HashMap<>(asignaciones); // Devuelve una copia del mapa de asignaciones
    }

    // Método para asignar un servicio a un beneficiario por nombre
    public static void asignarServicioPorNombre(String nombreBeneficiario, String nombreServicio) {
        Beneficiario beneficiario = BeneficiarioManager.obtenerBeneficiarioPorNombre(nombreBeneficiario);
        Servicio servicio = ServicioManager.obtenerServicioPorNombre(nombreServicio);
        
        if (beneficiario != null && servicio != null) {
            asignarServicio(beneficiario.getId(), servicio.getId());
        } else {
            System.out.println("Beneficiario o servicio no encontrado.");
        }
    }

    public static void inicializarAsignacionesDePrueba() {
        // Asignar servicios por nombre de beneficiario y nombre de servicio
        asignarServicioPorNombre("Sara Morales", "Fisioterapia");
        asignarServicioPorNombre("Eduardo González", "Terapia Ocupacional");
        asignarServicioPorNombre("Mónica López", "Inserción Laboral");
        asignarServicioPorNombre("Jessica Ramírez", "Asesoría Psicológica");
    }
}