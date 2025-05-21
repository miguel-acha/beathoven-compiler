import java.util.ArrayList;
import java.util.List;

public class Simbolo {
    String idToken;
    String tipoToken;
    String valor;
    List<Integer> posiciones;

    public Simbolo(String idToken, String tipoToken, String valor, int posicion) {
        this.idToken = idToken;
        this.tipoToken = tipoToken;
        this.valor = valor;
        this.posiciones = new ArrayList<>();
        this.posiciones.add(posicion);
    }

    public void agregarPosicion(int posicion) {
        posiciones.add(posicion);
    }

    @Override
    public String toString() {
        return String.format("%-6s | %-13s | %-20s | %s", idToken, tipoToken, valor, posiciones.toString().replaceAll("[\\[\\]]", ""));
    }
}
