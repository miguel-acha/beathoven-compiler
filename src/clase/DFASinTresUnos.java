package clase;

import java.util.*;

public class DFASinTresUnos {

    static Map<String, Map<String, String>> transiciones = new HashMap<>();
    static Set<String> estadosFinales = new HashSet<>();

    static void agregarTransicion(String desde, String simbolo, String hacia) {
        transiciones.computeIfAbsent(desde, k -> new HashMap<>()).put(simbolo, hacia);
    }

    static void inicializarAFD() {
        transiciones.clear();
        estadosFinales.clear();

        // Estados:
        // q0: inicio (ningún 1 aún)
        // q1: un '1' visto
        // q2: dos '1's consecutivos
        // q3: tres '1's consecutivos (estado de rechazo)

        agregarTransicion("q0", "0", "q0");
        agregarTransicion("q0", "1", "q1");

        agregarTransicion("q1", "0", "q0");
        agregarTransicion("q1", "1", "q2");

        agregarTransicion("q2", "0", "q0");
        agregarTransicion("q2", "1", "q3"); // estado de error

        // q3 no tiene transiciones válidas

        // Estados válidos (aceptación)
        estadosFinales.addAll(Arrays.asList("q0", "q1", "q2"));
    }

    static boolean simularAFD(String entrada) {
        String estadoActual = "q0";
        for (char c : entrada.toCharArray()) {
            String simbolo = String.valueOf(c);
            Map<String, String> posibles = transiciones.getOrDefault(estadoActual, Collections.emptyMap());
            if (!posibles.containsKey(simbolo)) {
                System.out.println("❌ No hay transición desde " + estadoActual + " con '" + simbolo + "'");
                return false;
            }
            estadoActual = posibles.get(simbolo);
            System.out.println("✔️  Transición: " + estadoActual);
            if (estadoActual.equals("q3")) {
                System.out.println("❌ Rechazado por contener '111'");
                return false;
            }
        }

        if (estadosFinales.contains(estadoActual)) {
            System.out.println("✅ Cadena válida");
            return true;
        } else {
            System.out.println("❌ Estado final inválido");
            return false;
        }
    }

    public static void main(String[] args) {
        inicializarAFD();

        List<String> pruebas = Arrays.asList(
                "0", "1", "10", "101", "1101", "111", "1110", "01110", "11011", "000", "101010", "1111", "0110011100011"
        );

        for (String entrada : pruebas) {
            System.out.println("\nProbando: " + entrada);
            boolean resultado = simularAFD(entrada);
            if (!resultado) {
                System.out.println("🚫 Comando inválido: " + entrada);
            }
        }
    }
}
