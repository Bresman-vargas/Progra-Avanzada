package logica;

import persistencia.ControladoraPersistencia;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.swing.JOptionPane;
import java.io.Serializable;

@Entity
public class AsignacionServicio implements Serializable { // Implementar Serializable
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Generar ID automáticamente
    private Long id; // ID único para la entidad

    @ManyToOne // Relación muchos a uno con Beneficiario
    @JoinColumn(name = "beneficiario_id") // Cambia el nombre según tu esquema
    private Beneficiario beneficiario;

    @ManyToOne // Relación muchos a uno con Servicio
    @JoinColumn(name = "servicio_id") // Cambia el nombre según tu esquema
    private Servicio servicio;

    // Constructor vacío requerido por JPA
    public AsignacionServicio() {}

    // Constructor
    public AsignacionServicio(Beneficiario beneficiario, Servicio servicio) {
        this.beneficiario = beneficiario;
        this.servicio = servicio;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Beneficiario getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(Beneficiario beneficiario) {
        this.beneficiario = beneficiario;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    // Método para guardar la relación en la base de datos
    public void guardar() {
        ControladoraPersistencia controladoraPersistencia = new ControladoraPersistencia();

        try {
            controladoraPersistencia.guardarAsignacionServicio(this); // Guarda la asignación
            JOptionPane.showMessageDialog(null, "El servicio se ha asignado correctamente al beneficiario.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al asignar el servicio: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}//xaxsdas

