package systemdisability;

import java.util.HashMap;
import java.util.List;
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
        System.out.printf("| %-15s | %-20s | %-5s | %-40s | %-30s |%n", "ID", "Nombre", "Edad", "Discapacidades", "Detalles");
        System.out.println("-------------------------------------------------------------------------------");
        for (Beneficiario beneficiario : beneficiarios.values()) {
            System.out.printf("| %-15s | %-20s | %-5d | %-40s | %-30s |%n",
                    beneficiario.getId(),
                    beneficiario.getNombre(),
                    beneficiario.getEdad(),
                    String.join(", ", beneficiario.getDiscapacidades()), // Cambiado a mostrar múltiples discapacidades
                    beneficiario.getDetallesAdicionales());
        }
        System.out.println("===============================================");
    }

    // Método para inicializar la lista de beneficiarios con datos de prueba
    public static void inicializarDatosBE() {
        // Agregar beneficiarios con múltiples discapacidades
    BeneficiarioManager.agregarBeneficiario(new Beneficiario("Sara Morales", 27, List.of("Ceguera", "Dislexia"), "Requiere asistencia en la lectura y navegación diaria"));
    BeneficiarioManager.agregarBeneficiario(new Beneficiario("Eduardo González", 34, List.of("Sordera", "Parálisis Cerebral"), "Necesita apoyo en la comunicación y movilidad"));
    BeneficiarioManager.agregarBeneficiario(new Beneficiario("Mónica López", 29, List.of("Autismo", "Epilepsia"), "Requiere terapia especializada y manejo de convulsiones"));
    BeneficiarioManager.agregarBeneficiario(new Beneficiario("Jessica Ramírez", 32, List.of("Esquizofrenia", "Dislexia"), "Necesita apoyo en la salud mental y educación"));
    BeneficiarioManager.agregarBeneficiario(new Beneficiario("Beatriz Soto", 45, List.of("Esclerosis múltiple", "Distrofia muscular"), "Necesita atención médica constante y adaptaciones para la movilidad"));

        // Puedes agregar más beneficiarios de prueba aquí con IDs únicos
    }
}

