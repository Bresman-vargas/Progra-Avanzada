package systemdisability;

public class ProgresoBeneficiario {
    private final String servicioId;
    private int progreso; // Valor de progreso del 1 al 10

    public ProgresoBeneficiario(String servicioId, int progresoInicial) {
        this.servicioId = servicioId;
        this.progreso = progresoInicial;
    }

    public String getServicioId() {
        return servicioId;
    }

    public int getProgreso() {
        return progreso;
    }

    public void setProgreso(int progreso) {
        this.progreso = progreso;
    }
}
