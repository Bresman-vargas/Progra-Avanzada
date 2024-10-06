package logica;

import java.io.Serializable;
import java.util.Map;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
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
    @MapKey(name = "id") // El ID del servicio será la clave en el mapa
    private Map<Long, Servicio> servicios; // Mapa de Servicios donde la clave es el ID
    
    // Progreso
    private int Progreso;

    // Constructor por defecto
    public Asignacion() {
        this.Progreso = 0;
    }

    // Constructor
    public Asignacion(Beneficiario beneficiario, Map<Long, Servicio> servicios) {
        this.beneficiario = beneficiario;
        this.servicios = servicios;
        this.Progreso = 0;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Beneficiario getBeneficiario() { return beneficiario; }
    public void setBeneficiario(Beneficiario beneficiario) { this.beneficiario = beneficiario; }

    public Map<Long, Servicio> getServicios() { return servicios; }
    public void setServicios(Map<Long, Servicio> servicios) { this.servicios = servicios; }

    public int getProgreso() { return Progreso; }
    public void setProgreso(int Progreso) { this.Progreso = Progreso; }
}
