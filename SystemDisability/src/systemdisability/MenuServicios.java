package systemdisability;
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
    
        String nombre;
        String descripcion;
    
        // Nombre del servicio
        while (true) {
            System.out.print("Ingrese el nombre del servicio: ");
            nombre = scanner.nextLine();
            if (!nombre.isBlank()) {
                break;
            } else {
                System.out.println("El nombre del servicio no puede quedar en blanco. Intente de nuevo.");
            }
        }
    
        // Descripción del servicio
        while (true) {
            System.out.print("Ingrese la descripción del servicio: ");
            descripcion = scanner.nextLine();
            if (!descripcion.isBlank()) {
                break;
            } else {
                System.out.println("La descripción del servicio no puede quedar en blanco. Intente de nuevo.");
            }
        }
    
        // Crear y agregar el nuevo servicio
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
        System.out.println("            INFORMACIÓN DEL SERVICIO");
        System.out.println("===============================================");
        System.out.printf("%-15s: %s%n", "Código", servicio.getId());
        System.out.printf("%-15s: %s%n", "Nombre", servicio.getNombre());
        System.out.printf("%-15s: %s%n", "Descripción", servicio.getDescripcion());
        System.out.println("===============================================\n");
    
        System.out.println("Ingrese los nuevos datos (deje en blanco para mantener el valor actual):");
    
        // Editar nombre del servicio
        String nuevoNombre;
        while (true) {
            System.out.print("Nombre: ");
            nuevoNombre = scanner.nextLine().trim();
            if (!nuevoNombre.isBlank()) {
                servicio.setNombre(nuevoNombre);
                break;
            } else {
                System.out.println("El nombre no puede quedar en blanco. Intente de nuevo.");
            }
        }
    
        // Editar descripción del servicio
        String nuevaDescripcion;
        while (true) {
            System.out.print("Descripción: ");
            nuevaDescripcion = scanner.nextLine().trim();
            if (!nuevaDescripcion.isBlank()) {
                servicio.setDescripcion(nuevaDescripcion);
                break;
            } else {
                System.out.println("La descripción no puede quedar en blanco. Intente de nuevo.");
            }
        }
    
        // Actualizar el servicio en el manager
        ServicioManager.actualizarServicio(servicio);
    
        System.out.println("Servicio actualizado exitosamente.");
        System.out.println("===============================================");
        System.out.println("Presione Enter para continuar...");
        scanner.nextLine(); // Esperar que el usuario presione Enter
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
        ServicioBeneficiariosManager.mostrarServiciosAsignados();
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


