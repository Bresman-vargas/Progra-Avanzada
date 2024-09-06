package systemdisability;

import java.util.Scanner;

public class MenuSeguimientoImpacto {

    public static void mostrarMenu(Scanner scanner) {
        int opcion = 0;

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
                case 1:
                    verProgresoBeneficiario(scanner);
                    break;
                case 2:
                    actualizarProgresoBeneficiario(scanner);
                    break;
                case 3:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida, intente de nuevo.");
            }

        } while (opcion != 3);
    }

    private static void verProgresoBeneficiario(Scanner scanner) {
        ServicioBeneficiariosManager.mostrarServiciosAsignados();
    
        // Pedir ID del beneficiario
        System.out.print("Ingrese el ID del beneficiario: ");
        String idBeneficiario = scanner.nextLine();
    
        // Buscar beneficiario por ID
        Beneficiario beneficiario = BeneficiarioManager.obtenerBeneficiarioPorId(idBeneficiario);
    
        if (beneficiario != null) {
            // Si el beneficiario existe, mostrar su nombre
            System.out.println("Mostrando progreso del beneficiario: " + beneficiario.getNombre());
            
            // Aquí puedes agregar la lógica para mostrar el progreso en los servicios asignados
            /*mostrarProgresoServicios(beneficiario);*/
        } else {
            // Si no se encuentra el beneficiario, mostrar mensaje de error
            System.out.println("No se encontró un beneficiario con el ID: " + idBeneficiario);
        }
    }

    private static void actualizarProgresoBeneficiario(Scanner scanner) {
        // Lógica para actualizar el progreso del beneficiario en un servicio
        System.out.print("Ingrese el ID del beneficiario: ");
        String idBeneficiario = scanner.nextLine();
        System.out.print("Ingrese el nombre del servicio: ");
        String servicio = scanner.nextLine();
        System.out.print("Ingrese el nuevo nivel de progreso (1-10): ");
        int progreso = scanner.nextInt();
        scanner.nextLine();  // Limpiar el buffer

        System.out.println("Actualizando el progreso del beneficiario con ID " + idBeneficiario + " en el servicio " + servicio + " a " + progreso + "/10.");
        // Aquí iría la lógica para actualizar el progreso.
    }
}
