package systemdisability;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MenuBeneficiarios {

    private static final List<Beneficiario> beneficiarios = new ArrayList<>();

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

        System.out.print("Ingrese el nombre completo: ");
        String nombre = scanner.nextLine();

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

        System.out.print("Ingrese el tipo de discapacidad: ");
        String discapacidad = scanner.nextLine();

        System.out.print("Ingrese detalles adicionales (opcional): ");
        String detalles = scanner.nextLine();

        Beneficiario beneficiario = new Beneficiario(nombre, edad, discapacidad, detalles);
        beneficiarios.add(beneficiario);

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
        System.out.printf("%-15s: %s%n", "Discapacidad", beneficiario.getDiscapacidad());
        System.out.printf("%-15s: %s%n", "Detalles", beneficiario.getDetallesAdicionales());
        System.out.println("===============================================\n");

        System.out.println("Ingrese los nuevos datos (deje en blanco para mantener el valor actual):\n");

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        if (!nombre.isBlank()) {
            beneficiario.setNombre(nombre);
        }

        System.out.print("Edad: ");
        String edadInput = scanner.nextLine();
        if (!edadInput.isBlank()) {
            try {
                int edad = Integer.parseInt(edadInput);
                beneficiario.setEdad(edad);
            } catch (NumberFormatException e) {
                System.out.println("Edad no válida. Debe ingresar un número entero.");
            }
        }

        System.out.print("Discapacidad: ");
        String discapacidad = scanner.nextLine();
        if (!discapacidad.isBlank()) {
            beneficiario.setDiscapacidad(discapacidad);
        }

        System.out.print("Detalles: ");
        String detalles = scanner.nextLine();
        if (!detalles.isBlank()) {
            beneficiario.setDetallesAdicionales(detalles);
        }

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
            beneficiarios.remove(beneficiario);
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
