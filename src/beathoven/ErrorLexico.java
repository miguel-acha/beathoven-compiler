package beathoven;

public class ErrorLexico {
    String id, tipo, descripcion, sugerencia;
    int linea;

    public ErrorLexico(String id, String tipo, int linea, String descripcion, String sugerencia) {
        this.id = id;
        this.tipo = tipo;
        this.linea = linea;
        this.descripcion = descripcion;
        this.sugerencia = sugerencia;
    }

    public String toString() {
        return String.format("%-6s | %-20s | %-8d | %-35s | %s", id, tipo, linea, descripcion, sugerencia);

    }
}
