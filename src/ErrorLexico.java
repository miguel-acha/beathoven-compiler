public class ErrorLexico {
    String idError;
    String tipoError;
    int posicion;
    String descripcion;
    String accion;

    public ErrorLexico(String idError, String tipoError, int posicion, String descripcion, String accion) {
        this.idError = idError;
        this.tipoError = tipoError;
        this.posicion = posicion;
        this.descripcion = descripcion;
        this.accion = accion;
    }

    @Override
    public String toString() {
        return String.format("%-6s | %-20s | %-8d | %-35s | %s", idError, tipoError, posicion, descripcion, accion);
    }
}
