import java.util.*;

public class Main {
    static class Estado {
        String nombre;
        boolean esFinal;

        Estado(String nombre, boolean esFinal) {
            this.nombre = nombre;
            this.esFinal = esFinal;
        }
    }

    static Map<String, Map<String, String>> transiciones = new HashMap<>();
    static Set<String> estadosFinales = new HashSet<>();

    static {
        Map<String, String> inicio = new HashMap<>();
        inicio.put("TEMPO", "T");
        inicio.put("TIME", "TI");
        inicio.put("KEY", "K");
        inicio.put("INSTRUMENT", "I");
        inicio.put("TITLE", "TITLE");
        inicio.put("NOTE", "NOTE");
        inicio.put("R", "R");
        inicio.put("[", "CHORD");
        inicio.put("REPEAT", "REPEAT");
        inicio.put("MARK", "MARK");
        inicio.put("GOTO", "GOTO");
        inicio.put("}", "CLOSE");

        transiciones.put("INICIO", inicio);

        estadosFinales.addAll(Arrays.asList(
                "ACCEPT_TEMPO", "ACCEPT_TIME", "ACCEPT_KEY", "ACCEPT_INST",
                "ACCEPT_TITLE", "ACCEPT_NOTE", "ACCEPT_REST", "ACCEPT_CHORD",
                "ACCEPT_REPEAT", "ACCEPT_MARK", "ACCEPT_GOTO", "ACCEPT_CLOSE"
        ));
    }

    public static String clasificar(String linea) {
        linea = linea.trim();

        if (linea.matches("^TEMPO\\s+\\d+$")) return "ACCEPT_TEMPO";
        if (linea.matches("^TIME\\s+\\d+/\\d+$")) return "ACCEPT_TIME";
        if (linea.matches("^KEY\\s+[A-G](#|b)?m?$")) return "ACCEPT_KEY";
        if (linea.matches("^INSTRUMENT\\s+[A-Za-z]+$")) return "ACCEPT_INST";
        if (linea.matches("^TITLE\\s+\\\".+\\\"$")) return "ACCEPT_TITLE";
        if (linea.matches("^[A-G](#|b)?[0-8]\\s+(W|H|Q|E|S)$")) return "ACCEPT_NOTE";
        if (linea.matches("^R\\s+(W|H|Q|E|S)$")) return "ACCEPT_REST";
        if (linea.matches("^\\[[A-G#b0-8\\s]+\\]\\s+(W|H|Q|E|S)$")) return "ACCEPT_CHORD";
        if (linea.matches("^REPEAT\\s+\\d+\\s+\\{$")) return "ACCEPT_REPEAT";
        if (linea.matches("^MARK\\s+[a-zA-Z_][a-zA-Z0-9_]*$")) return "ACCEPT_MARK";
        if (linea.matches("^GOTO\\s+[a-zA-Z_][a-zA-Z0-9_]*$")) return "ACCEPT_GOTO";
        if (linea.equals("}")) return "ACCEPT_CLOSE";

        return "ERROR";
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Escribe instrucciones musicales. Escribe SALIR para terminar.");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("SALIR")) {
                break;
            }

            String resultado = clasificar(input);
            if (estadosFinales.contains(resultado)) {
                System.out.println("Instrucción válida: " + resultado);
            } else {
                System.out.println("Instrucción NO válida.");
            }
        }

        scanner.close();
    }
}
