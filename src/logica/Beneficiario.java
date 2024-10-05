package logica;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Beneficiario implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // ID se genera automáticamente
    private Long id;  // Clave primaria
    
    private String nombre;
    private int edad;
    
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> discapacidades = new ArrayList<>();
    
    private String detallesAdicionales;
    
    private boolean inicializado; // Nuevo campo

    // Relación uno a muchos con Servicio
    @OneToMany(mappedBy = "beneficiario", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Servicio> servicios = new ArrayList<>(); // Inicializado como una lista vacía
    
    // Constructor vacío (requerido por JPA)
    public Beneficiario() {}

    // Constructor con parámetros
    public Beneficiario(String nombre, int edad, List<String> discapacidades, String detallesAdicionales) {
        this.nombre = nombre;
        this.edad = edad;
        this.discapacidades = discapacidades;
        this.detallesAdicionales = detallesAdicionales;
        this.inicializado = false; // Inicialmente no está inicializado
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public List<String> getDiscapacidades() {
        return discapacidades;
    }

    public void setDiscapacidades(List<String> discapacidades) {
        this.discapacidades = discapacidades;
    }

    public String getDetallesAdicionales() {
        return detallesAdicionales;
    }

    public void setDetallesAdicionales(String detallesAdicionales) {
        this.detallesAdicionales = detallesAdicionales;
    }

    public List<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(List<Servicio> servicios) {
        this.servicios = servicios;
    }

    @Override
    public String toString() {
        return nombre + " (Edad: " + edad + ")"; // Cambia esto si deseas más información
    }
}


