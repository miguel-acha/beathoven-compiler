package clase;

import java.util.*;

public class Automata {

    static Map<String, Map<String, String>> transiciones = new HashMap<>();
    static Set<String> estadosFinales = new HashSet<>();

    static void agregarTransicion(String desde, String simbolo, String hacia) {
        transiciones.computeIfAbsent(desde, k -> new HashMap<>()).put(simbolo, hacia);
    }

    static String clasificarSimbolo(char c, String estadoActual) {
        if (Character.isWhitespace(c)) return "espacio";
        if (Character.isDigit(c)) return "digito";
        if (c == '#') return "#";
        if (c == 'b') return "b"; // minúscula bemol
        if (c == 'B') return "B"; // mayúscula nota B
        if (c == '/') return "/";
        if (c == '{') return "{";
        if (c == '}') return "}";
        if (c == '[') return "[";
        if (c == ']') return "]";
        if (c == '"') return "\"";

        // Para estados donde se esperan letras en nombres o títulos
        if (estadoActual.equals("SPACE_KEY") || estadoActual.equals("NOTE_KEY") || estadoActual.equals("MOD_KEY") ||
                estadoActual.equals("SPACE_INSTRUMENT") || estadoActual.equals("NAME_INSTRUMENT") ||
                estadoActual.equals("SPACE_MARK") || estadoActual.equals("NAME_M") ||
                estadoActual.equals("SPACE_GOTO") || estadoActual.equals("NAME_G")) {
            if (Character.isLetter(c)) return "letra";
        }

        // Para TITLE contenido entre comillas
        if ((estadoActual.equals("QUOTE_OPEN") || estadoActual.equals("TITLE_CONTENT")) && c != '"') {
            return "contenido";
        }

        if (Character.isLetter(c)) return String.valueOf(c); // distingue mayúsculas/minúsculas

        return "otro";
    }

