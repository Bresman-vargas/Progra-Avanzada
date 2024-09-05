/* 
package systemdisability;

public class ProgresoBeneficiario {
    private Beneficiario beneficiario;
    private Servicio servicio;
    private int etapaProgreso;  // Rango de 1 a 10
    private String fechaActualizacion;  // Fecha de la última actualización
    
    public Progreso(Beneficiario beneficiario, Servicio servicio) {
        this.beneficiario = beneficiario;
        this.servicio = servicio;
        this.etapaProgreso = 1; 
        this.fechaActualizacion = Utilidades.obtenerFechaActual(); 
    }
    public void actualizarProgreso(int nuevaEtapa) {
        if (nuevaEtapa >= 1 && nuevaEtapa <= 10) {
            this.etapaProgreso = nuevaEtapa;
            this.fechaActualizacion = Utilidades.obtenerFechaActual();
        } else {
            System.out.println("Etapa fuera de rango. Debe ser entre 1 y 10.");
        }
    } 
    
}
*/