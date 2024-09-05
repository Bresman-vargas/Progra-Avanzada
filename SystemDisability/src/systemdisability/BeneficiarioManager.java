package systemdisability;
import java.util.HashMap;
import java.util.Map;

public class BeneficiarioManager {

    // Mapa que almacena los beneficiarios por su ID
    private static final Map<String, Beneficiario> beneficiarios = new HashMap<>();

    // Método para agregar un nuevo beneficiario
    public static void agregarBeneficiario(Beneficiario beneficiario) {
        if (beneficiarios.containsKey(beneficiario.getId())) {
            System.out.println("El beneficiario con el ID " + beneficiario.getId() + " ya existe.");
        } else {
            beneficiarios.put(beneficiario.getId(), beneficiario);
        }
    }

    // Método para obtener un beneficiario por su ID
    public static Beneficiario obtenerBeneficiarioPorId(String id) {
        return beneficiarios.get(id);
    }

    // Método para actualizar la información de un beneficiario existente
    public static void actualizarBeneficiario(Beneficiario beneficiario) {
        if (beneficiarios.containsKey(beneficiario.getId())) {
            beneficiarios.put(beneficiario.getId(), beneficiario);
        } else {
            System.out.println("El beneficiario no existe y no se puede actualizar.");
        }
    }

    // Método para eliminar un beneficiario por su ID
    public static void eliminarBeneficiario(String id) {
        if (beneficiarios.containsKey(id)) {
            beneficiarios.remove(id);
        } else {
            System.out.println("El beneficiario con el ID proporcionado no existe.");
        }
    }

    // Método para mostrar todos los beneficiarios en formato tabular
    public static void mostrarBeneficiariosEnTabla() {
        if (beneficiarios.isEmpty()) {
            System.out.println("No hay beneficiarios disponibles.");
            return;
        }

        System.out.println("===============================================");
        System.out.println("            LISTA DE BENEFICIARIOS            ");
        System.out.println("===============================================");
        System.out.printf("| %-15s | %-20s | %-5s | %-15s | %-30s |%n", "ID", "Nombre", "Edad", "Discapacidad", "Detalles");
        System.out.println("-------------------------------------------------------------------------------");
        for (Beneficiario beneficiario : beneficiarios.values()) {
            System.out.printf("| %-15s | %-20s | %-5d | %-15s | %-30s |%n",
                    beneficiario.getId(),
                    beneficiario.getNombre(),
                    beneficiario.getEdad(),
                    beneficiario.getDiscapacidad(),
                    beneficiario.getDetallesAdicionales());
        }
        System.out.println("===============================================");
    }

    // Método para inicializar la lista de beneficiarios con datos de prueba
    public static void inicializarDatosBE() {
        agregarBeneficiario(new Beneficiario("Juan Pérez", 30, "Ceguera", "Ninguno"));
        agregarBeneficiario(new Beneficiario("Ana Gómez", 25, "Parálisis Cerebral", "Requiere silla de ruedas"));
        agregarBeneficiario(new Beneficiario("Luis Rodríguez", 40, "Sordera", "Uso de audífonos"));
        // Puedes agregar más beneficiarios de prueba aquí con IDs únicos
    }
}
