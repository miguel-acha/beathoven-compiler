import java.util.*;

public class Main2 {

    static Map<String, Map<String, String>> transiciones = new HashMap<>();
    static Set<String> estadosFinales = new HashSet<>();

    static String clasificarSimbolo(char c) {
        if (Character.isWhitespace(c)) return "espacio";
        if (Character.isDigit(c)) return "digito";
        if (Character.isLetter(c)) return String.valueOf(Character.toUpperCase(c));
        if ("#b".indexOf(c) >= 0) return "alteracion";
        if (c == '/') return "/";
        if (c == '{') return "{";
        if (c == '}') return "}";
        if (c == '\"') return "comilla";
        if (c == '[') return "[";
        if (c == ']') return "]";
        return "otro";
    }

    static void agregarTransicion(String desde, String simbolo, String hacia) {
        transiciones.computeIfAbsent(desde, k -> new HashMap<>()).put(simbolo, hacia);
    }

    static void inicializarAFD() {
        transiciones.clear();
        estadosFinales.clear();

        // INICIO
        agregarTransicion("INICIO", "T", "T1");
        agregarTransicion("INICIO", "K", "K");
        agregarTransicion("INICIO", "R", "R_REST");
        agregarTransicion("INICIO", "}", "ACCEPT_CLOSE");
        agregarTransicion("INICIO", "[", "CHORD_OPEN");
        for (String nota : List.of("A", "B", "C", "D", "E", "F", "G")) {
            agregarTransicion("INICIO", nota, "NOTE");
        }

        // TEMPO
        agregarTransicion("T1", "E", "TE");
        agregarTransicion("TE", "M", "TEM");
        agregarTransicion("TEM", "P", "TEMP");
        agregarTransicion("TEMP", "O", "TEMPO_FINAL");
        agregarTransicion("TEMPO_FINAL", "espacio", "SPACE_T");
        agregarTransicion("SPACE_T", "digito", "DIGIT_T");
        agregarTransicion("DIGIT_T", "digito", "DIGIT_T");
        estadosFinales.add("DIGIT_T");

        // TIME
        agregarTransicion("T1", "I", "TI");
        agregarTransicion("TI", "M", "TIM");
        agregarTransicion("TIM", "E", "TIME");
        agregarTransicion("TIME", "espacio", "SPACE_TIME");
        agregarTransicion("SPACE_TIME", "digito", "NUM1");
        agregarTransicion("NUM1", "digito", "NUM1");
        agregarTransicion("NUM1", "/", "SLASH");
        agregarTransicion("SLASH", "digito", "NUM2");
        agregarTransicion("NUM2", "digito", "NUM2");
        estadosFinales.add("NUM2");

        // KEY
        agregarTransicion("K", "E", "KE");
        agregarTransicion("KE", "Y", "KEY");
        agregarTransicion("KEY", "espacio", "SPACE_KEY");
        for (String nota : List.of("A", "B", "C", "D", "E", "F", "G")) {
            agregarTransicion("SPACE_KEY", nota, "NOTE_KEY");
        }
        agregarTransicion("NOTE_KEY", "alteracion", "MOD_KEY");
        agregarTransicion("MOD_KEY", "M", "MINOR");
        agregarTransicion("NOTE_KEY", "M", "MINOR");
        estadosFinales.add("NOTE_KEY");
        estadosFinales.add("MOD_KEY");
        estadosFinales.add("MINOR");

        // REST
        agregarTransicion("R_REST", "espacio", "SPACE_REST");
        for (String d : List.of("W", "H", "Q", "E", "S")) {
            agregarTransicion("SPACE_REST", d, "DUR_REST");
        }
        estadosFinales.add("DUR_REST");

        // NOTE
        agregarTransicion("NOTE", "alteracion", "MOD_NOTE");
        agregarTransicion("NOTE", "digito", "OCTAVE");
        agregarTransicion("MOD_NOTE", "digito", "OCTAVE");
        agregarTransicion("OCTAVE", "espacio", "SPACE_NOTE");
        for (String d : List.of("W", "H", "Q", "E", "S")) {
            agregarTransicion("SPACE_NOTE", d, "DUR_NOTE");
        }
        estadosFinales.add("DUR_NOTE");

        // CHORD
        for (String nota : List.of("A", "B", "C", "D", "E", "F", "G")) {
            agregarTransicion("CHORD_OPEN", nota, "CH_NOTE");
            agregarTransicion("CH_NEXT", nota, "CH_NOTE");
        }
        agregarTransicion("CH_NOTE", "alteracion", "CH_ACC");
        agregarTransicion("CH_NOTE", "digito", "CH_OCT");
        agregarTransicion("CH_ACC", "digito", "CH_OCT");
        agregarTransicion("CH_OCT", "espacio", "CH_NEXT");
        agregarTransicion("CH_OCT", "]", "CHORD_CLOSE");
        agregarTransicion("CHORD_CLOSE", "espacio", "CHORD_SPACE");
        for (String d : List.of("W", "H", "Q", "E", "S")) {
            agregarTransicion("CHORD_SPACE", d, "DUR_CHORD");
        }
        estadosFinales.add("DUR_CHORD");

        // CLOSE
        estadosFinales.add("ACCEPT_CLOSE");
    }

    public static boolean simularAFD(String entrada) {
        String estadoActual = "INICIO";
        char[] caracteres = entrada.trim().toCharArray();

        for (int i = 0; i < caracteres.length; i++) {
            String simbolo = clasificarSimbolo(caracteres[i]);

            // Validación de octava
            if ((estadoActual.equals("NOTE") || estadoActual.equals("MOD_NOTE") ||
                    estadoActual.equals("CH_NOTE") || estadoActual.equals("CH_ACC")) &&
                    Character.isDigit(caracteres[i])) {

                int octava = Character.getNumericValue(caracteres[i]);
                if (octava < 0 || octava > 8) {
                    System.out.println("❌ Error: octava inválida '" + octava + "'. Debe estar entre 0 y 8.");
                    return false;
                }
            }

            Map<String, String> posibles = transiciones.getOrDefault(estadoActual, new HashMap<>());
            if (!posibles.containsKey(simbolo)) {
                System.out.println("❌ Error: sin transición desde '" + estadoActual + "' con '" + simbolo + "'");
                return false;
            }

            String siguiente = posibles.get(simbolo);
            System.out.println("✔️  " + estadoActual + " --[" + simbolo + "]--> " + siguiente);
            estadoActual = siguiente;
        }

        if (estadosFinales.contains(estadoActual)) {
            System.out.println("✅ Instrucción válida. Estado final: " + estadoActual);
            return true;
        } else {
            System.out.println("❌ Estado final '" + estadoActual + "' no es de aceptación.");
            return false;
        }
    }

    public static void main(String[] args) {
        inicializarAFD(); // ← ¡No debe faltar!
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese instrucciones musicales (SALIR para terminar):");

        while (true) {
            System.out.print("> ");
            String linea = scanner.nextLine();

            if (linea.equalsIgnoreCase("SALIR")) break;

            boolean aceptado = simularAFD(linea);
            System.out.println(aceptado ? "✔️ Aceptado.\n" : "❌ Rechazado.\n");
        }

        scanner.close();
    }
}