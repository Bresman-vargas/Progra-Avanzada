package systemdisability;

/**
 *
 * @author bresm
 */
public class Beneficiario {
    private String id;
    private String nombre;
    private int edad;
    private String discapacidad;
    private String detallesAdicionales;

    public Beneficiario(String nombre, int edad, String discapacidad, String detallesAdicionales) {
        this.id = Utilidades.generateUniqueId();
        this.nombre = nombre;
        this.edad = edad;
        this.discapacidad = discapacidad;
        this.detallesAdicionales = detallesAdicionales;
    }
    public Beneficiario(String nombre, int edad, String discapacidad) {
        this.id = Utilidades.generateUniqueId();
        this.nombre = nombre;
        this.edad = edad;
        this.discapacidad = discapacidad;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }

    public String getDiscapacidad() { return discapacidad; }
    public void setDiscapacidad(String discapacidad) { this.discapacidad = discapacidad; }

    public String getDetallesAdicionales() { return detallesAdicionales; }
    public void setDetallesAdicionales(String detallesAdicionales) { this.detallesAdicionales = detallesAdicionales; }

    @Override
    public String toString() {
        return String.format("%-15s %-25s %-5d %-15s %-30s", id, nombre, edad, discapacidad, detallesAdicionales);
    }
}


