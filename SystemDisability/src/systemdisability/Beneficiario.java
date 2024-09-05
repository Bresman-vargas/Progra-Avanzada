package systemdisability;

import java.util.ArrayList;
import java.util.List;

public class Beneficiario { 
    private String id;
    private String nombre;
    private int edad;
    private List<String> discapacidades;
    private String detallesAdicionales; 

    // Constructor con discapacidades múltiples
    public Beneficiario(String nombre, int edad, List<String> discapacidades, String detallesAdicionales) {
        this.id = Utilidades.generateUniqueId();  // Generación automática de ID
        this.nombre = nombre;
        this.edad = edad;
        this.discapacidades = new ArrayList<>(discapacidades);
        this.detallesAdicionales = detallesAdicionales;
    }
    
    // Constructor sin detalles adicionales
    public Beneficiario(String nombre, int edad, List<String> discapacidades) {
        this(nombre, edad, discapacidades, null);
    }

    // Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }

    public List<String> getDiscapacidades() { return discapacidades; }
    public void setDiscapacidades(List<String> discapacidades) { this.discapacidades = discapacidades; }

    public String getDetallesAdicionales() { return detallesAdicionales; }
    public void setDetallesAdicionales(String detallesAdicionales) { this.detallesAdicionales = detallesAdicionales; }

    @Override
    public String toString() {
        return String.format("%-15s %-25s %-5d %-25s %-30s", id, nombre, edad, String.join(", ", discapacidades), detallesAdicionales);
    }
}
