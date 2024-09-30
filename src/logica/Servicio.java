package logica;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Servicio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // ID se genera automáticamente
    private Long id; // Clave primaria
    
    private String nombre;
    private String responsable;
    private String descripcion;
    
    @ManyToOne(fetch = FetchType.LAZY) // Relación muchos a uno con Beneficiario
    private Beneficiario beneficiario;

    // Constructor vacío requerido por JPA
    public Servicio() {}

    // Constructor con parámetros
    public Servicio(String nombre, String responsable, String descripcion, Beneficiario beneficiario) {
        this.nombre = nombre;
        this.responsable = responsable;
        this.descripcion = descripcion;
        this.beneficiario = beneficiario;
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

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Beneficiario getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(Beneficiario beneficiario) {
        this.beneficiario = beneficiario;
    }

    @Override
    public String toString() {
        return nombre + " (Responsable: " + responsable + ")"; // Cambia esto si deseas más información
    }
}
