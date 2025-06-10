package beathoven;

import java.util.*;

public class Simbolo {
    String id, tipo, valor;
    List<Integer> posiciones;

    public Simbolo(String id, String tipo, String valor, int linea) {
        this.id = id;
        this.tipo = tipo;
        this.valor = valor;
        this.posiciones = new ArrayList<>();
        this.posiciones.add(linea);
    }

    public void agregarPosicion(int linea) {
        this.posiciones.add(linea);
    }

    public String toString() {
        return String.format("%-6s | %-15s | %-20s | %s", id, tipo, valor, posiciones.toString());
    }
}
