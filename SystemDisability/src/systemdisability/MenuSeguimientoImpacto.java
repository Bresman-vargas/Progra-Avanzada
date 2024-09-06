package systemdisability;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MenuSeguimientoImpacto {

    public static void mostrarMenu(Scanner scanner) {
        int opcion;

        do {
            System.out.println("==============================================");
            System.out.println("         Sistema de Gestión de Servicios");
            System.out.println("       Seguimiento de Impacto de Beneficiarios");
            System.out.println("==============================================");
            System.out.println("1. Ver progreso de un beneficiario");
            System.out.println("2. Actualizar progreso en un servicio");
            System.out.println("3. Ver impacto general por servicio"); // Nueva opción
            System.out.println("4. Volver al menú principal");
            System.out.println("==============================================");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();  // Limpiar el buffer

            switch (opcion) {
                case 1 -> verProgresoBeneficiario(scanner);
                case 2 -> actualizarProgresoBeneficiario(scanner);
                case 3 -> mostrarImpactoGeneralPorServicio(scanner); // Pasar el scanner como parámetro
                case 4 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción no válida, intente de nuevo.");
            }

        } while (opcion != 4);
    }

    private static void verProgresoBeneficiario(Scanner scanner) {
        Utilidades.limpiarPantalla();
        ServicioBeneficiariosManager.mostrarServiciosAsignados();
        
        System.out.print("Ingrese el ID del beneficiario: ");
        String idBeneficiario = scanner.nextLine();

        Beneficiario beneficiario = BeneficiarioManager.obtenerBeneficiarioPorId(idBeneficiario);
        Utilidades.limpiarPantalla();
        if (beneficiario != null) {
            System.out.println("Progreso de " + beneficiario.getNombre() + ":");

            List<ProgresoBeneficiario> progresos = ServicioBeneficiariosManager.obtenerProgresoBeneficiario(idBeneficiario);
            
            if (progresos.isEmpty()) {
                System.out.println("No hay servicios asignados.");
            } else {
                System.out.printf("| %-20s | %-10s |%n", "Servicio", "Progreso");
                System.out.printf("+----------------------+------------+%n");

                for (ProgresoBeneficiario progreso : progresos) {
                    Servicio servicio = ServicioManager.obtenerServicioPorCodigo(progreso.getServicioId());
                    System.out.printf("| %-20s | %-10d |%n", servicio.getNombre(), progreso.getProgreso());
                }
                System.out.printf("+----------------------+------------+%n");
                System.out.println("--------------------------------------------------------------------------------------");
                System.out.println("Presione Enter para continuar...");
                scanner.nextLine();  // Esperar que el usuario presione Enter
            }
        } else {
            System.out.println("Beneficiario no encontrado.");
        }
    }

    public static void actualizarProgresoBeneficiario(Scanner scanner) {
        // Solicitar el ID del beneficiario
        Utilidades.limpiarPantalla();
        ServicioBeneficiariosManager.mostrarServiciosAsignados();
        System.out.print("Ingrese el ID del beneficiario: ");
        String beneficiarioId = scanner.nextLine().trim();

        // Buscar el beneficiario por ID
        Beneficiario beneficiario = BeneficiarioManager.obtenerBeneficiarioPorId(beneficiarioId);

        if (beneficiario == null) {
            System.out.println("No se encontró un beneficiario con ese ID.");
            return;
        }

        // Obtener los progresos de los servicios asignados al beneficiario
        List<ProgresoBeneficiario> progresos = ServicioBeneficiariosManager.obtenerProgresoBeneficiario(beneficiarioId);

        if (progresos.isEmpty()) {
            System.out.println("El beneficiario no tiene servicios asignados.");
            return;
        }

        // Iterar sobre los servicios asignados y permitir actualizar el progreso
        for (ProgresoBeneficiario progreso : progresos) {
            Servicio servicio = ServicioManager.obtenerServicioPorCodigo(progreso.getServicioId());

            if (servicio != null) {
                System.out.println("Servicio: " + servicio.getNombre());
                System.out.println("Progreso actual: " + progreso.getProgreso() + "%");

                // Preguntar si desea actualizar el progreso
                System.out.print("Ingrese el nuevo progreso (deje en blanco para mantener el progreso actual): ");
                String input = scanner.nextLine().trim();

                // Si el usuario deja en blanco, mantener el progreso actual
                if (!input.isEmpty()) {
                    try {
                        int nuevoProgreso = Integer.parseInt(input);
                        if (nuevoProgreso >= 0 && nuevoProgreso <= 100) {
                            progreso.setProgreso(nuevoProgreso);
                            System.out.println("Progreso actualizado a " + nuevoProgreso + "%.");
                        } else {
                            System.out.println("El progreso debe ser un valor entre 0 y 100.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Entrada no válida. Manteniendo el progreso actual.");
                    }
                } else {
                    System.out.println("Progreso no modificado.");
                }
                System.out.println("-------------------------");
            } else {
                System.out.println("No se encontró el servicio con ID: " + progreso.getServicioId());
            }
        }

        System.out.println("Actualización de progreso completada.");
    }

    public static void mostrarImpactoGeneralPorServicio(Scanner scanner) {
        Utilidades.limpiarPantalla();
        List<Servicio> todosServicios = ServicioManager.obtenerTodosLosServicios();
        Map<String, List<String>> beneficiariosPorServicio = new HashMap<>();
        Map<String, Map<String, Integer>> progresoPorBeneficiario = new HashMap<>();
    
        // Inicializar mapas con los servicios
        for (Servicio servicio : todosServicios) {
            beneficiariosPorServicio.put(servicio.getId(), new ArrayList<>());
            progresoPorBeneficiario.put(servicio.getId(), new HashMap<>());
        }
    
        // Obtener todos los beneficiarios
        List<Beneficiario> todosBeneficiarios = BeneficiarioManager.obtenerTodosLosBeneficiarios();
    
        // Iterar sobre todos los beneficiarios y asignar sus progresos
        for (Beneficiario beneficiario : todosBeneficiarios) {
            List<ProgresoBeneficiario> progresos = ServicioBeneficiariosManager.obtenerProgresoBeneficiario(beneficiario.getId());
    
            for (ProgresoBeneficiario progreso : progresos) {
                String servicioId = progreso.getServicioId();
                // Agregar nombre del beneficiario al servicio
                if (!beneficiariosPorServicio.get(servicioId).contains(beneficiario.getNombre())) {
                    beneficiariosPorServicio.get(servicioId).add(beneficiario.getNombre());
                }
                // Agregar progreso del beneficiario para el servicio
                progresoPorBeneficiario.get(servicioId).put(beneficiario.getNombre(), progreso.getProgreso());
            }
        }
    
        // Mostrar tabla
        System.out.println("==============================================");
        System.out.println("| Servicio             | Beneficiarios y Progreso                 |");
        System.out.println("+----------------------+------------------------------------------+");
    
        int totalProgreso = 0;
        int totalContador = 0;
    
        for (Servicio servicio : todosServicios) {
            String servicioId = servicio.getId();
            List<String> beneficiarios = beneficiariosPorServicio.get(servicioId);
            Map<String, Integer> progresos = progresoPorBeneficiario.get(servicioId);
    
            if (!beneficiarios.isEmpty()) {
                for (String nombreBeneficiario : beneficiarios) {
                    int progreso = progresos.getOrDefault(nombreBeneficiario, 0);
                    totalProgreso += progreso;
                    totalContador++;
                    System.out.printf("| %-20s | %-40s: %d%%        |%n", 
                                      servicio.getNombre(), 
                                      nombreBeneficiario, 
                                      progreso);
                }
            } else {
                System.out.printf("| %-20s | No hay beneficiarios                      |%n", servicio.getNombre());
            }
        }
    
        int progresoGeneral = totalContador > 0 ? totalProgreso / totalContador : 0;
    
        System.out.println("+----------------------+------------------------------------------+");
        System.out.printf("Progreso general acumulado: %d%%%n", progresoGeneral);
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println("Presione Enter para continuar...");
    
        // Esperar la entrada del usuario sin cerrar el Scanner
        scanner.nextLine(); // Esperar que el usuario presione Enter
        Utilidades.limpiarPantalla();
    }
}
