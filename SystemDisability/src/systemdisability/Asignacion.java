/* 
package systemdisability;

 *
 * @author bresm

public class Asignacion {
    private String id;
    private Beneficiario beneficiario;
    private Servicio servicio;
    private String fecha;
    private String hora; 

    public Asignacion(Beneficiario beneficiario, Servicio servicio, String fecha, String hora) {
        this.id = Utilidades.generateUniqueId();
        this.beneficiario = beneficiario;
        this.servicio = servicio;
        this.fecha = fecha;
        this.hora = hora;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public Beneficiario getBeneficiario() { return beneficiario; }
    public void setBeneficiario(Beneficiario beneficiario) { this.beneficiario = beneficiario; }

    public Servicio getServicio() { return servicio; }
    public void setServicio(Servicio servicio) { this.servicio = servicio; }

    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }

    public String getHora() { return hora; }
    public void setHora(String hora) { this.hora = hora; }

    @Override
    public String toString() {
        return "ID: " + id + ", Beneficiario: " + beneficiario.getNombre() + ", Servicio: " + servicio.getNombre() + ", Fecha: " + fecha + ", Hora: " + hora;
    }
}
*/