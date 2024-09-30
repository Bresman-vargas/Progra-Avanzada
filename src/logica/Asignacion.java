package logica;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "asignaciones") // Nombre de la tabla en la base de datos
public class Asignacion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Generación automática del ID
    private Long id; // ID de la asignación

    @ManyToOne // Relación muchos a uno con Beneficiario
    @JoinColumn(name = "beneficiario_id") // Columna que hace referencia al ID del Beneficiario
    private Beneficiario beneficiario;

    @ManyToMany // Relación muchos a muchos con Servicio
    @JoinTable(
        name = "asignacion_servicios", // Tabla de relación
        joinColumns = @JoinColumn(name = "asignacion_id"), // Columna de la tabla asignaciones
        inverseJoinColumns = @JoinColumn(name = "servicio_id") // Columna de la tabla servicios
    )
    private List<Servicio> servicios; // Lista de Servicios
    
    // progreso

    // Constructor por defecto
    public Asignacion() {}

    // Constructor
    public Asignacion(Beneficiario beneficiario, List<Servicio> servicios) {
        this.beneficiario = beneficiario;
        this.servicios = servicios;
        //this.progreso = 0;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Beneficiario getBeneficiario() { return beneficiario; }
    public void setBeneficiario(Beneficiario beneficiario) { this.beneficiario = beneficiario; }
    public List<Servicio> getServicios() { return servicios; }
    public void setServicios(List<Servicio> servicios) { this.servicios = servicios; }
    
    // setter getter progreso
}

