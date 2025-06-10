import java.util.*;

public class PracticaAutomataExamen {

    static Map<String, Map<String, String>> transiciones = new HashMap<>();
    static Set<String> estadosFinales = new HashSet<>();

    static void agregarTransicion(String desde, String simbolo, String hacia) {
        transiciones.computeIfAbsent(desde, k -> new HashMap<>()).put(simbolo, hacia);
    }

    static void inicializarAFD() {
        transiciones.clear();
        estadosFinales.clear();

        // Estado inicial
        agregarTransicion("q0", "0", "q1_0");
        agregarTransicion("q0", "1", "q1_1");

        // Longitud 2
        agregarTransicion("q1_0", "0", "q2_00");
        agregarTransicion("q1_0", "1", "q2_01");
        agregarTransicion("q1_1", "0", "q2_10");
        agregarTransicion("q1_1", "1", "q2_11");

        // Longitud 3
        agregarTransicion("q2_00", "0", "q3_000");
        agregarTransicion("q2_00", "1", "q3_001");
        agregarTransicion("q2_01", "0", "q3_010");
        agregarTransicion("q2_01", "1", "q3_011");
        agregarTransicion("q2_10", "0", "q3_100");
        agregarTransicion("q2_10", "1", "q3_101");
        agregarTransicion("q2_11", "0", "q3_110");
        agregarTransicion("q2_11", "1", "q3_111");

        // Longitud 4
        agregarTransicion("q3_000", "0", "q4_0000");
        agregarTransicion("q3_001", "0", "q4_0010");
        agregarTransicion("q3_010", "1", "q4_0101");
        agregarTransicion("q3_011", "0", "q4_0110");
        agregarTransicion("q3_011", "1", "q4_0111");
        agregarTransicion("q3_100", "0", "q4_1000");
        agregarTransicion("q3_100", "1", "q4_1001");
        agregarTransicion("q3_101", "0", "q4_1010");
        agregarTransicion("q3_110", "1", "q4_1101");
        agregarTransicion("q3_111", "1", "q4_1111");

        // Longitud 5 (solo palíndromos válidos)
        agregarTransicion("q4_0000", "0", "q5_00000");
        agregarTransicion("q4_0010", "0", "q5_00100");
        agregarTransicion("q4_0101", "0", "q5_01010");
        // ❌ No se agrega transición para "01100"
        agregarTransicion("q4_0111", "0", "q5_01110"); // palíndromo válido
        agregarTransicion("q4_1000", "1", "q5_10001");
        agregarTransicion("q4_1001", "1", "q5_10011");
        agregarTransicion("q4_1010", "1", "q5_10101");
        agregarTransicion("q4_1101", "1", "q5_11011");
        agregarTransicion("q4_1111", "1", "q5_11111");

        // Estados finales válidos
        Collections.addAll(estadosFinales,
                "q1_0", "q1_1",
                "q2_00", "q2_11",
                "q3_000", "q3_010", "q3_101", "q3_111",
                "q4_0000", "q4_0110", "q4_1001", "q4_1111",
                "q5_00000", "q5_00100", "q5_01010", "q5_01110", "q5_10001",
                "q5_10101", "q5_11011", "q5_11111"
        );
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
        }

        if (estadosFinales.contains(estadoActual)) {
            System.out.println("✅ Es palíndromo binario de hasta 5 bits");
            return true;
        } else {
            System.out.println("❌ No es palíndromo binario válido");
            return false;
        }
    }

    public static void main(String[] args) {
        inicializarAFD();

        List<String> pruebas = new ArrayList<>();

        // Generar todas las combinaciones binarias de longitud 1 a 5
        for (int n = 1; n <= 5; n++) {
            int total = (int) Math.pow(2, n);
            for (int i = 0; i < total; i++) {
                String bin = String.format("%" + n + "s", Integer.toBinaryString(i)).replace(' ', '0');
                pruebas.add(bin);
            }
        }

        for (String entrada : pruebas) {
            System.out.println("\nProbando: " + entrada);
            boolean resultado = simularAFD(entrada);
            if (!resultado) {
                System.out.println("🚫 No es palíndromo: " + entrada);
            }
        }
    }
}
