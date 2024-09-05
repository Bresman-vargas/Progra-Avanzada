package systemdisability;
import java.util.Map;
import java.util.Scanner;
/**
 * @author bresm
 */

public class MenuServicios {

    private static Scanner scanner = new Scanner(System.in);

    public static void mostrarMenu(Scanner scanner) {
        while (true) {
            Utilidades.limpiarPantalla();

            System.out.println("===============================================");
            System.out.println("Administración de Servicios de Apoyo");
            System.out.println("===============================================");
            System.out.println("1. Añadir Nuevo Servicio");
            System.out.println("2. Editar Información de Servicio");
            System.out.println("3. Asignar Servicio a Beneficiario");
            System.out.println("4. Ver Servicios Asignados");
            System.out.println("5. Eliminar Servicio");
            System.out.println("6. Ver Todos los Servicios");
            System.out.println("7. Volver al Menú Principal");
            System.out.println("===============================================");
            System.out.print("Seleccione una opción: ");

            int opcion = obtenerOpcionValida();

            switch (opcion) {
                case 1 -> añadirServicio();
                case 2 -> editarServicio();
                case 3 -> asignarServicio();
                case 4 -> verServiciosAsignados();
                case 5 -> eliminarServicio();
                case 6 -> verTodosLosServicios(); // Nueva opción
                case 7 -> {
                    return;
                }
                default -> System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    private static int obtenerOpcionValida() {
        while (!scanner.hasNextInt()) {
            System.out.println("Entrada inválida. Debe ingresar un número.");
            scanner.next(); // Limpiar la entrada no válida
        }
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer después de leer un entero
        return opcion;
    }

    private static void añadirServicio() {
        Utilidades.limpiarPantalla();
        System.out.println("===============================================");
        System.out.println("Añadir Nuevo Servicio");
        System.out.println("===============================================");

        System.out.print("Ingrese el nombre del servicio: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese la descripción del servicio: ");
        String descripcion = scanner.nextLine();

        Servicio nuevoServicio = new Servicio(nombre, descripcion);
        ServicioManager.agregarServicio(nuevoServicio);

        System.out.println("\n-----------------------------------------------");
        System.out.println("Servicio añadido exitosamente.");
        System.out.println("Presione Enter para continuar...");
        scanner.nextLine();  // Esperar que el usuario presione Enter
    }

    private static void editarServicio() {
    ServicioManager.mostrarServiciosEnTabla();
    System.out.println("\n===============================================");
    System.out.println("   EDITAR INFORMACIÓN DE SERVICIO");
    System.out.println("===============================================");

    System.out.print("Ingrese el código del servicio a editar: ");
    String codigo = scanner.nextLine().trim();

    Servicio servicio = ServicioManager.obtenerServicioPorCodigo(codigo);

    if (servicio == null) {
        System.out.println("No se encontró un servicio con ese código.");
        System.out.println("Presione Enter para continuar...");
        scanner.nextLine();  // Esperar que el usuario presione Enter
        return;
    }

    Utilidades.limpiarPantalla();

    System.out.println("===============================================");
    System.out.println("          INFORMACIÓN DEL SERVICIO");
    System.out.println("===============================================");
    System.out.printf("%-15s: %s%n", "Código", servicio.getId());
    System.out.printf("%-15s: %s%n", "Nombre", servicio.getNombre());
    System.out.printf("%-15s: %s%n", "Descripción", servicio.getDescripcion());
    System.out.println("===============================================\n");

    System.out.println("Ingrese los nuevos datos (deje en blanco para mantener el valor actual):\n");

    System.out.print("Nombre: ");
    String nombre = scanner.nextLine().trim();
    if (!nombre.isBlank()) {
        servicio.setNombre(nombre);
    }

    System.out.print("Descripción: ");
    String descripcion = scanner.nextLine().trim();
    if (!descripcion.isBlank()) {
        servicio.setDescripcion(descripcion);
    }

    // Actualizar el servicio en el sistema
    ServicioManager.actualizarServicio(servicio);

    System.out.println("Servicio actualizado exitosamente.");
    System.out.println("===============================================");
    System.out.println("Presione Enter para continuar...");
    scanner.nextLine();  // Esperar que el usuario presione Enter
    }

    private static void asignarServicio() {
    Utilidades.limpiarPantalla();
    // Mostrar todos los servicios disponibles
    ServicioManager.mostrarServiciosEnTabla();
    System.out.println("===============================================");
    System.out.println("          ASIGNAR SERVICIO A BENEFICIARIO      ");
    System.out.println("===============================================");

    System.out.print("\nIngrese el código del servicio que desea asignar: ");
    String codigoServicio = scanner.nextLine();

    Servicio servicio = ServicioManager.obtenerServicioPorCodigo(codigoServicio);
    if (servicio == null) {
        System.out.println("No se encontró un servicio con ese código.");
        System.out.println("Presione Enter para continuar...");
        scanner.nextLine();
        return;
    }

    System.out.println("Servicio seleccionado: " + servicio.getNombre());

    // Mostrar todos los beneficiarios disponibles
    BeneficiarioManager.mostrarBeneficiariosEnTabla();

    System.out.print("\nIngrese el ID del beneficiario al que desea asignar el servicio: ");
    String idBeneficiario = scanner.nextLine();

    Beneficiario beneficiario = BeneficiarioManager.obtenerBeneficiarioPorId(idBeneficiario);
    if (beneficiario == null) {
        System.out.println("No se encontró un beneficiario con ese ID.");
        System.out.println("Presione Enter para continuar...");
        scanner.nextLine();
        return;
    }

    System.out.println("Beneficiario seleccionado: " + beneficiario.getNombre());

    // Asignar el servicio al beneficiario
    ServicioBeneficiariosManager.asignarServicio(idBeneficiario, codigoServicio);

    System.out.println("\n-----------------------------------------------");
    System.out.println("Servicio '" + servicio.getNombre() + "' asignado exitosamente al beneficiario '" + beneficiario.getNombre() + "'.");
    System.out.println("Presione Enter para continuar...");
    scanner.nextLine();
    }


    private static void verServiciosAsignados() {
    Utilidades.limpiarPantalla();
    System.out.println("===============================================");
    System.out.println("           Ver Servicios Asignados            ");
    System.out.println("===============================================");

    // Obtener asignaciones
    Map<String, String> asignaciones = ServicioBeneficiariosManager.obtenerAsignaciones();

    if (asignaciones.isEmpty()) {
        System.out.println("No hay servicios asignados a beneficiarios.");
    } else {
        // Encabezado de la tabla
        System.out.printf("+--------------+------------------------------+------------------------------+%n");
        System.out.printf("| Beneficiario | Servicio                     | Detalles                     |%n");
        System.out.printf("+--------------+------------------------------+------------------------------+%n");

        // Imprimir detalles de las asignaciones
        for (Map.Entry<String, String> entry : asignaciones.entrySet()) {
            String beneficiarioId = entry.getKey();
            String servicioId = entry.getValue();

            // Obtener detalles del beneficiario y del servicio
            Beneficiario beneficiario = BeneficiarioManager.obtenerBeneficiarioPorId(beneficiarioId);
            Servicio servicio = ServicioManager.obtenerServicioPorCodigo(servicioId);

            if (beneficiario != null && servicio != null) {
                System.out.printf("| %-12s | %-28s | %-28s |%n",
                        beneficiario.getNombre(),
                        servicio.getNombre(),
                        servicio.getDescripcion());
            } else {
                System.out.printf("| %-12s | %-28s | %-28s |%n",
                        beneficiarioId,
                        "Servicio no disponible",
                        "Detalles no disponibles");
            }
        }

        // Línea final de la tabla
        System.out.printf("+--------------+------------------------------+------------------------------+%n");
    }

    System.out.println("===============================================");
    System.out.println("Presione Enter para continuar...");
    scanner.nextLine(); // Esperar que el usuario presione Enter
    }

    private static void eliminarServicio() {
    // Mostrar todos los servicios disponibles
    Utilidades.limpiarPantalla();
    ServicioManager.mostrarServiciosEnTabla();
    System.out.println("===============================================");
    System.out.println("Eliminar Servicio");
    System.out.println("===============================================");

    System.out.print("\nIngrese el código del servicio que desea eliminar: ");
    String codigo = scanner.nextLine().trim();

    // Verificar si el servicio existe
    Servicio servicio = ServicioManager.obtenerServicioPorCodigo(codigo);
    if (servicio == null) {
        System.out.println("No se encontró un servicio con ese código.");
    } else {
        // Eliminar el servicio
        ServicioManager.eliminarServicio(codigo);
        System.out.println("Servicio eliminado exitosamente.");
    }

    System.out.println("Presione Enter para continuar...");
    scanner.nextLine(); // Esperar que el usuario presione Enter
    }

    private static void verTodosLosServicios() {
    Utilidades.limpiarPantalla();
    ServicioManager.mostrarServiciosEnTabla();

    System.out.println("--------------------------------------------------------------------------------------");
    System.out.println("Presione Enter para continuar...");
    scanner.nextLine();  // Esperar que el usuario presione Enter
    }
}