    static void inicializarAFD() {
        transiciones.clear();
        estadosFinales.clear();

        // INICIO
        agregarTransicion("INICIO", "T", "T1");
        agregarTransicion("INICIO", "K", "K1");
        agregarTransicion("INICIO", "M", "M1");
        // Nota G va directo a NOTE
        agregarTransicion("INICIO", "G", "NOTE");
        // GOTO empieza con G1
        agregarTransicion("INICIO", "G", "G1"); // Para GOTO (conflicto resuelto en simularAFD)
        agregarTransicion("INICIO", "R", "R1");
        agregarTransicion("INICIO", "[", "CHORD_OPEN");
        agregarTransicion("INICIO", "}", "ACCEPT_CLOSE");
        agregarTransicion("INICIO", "I", "I1");
        agregarTransicion("INICIO", "\"", "TITLE_QUOTE");

        // Notas sin B
        for (String nota : List.of("A", "C", "D", "E", "F")) {
            agregarTransicion("INICIO", nota, "NOTE");
        }
        // Nota B y alteración b
        agregarTransicion("INICIO", "B", "B_NOTE");
        agregarTransicion("B_NOTE", "b", "Bb_NOTE");
        agregarTransicion("B_NOTE", "digito", "OCTAVE");
        agregarTransicion("Bb_NOTE", "digito", "OCTAVE");

        // R1: R (rest) o REPEAT
        agregarTransicion("R1", "E", "RE");
        agregarTransicion("R1", "espacio", "SPACE_REST");

        // REPEAT
        agregarTransicion("RE", "P", "REP");
        agregarTransicion("REP", "E", "REPE");
        agregarTransicion("REPE", "A", "REPEA");
        agregarTransicion("REPEA", "T", "REPEAT");
        agregarTransicion("REPEAT", "espacio", "SPACE_REPEAT");
        agregarTransicion("SPACE_REPEAT", "digito", "NUM_REPEAT");
        agregarTransicion("NUM_REPEAT", "digito", "NUM_REPEAT");
        agregarTransicion("NUM_REPEAT", "espacio", "SPACE_REPEAT2");
        agregarTransicion("SPACE_REPEAT2", "{", "BRACE_REPEAT");
        estadosFinales.add("BRACE_REPEAT");

        // Rest (silencio)
        for (String d : List.of("W", "H", "Q", "E", "S")) {
            agregarTransicion("SPACE_REST", d, "DUR_REST");
        }
        estadosFinales.add("DUR_REST");

        // G1: GOTO
        agregarTransicion("G1", "O", "GO");
        agregarTransicion("GO", "T", "GOT");
        agregarTransicion("GOT", "O", "GOTO");
        agregarTransicion("GOTO", "espacio", "SPACE_GOTO");
        agregarTransicion("SPACE_GOTO", "letra", "NAME_G");
        agregarTransicion("NAME_G", "letra", "NAME_G");
        agregarTransicion("NAME_G", "digito", "NAME_G");
        estadosFinales.add("NAME_G");

        // Nota G (ya en NOTE)
        agregarTransicion("NOTE", "#", "MOD_NOTE");
        agregarTransicion("NOTE", "b", "MOD_NOTE");
        agregarTransicion("NOTE", "digito", "OCTAVE");
        agregarTransicion("MOD_NOTE", "digito", "OCTAVE");
        agregarTransicion("OCTAVE", "espacio", "SPACE_NOTE");
        for (String d : List.of("W", "H", "Q", "E", "S")) {
            agregarTransicion("SPACE_NOTE", d, "DUR_NOTE");
        }
        estadosFinales.add("DUR_NOTE");

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
        agregarTransicion("K1", "E", "KE");
        agregarTransicion("KE", "Y", "KEY");
        agregarTransicion("KEY", "espacio", "SPACE_KEY");
        for (String nota : List.of("A", "B", "C", "D", "E", "F", "G")) {
            agregarTransicion("SPACE_KEY", nota, "NOTE_KEY");
        }
        agregarTransicion("SPACE_KEY", "letra", "NOTE_KEY"); // para 'm' de modo menor
        agregarTransicion("NOTE_KEY", "letra", "NOTE_KEY");
        agregarTransicion("NOTE_KEY", "#", "MOD_KEY");
        agregarTransicion("NOTE_KEY", "b", "MOD_KEY");
        agregarTransicion("MOD_KEY", "letra", "MINOR_KEY");
        estadosFinales.add("NOTE_KEY");
        estadosFinales.add("MOD_KEY");
        estadosFinales.add("MINOR_KEY");

        // INSTRUMENT
        agregarTransicion("I1", "N", "IN");
        agregarTransicion("IN", "S", "INS");
        agregarTransicion("INS", "T", "INST");
        agregarTransicion("INST", "R", "INSTR");
        agregarTransicion("INSTR", "U", "INSTRU");
        agregarTransicion("INSTRU", "M", "INSTRUM");
        agregarTransicion("INSTRUM", "E", "INSTRUME");
        agregarTransicion("INSTRUME", "N", "INSTRUMEN");
        agregarTransicion("INSTRUMEN", "T", "INSTRUMENT");
        agregarTransicion("INSTRUMENT", "espacio", "SPACE_INSTRUMENT");
        agregarTransicion("SPACE_INSTRUMENT", "letra", "NAME_INSTRUMENT");
        agregarTransicion("NAME_INSTRUMENT", "letra", "NAME_INSTRUMENT");
        agregarTransicion("NAME_INSTRUMENT", "digito", "NAME_INSTRUMENT");
        estadosFinales.add("NAME_INSTRUMENT");

        // TITLE
        agregarTransicion("T1", "I", "TI");
        agregarTransicion("TI", "T", "TIT");
        agregarTransicion("TIT", "L", "TITL");
        agregarTransicion("TITL", "E", "TITLE");
        agregarTransicion("TITLE", "espacio", "SPACE_TITLE");
        agregarTransicion("SPACE_TITLE", "\"", "QUOTE_OPEN");
        agregarTransicion("QUOTE_OPEN", "contenido", "TITLE_CONTENT");
        agregarTransicion("TITLE_CONTENT", "contenido", "TITLE_CONTENT");
        agregarTransicion("TITLE_CONTENT", "\"", "QUOTE_CLOSE");
        estadosFinales.add("QUOTE_CLOSE");

        // MARK
        agregarTransicion("M1", "A", "MA");
        agregarTransicion("MA", "R", "MAR");
        agregarTransicion("MAR", "K", "MARK");
        agregarTransicion("MARK", "espacio", "SPACE_MARK");
        agregarTransicion("SPACE_MARK", "letra", "NAME_M");
        agregarTransicion("NAME_M", "letra", "NAME_M");
        agregarTransicion("NAME_M", "digito", "NAME_M");
        estadosFinales.add("NAME_M");

        // CHORD
        for (String nota : List.of("A", "B", "C", "D", "E", "F", "G")) {
            agregarTransicion("CHORD_OPEN", nota, "CH_NOTE");
            agregarTransicion("CH_NEXT", nota, "CH_NOTE");
        }
        agregarTransicion("CH_NOTE", "#", "CH_ACC");
        agregarTransicion("CH_NOTE", "b", "CH_ACC");
        agregarTransicion("CH_NOTE", "digito", "CH_OCT");
        agregarTransicion("CH_ACC", "digito", "CH_OCT");
        agregarTransicion("CH_OCT", "espacio", "CH_NEXT");
        agregarTransicion("CH_OCT", "]", "CHORD_CLOSE");
        agregarTransicion("CHORD_CLOSE", "espacio", "CHORD_SPACE");
        for (String d : List.of("W", "H", "Q", "E", "S")) {
            agregarTransicion("CHORD_SPACE", d, "DUR_CHORD");
        }
        estadosFinales.add("DUR_CHORD");

        // Cierre de bloque
        estadosFinales.add("ACCEPT_CLOSE");
    }

