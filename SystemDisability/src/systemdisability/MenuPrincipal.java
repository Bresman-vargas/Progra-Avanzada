package systemdisability;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * @author bresm
 */
public class MenuPrincipal {
    public static void main(String[] args) {
        System.out.print("hola");
        BeneficiarioManager.inicializarDatosBE();
        ServicioManager.inicializarDatosSE();
        ServicioBeneficiariosManager.inicializarAsignacionesDePrueba();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            Utilidades.limpiarPantalla(); 
            
            System.out.println("===============================================");
            System.out.println("Sistema de Gestión de Servicios");
            System.out.println("Atención a Personas con Discapacidad");
            System.out.println("===============================================");
            System.out.println("1. Registro de Beneficiarios");
            System.out.println("2. Administración de Servicios de Apoyo");
            System.out.println("3. Seguimiento de Impacto");
            System.out.println("4. Salir");
            System.out.println("===============================================");
            System.out.print("Seleccione una opción: ");

            int opcion = 0;
            boolean entradaValida = false;

            while (!entradaValida) {
                try {
                    opcion = scanner.nextInt();
                    scanner.nextLine();  // Limpiar el buffer del scanner
                    if (opcion >= 1 && opcion <= 4) {
                        entradaValida = true;
                    } else {
                        System.out.println("Opción no válida. Debe ingresar un número entre 1 y 4.");
                        System.out.print("Seleccione una opción: ");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Entrada no válida. Debe ingresar un número entero.");
                    scanner.next();  // Limpiar el buffer de entrada
                    System.out.print("Seleccione una opción: ");
                }
            }

            switch (opcion) {
                case 1 -> {
                    Utilidades.limpiarPantalla();
                    MenuBeneficiarios.mostrarMenu(scanner);
                }
                case 2 -> {
                    Utilidades.limpiarPantalla();
                    MenuServicios.mostrarMenu(scanner);
                }
                case 3 -> {
                    Utilidades.limpiarPantalla();
                    MenuSeguimientoImpacto.mostrarMenu(scanner);
                }
                case 4 -> {
                    System.out.println("¡Hasta luego!");
                    scanner.close();
                    System.exit(0);
                }
                default -> System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }
}
