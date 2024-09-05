package systemdisability;


import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MenuBeneficiarios {

    public static void mostrarMenu(Scanner scanner) {
        while (true) {
            Utilidades.limpiarPantalla();  // Limpiar pantalla al mostrar el menú de beneficiarios

            System.out.println("===============================================");
            System.out.println("Registro de Beneficiarios");
            System.out.println("===============================================");
            System.out.println("1. Añadir Nuevo Beneficiario");
            System.out.println("2. Editar Información de Beneficiario");
            System.out.println("3. Eliminar Beneficiario");
            System.out.println("4. Listar Todos los Beneficiarios");
            System.out.println("5. Volver al Menú Principal");
            System.out.println("===============================================");
            System.out.print("Seleccione una opción: ");

            int opcion = 0;
            boolean entradaValida = false;

            while (!entradaValida) {
                try {
                    opcion = scanner.nextInt();
                    scanner.nextLine();  // Limpiar el buffer del scanner
                    if (opcion >= 1 && opcion <= 5) {
                        entradaValida = true;
                    } else {
                        System.out.println("Opción no válida. Debe ingresar un número entre 1 y 5.");
                        System.out.print("Seleccione una opción: ");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Entrada no válida. Debe ingresar un número entero.");
                    scanner.next();  // Limpiar el buffer de entrada
                    System.out.print("Seleccione una opción: ");
                }
            }

            switch (opcion) {
                case 1 -> añadirBeneficiario(scanner);
                case 2 -> editarBeneficiario(scanner);
                case 3 -> eliminarBeneficiario(scanner);
                case 4 -> listarBeneficiarios(scanner);
                case 5 -> {
                    return;  // Volver al menú principal
                }
                default -> System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    private static void añadirBeneficiario(Scanner scanner) {
        Utilidades.limpiarPantalla();
        System.out.println("===============================================");
        System.out.println("Añadir Nuevo Beneficiario");
        System.out.println("===============================================");
    
        String nombre = "";
        while (nombre.isBlank()) {
            System.out.print("Ingrese el nombre completo: ");
            nombre = scanner.nextLine();
            if (nombre.isBlank()) {
                System.out.println("El nombre no puede quedar en blanco. Intente de nuevo.");
            }
        }
    
        int edad = -1; // Inicializar con un valor no válido para entrar en el bucle
        while (edad < 0) {
            System.out.print("Ingrese la edad: ");
            String edadInput = scanner.nextLine();
            try {
                edad = Integer.parseInt(edadInput);
                if (edad < 0) {
                    System.out.println("Edad no válida. La edad debe ser un número entero positivo.");
                    edad = -1; // Restablecer a un valor no válido para continuar en el bucle
                }
            } catch (NumberFormatException e) {
                System.out.println("Edad no válida. Debe ingresar un número entero.");
            }
        }
    
        System.out.print("Ingrese el/las discapacidad/es (separados por comas): ");
        String discapacidadesInput = scanner.nextLine();
        List<String> discapacidades = List.of(discapacidadesInput.split("\\s*,\\s*"));
    
        System.out.print("Ingrese detalles adicionales (opcional): ");
        String detalles = scanner.nextLine();
    
        Beneficiario beneficiario = new Beneficiario(nombre, edad, discapacidades, detalles);
        BeneficiarioManager.agregarBeneficiario(beneficiario);
    
        System.out.println("\n-----------------------------------------------");
        System.out.println("Nuevo beneficiario añadido exitosamente.");
        System.out.println("Presione Enter para continuar...");
        scanner.nextLine();  // Esperar que el usuario presione Enter
    }
    

    private static void editarBeneficiario(Scanner scanner) {
        BeneficiarioManager.mostrarBeneficiariosEnTabla();
        System.out.println("===============================================");
        System.out.println("   EDITAR INFORMACIÓN DE BENEFICIARIO");
        System.out.println("===============================================");
    
        System.out.print("Ingrese el ID del beneficiario a editar: ");
        String id = scanner.nextLine();
    
        Beneficiario beneficiario = BeneficiarioManager.obtenerBeneficiarioPorId(id);
    
        if (beneficiario == null) {
            System.out.println("No se encontró un beneficiario con ese ID.");
            return;
        }
        
        Utilidades.limpiarPantalla();
    
        System.out.println("===============================================");
        System.out.println("            INFORMACIÓN DEL BENEFICIARIO");
        System.out.println("===============================================");
        System.out.printf("%-15s: %s%n", "ID", beneficiario.getId());
        System.out.printf("%-15s: %s%n", "Nombre", beneficiario.getNombre());
        System.out.printf("%-15s: %d%n", "Edad", beneficiario.getEdad());
        System.out.printf("%-15s: %s%n", "Discapacidad(es)", String.join(", ", beneficiario.getDiscapacidades()));
        System.out.printf("%-15s: %s%n", "Detalles", beneficiario.getDetallesAdicionales());
        System.out.println("===============================================\n");
    
        System.out.println("Ingrese los nuevos datos (deje en blanco para mantener el valor actual):\n");
    
        // Nombre
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        if (!nombre.isBlank()) {
            beneficiario.setNombre(nombre);
        }
    
        // Edad
        String edadInput;
        int edad;
        while (true) {
            System.out.print("Edad: ");
            edadInput = scanner.nextLine();
            if (edadInput.isBlank()) {
                break;  // Mantener el valor actual si no se ingresa un nuevo valor
            }
            try {
                edad = Integer.parseInt(edadInput);
                if (edad >= 0) {
                    beneficiario.setEdad(edad);
                    break;
                } else {
                    System.out.println("Edad no válida. Debe ser un número entero positivo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Edad no válida. Debe ingresar un número entero.");
            }
        }
    
        // Discapacidad(es)
        System.out.print("Discapacidad(es) (separadas por comas): ");
        String discapacidadesInput = scanner.nextLine();
        if (!discapacidadesInput.isBlank()) {
            List<String> discapacidades = List.of(discapacidadesInput.split("\\s*,\\s*"));
            beneficiario.setDiscapacidades(discapacidades);
        }
    
        // Detalles
        System.out.print("Detalles: ");
        String detalles = scanner.nextLine();
        beneficiario.setDetallesAdicionales(detalles);
        
    
        BeneficiarioManager.actualizarBeneficiario(beneficiario);
    
        System.out.println("Beneficiario actualizado exitosamente.");
    
        System.out.println("===============================================");
        System.out.println("Presione Enter para continuar...");
        scanner.nextLine(); // Esperar que el usuario presione Enter
    }
    

    private static void eliminarBeneficiario(Scanner scanner) {
        BeneficiarioManager.mostrarBeneficiariosEnTabla();
        System.out.println("\n===============================================");
        System.out.println("Eliminar Beneficiario");
        System.out.println("===============================================");

        System.out.print("Ingrese el ID del beneficiario a eliminar: ");
        String id = scanner.nextLine();
        Beneficiario beneficiario = BeneficiarioManager.obtenerBeneficiarioPorId(id);

        if (beneficiario != null) {
            BeneficiarioManager.eliminarBeneficiario(id);
            System.out.println("\n-----------------------------------------------");
            System.out.println("Beneficiario eliminado exitosamente.");
        } else {
            System.out.println("Beneficiario no encontrado.");
        }

        System.out.println("Presione Enter para continuar...");
        scanner.nextLine();  // Esperar que el usuario presione Enter
    }

    private static void listarBeneficiarios(Scanner scanner) {
        Utilidades.limpiarPantalla();
        BeneficiarioManager.mostrarBeneficiariosEnTabla();

        System.out.println("===============================================");
        System.out.println("Presione Enter para continuar...");
        scanner.nextLine(); // Esperar que el usuario presione Enter
    }
}
