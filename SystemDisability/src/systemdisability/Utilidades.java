package systemdisability;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
/**
 * @author bresm
 */
public class Utilidades {
    private static final Random random = new Random();
    public static void limpiarPantalla() {
        // Imprimir Líneas Vacías
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    } 
    
    // Método para generar un ID único de 8 dígitos
    public static String generateUniqueId() {
        return String.format("%08d", random.nextInt(100000000));
    } 

    // Método estático para obtener la fecha actual
    public static String obtenerFechaActual() {
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return fechaHoraActual.format(formato);
    }
} 