    static boolean esLetra(char c) {
        return Character.isLetter(c);
    }

    static boolean esNombreValido(String s) {
        if (s.isEmpty()) return false;
        if (!Character.isLetter(s.charAt(0))) return false;
        for (char c : s.toCharArray()) {
            if (!Character.isLetterOrDigit(c) && c != '_') return false;
        }
        return true;
    }

    static boolean simularAFD(String entradaOriginal) {
        String entrada = entradaOriginal.trim();
        String estadoActual = "INICIO";
        char[] caracteres = entrada.toCharArray();

        StringBuilder numeroTempo = new StringBuilder();
        StringBuilder nombreMarkGoto = new StringBuilder();

        for (int i = 0; i < caracteres.length; i++) {
            char cOriginal = caracteres[i];
            char c = caracteres[i];
            String simbolo = clasificarSimbolo(c, estadoActual);

            if ((estadoActual.equals("SPACE_MARK") || estadoActual.equals("SPACE_GOTO") || estadoActual.equals("NAME_M") || estadoActual.equals("NAME_G")) && esLetra(c)) {
                simbolo = "letra";
            }

            if ((estadoActual.equals("QUOTE_OPEN") || estadoActual.equals("TITLE_CONTENT")) && c != '"') {
                simbolo = "contenido";
            }

            if ((estadoActual.equals("NOTE") || estadoActual.equals("MOD_NOTE") || estadoActual.equals("CH_NOTE") || estadoActual.equals("CH_ACC"))
                    && Character.isDigit(c)) {
                int octava = Character.getNumericValue(c);
                if (octava < 0 || octava > 8) {
                    System.out.println("❌ Error: octava inválida '" + octava + "'. Debe estar entre 0 y 8.");
                    return false;
                }
            }

            Map<String, String> posibles = transiciones.getOrDefault(estadoActual, Collections.emptyMap());
            if (!posibles.containsKey(simbolo)) {
                System.out.println("❌ Error: sin transición desde '" + estadoActual + "' con símbolo '" + simbolo + "' (carácter '" + cOriginal + "')");
                return false;
            }

            String siguiente = posibles.get(simbolo);
            System.out.println("✔️  " + estadoActual + " --[" + simbolo + "]--> " + siguiente);

            if (siguiente.equals("DIGIT_T") || (estadoActual.equals("DIGIT_T") && simbolo.equals("digito"))) {
                numeroTempo.append(c);
            }
            if (siguiente.equals("NAME_M") || siguiente.equals("NAME_G") || estadoActual.equals("NAME_M") || estadoActual.equals("NAME_G")) {
                if (simbolo.equals("letra") || Character.isDigit(c) || c == '_') {
                    nombreMarkGoto.append(cOriginal);
                }
            }

            estadoActual = siguiente;
        }

        if (!estadosFinales.contains(estadoActual)) {
            System.out.println("❌ Estado final '" + estadoActual + "' no es de aceptación.");
            return false;
        }

        if (estadoActual.equals("DIGIT_T")) {
            int tempo = Integer.parseInt(numeroTempo.toString());
            if (tempo < 20 || tempo > 300) {
                System.out.println("❌ Tempo fuera de rango válido (20-300).");
                return false;
            }
        }

        if (estadoActual.equals("NAME_M") || estadoActual.equals("NAME_G")) {
            if (!esNombreValido(nombreMarkGoto.toString())) {
                System.out.println("❌ Nombre para MARK/GOTO inválido.");
                return false;
            }
        }

        System.out.println("✅ Instrucción válida. Estado final: " + estadoActual);
        return true;
    }

    public static void main(String[] args) {
        inicializarAFD();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese instrucciones musicales (escribe SALIR para terminar):");

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
