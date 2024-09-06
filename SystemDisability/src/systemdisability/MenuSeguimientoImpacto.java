package systemdisability;

import java.util.List;
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
            System.out.println("3. Volver al menú principal");
            System.out.println("==============================================");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();  // Limpiar el buffer

            switch (opcion) {
                case 1 -> verProgresoBeneficiario(scanner);
                case 2 -> actualizarProgresoBeneficiario(scanner);
                case 3 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción no válida, intente de nuevo.");
            }

        } while (opcion != 3);
    }

        private static void verProgresoBeneficiario(Scanner scanner) {
        BeneficiarioManager.mostrarBeneficiariosEnTabla();
        
        System.out.print("Ingrese el ID del beneficiario: ");
        String idBeneficiario = scanner.nextLine();

        Beneficiario beneficiario = BeneficiarioManager.obtenerBeneficiarioPorId(idBeneficiario);
        
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
                System.out.printf("+----------------------|------------+%n");
            }
        } else {
            System.out.println("Beneficiario no encontrado.");
        }
    }

    private static void actualizarProgresoBeneficiario(Scanner scanner) {
        BeneficiarioManager.mostrarBeneficiariosEnTabla();
        
        System.out.print("Ingrese el ID del beneficiario: ");
        String idBeneficiario = scanner.nextLine();
        
        Beneficiario beneficiario = BeneficiarioManager.obtenerBeneficiarioPorId(idBeneficiario);
        if (beneficiario == null) {
            System.out.println("Beneficiario no encontrado.");
            return;
        }
        ServicioManager.mostrarServiciosEnTabla();
        
        System.out.print("Ingrese el ID del servicio: ");
        String servicioId = scanner.nextLine();
        
        Servicio servicio = ServicioManager.obtenerServicioPorCodigo(servicioId);
        if (servicio == null) {
            System.out.println("Servicio no encontrado.");
            return;
        }
    
        System.out.print("Ingrese el nuevo nivel de progreso (1-10): ");
        int progreso = scanner.nextInt();
        scanner.nextLine();  // Limpiar el buffer
        
        if (progreso < 1 || progreso > 10) {
            System.out.println("El progreso debe estar entre 1 y 10.");
            return;
        }
    
        // Actualizar el progreso
        List<ProgresoBeneficiario> progresos = ServicioBeneficiariosManager.obtenerProgresoBeneficiario(idBeneficiario);
        for (ProgresoBeneficiario p : progresos) {
            if (p.getServicioId().equals(servicioId)) {
                p.setProgreso(progreso);
                System.out.println("Progreso actualizado exitosamente.");
                return;
            }
        }
        
        System.out.println("No se encontró el servicio para el beneficiario.");
    }
    

}
