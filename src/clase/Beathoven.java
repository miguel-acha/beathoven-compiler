import java.io.*;
import java.util.*;

public class Beathoven {

    static int tokenCounter = 1, errorCounter = 1;
    static List<String> tokensSintacticos = new ArrayList<>();
    static Stack<String> pila = new Stack<>();
    static Map<String, Map<String, List<String>>> tablaParse;
    static Map<String, Simbolo> tablaSimbolos = new LinkedHashMap<>();
    static List<ErrorLexico> tablaErrores = new ArrayList<>();

    static class Simbolo {
        String id, tipo, valor;
        List<Integer> posiciones;
        public Simbolo(String id, String tipo, String valor, int linea) {
            this.id = id; this.tipo = tipo; this.valor = valor;
            this.posiciones = new ArrayList<>();
            this.posiciones.add(linea);
        }
        public void agregarPosicion(int linea) { this.posiciones.add(linea); }
        public String toString() {
            return String.format("%-6s | %-15s | %-20s | %s", id, tipo, valor, posiciones.toString());
        }
    }

    static class ErrorLexico {
        String id, tipo, descripcion, sugerencia;
        int linea;
        public ErrorLexico(String id, String tipo, int linea, String descripcion, String sugerencia) {
            this.id = id; this.tipo = tipo; this.linea = linea; this.descripcion = descripcion; this.sugerencia = sugerencia;
        }
        public String toString() {
            return String.format("%-6s | %-20s | %-8d | %-35s | %s", id, tipo, linea, descripcion, sugerencia);
        }
    }

    static String generarIDToken() { return String.format("T%03d", tokenCounter++); }
    static String generarIDError() { return String.format("E%03d", errorCounter++); }

    public static String obtenerTipo(String token) {
        if (token.matches("\\d+")) return "NUM";
        if (token.matches("\\d+/\\d+")) return "FRACCION";
        if (token.matches("[A-G](#|b)?[0-8]?")) return "NOTA_SIMPLE";
        if (token.matches("TEMPO|TIME|KEY|INSTRUMENT|TITLE|R|REPEAT|MARK|GOTO|\\{|\\}")) return token;
        if (token.matches("W|H|Q|E|S")) return "DUR";
        if (token.matches("\".*\"")) return "CADENA";
        if (token.matches("\\[.*\\]")) return "ACORDE";
        if (token.matches("[A-Za-z_][A-Za-z0-9_]*")) return "ID";
        return "ERROR";
    }

    public static List<String> extraerTokens(String linea) {
        List<String> tokens = new ArrayList<>();
        StringBuilder actual = new StringBuilder();
        boolean dentroComillas = false, dentroCorchetes = false;
        for (char c : linea.toCharArray()) {
            if (c == '\"') dentroComillas = !dentroComillas;
            if (c == '[') dentroCorchetes = true;
            if (c == ']') dentroCorchetes = false;
            if (Character.isWhitespace(c) && !dentroComillas && !dentroCorchetes) {
                if (actual.length() > 0) {
                    tokens.add(actual.toString()); actual.setLength(0);
                }
            } else actual.append(c);
        }
        if (actual.length() > 0) tokens.add(actual.toString());
        return tokens;
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Uso: java Beathoven archivo.beat");
            return;
        }

        System.out.println("\nANALIZADOR LEXICO\n");

        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String linea; int numeroLinea = 0;
            while ((linea = br.readLine()) != null) {
                numeroLinea++;
                for (String token : extraerTokens(linea.trim())) {
                    String tipo = obtenerTipo(token);
                    if (tipo.equals("ERROR")) {
                        tablaErrores.add(new ErrorLexico(
                                generarIDError(), "Token inválido", numeroLinea,
                                "Token no reconocido: " + token, "Revisar ortografía"
                        ));
                    } else {
                        tokensSintacticos.add(tipo);
                        if (tablaSimbolos.containsKey(token)) {
                            tablaSimbolos.get(token).agregarPosicion(numeroLinea);
                        } else {
                            tablaSimbolos.put(token, new Simbolo(generarIDToken(), tipo, token, numeroLinea));
                        }
                    }
                }
            }
            tokensSintacticos.add("$");
        } catch (IOException e) {
            System.err.println("Error al leer archivo: " + e.getMessage());
            return;
        }

        System.out.println("--- Tabla de Símbolos ---");
        System.out.printf("ID     | Tipo de Token  | Valor                | Posición\n");
        for (Simbolo s : tablaSimbolos.values()) System.out.println(s);

        inicializarTabla();
        pila.clear();
        pila.push("$");
        pila.push("S");
        int pos = 0;

        System.out.println("\nANALIZADOR SINTACTICO\n");

        while (!pila.isEmpty()) {
            String tope = pila.peek();
            String actual = tokensSintacticos.get(pos);
            System.out.printf("Pila: %-30s Entrada: %-30s ", stackToString(pila), inputToString(tokensSintacticos, pos));

            if (tope.equals(actual)) {
                pila.pop(); pos++;
                System.out.println("✓ match " + actual);
            } else if (isTerminal(tope)) {
                System.out.println("✗ error, token inesperado '" + actual + "'");
                tablaErrores.add(new ErrorLexico(
                        generarIDError(), "Error Sintáctico", pos + 1,
                        "Token inesperado: " + actual, "Revisar la gramática o la secuencia de tokens"
                ));
                break;
            } else {
                List<String> produccion = getProduccion(tope, actual);
                if (produccion == null) {
                    System.out.println("✗ error, sin producción para [" + tope + ", " + actual + "]");
                    tablaErrores.add(new ErrorLexico(
                            generarIDError(), "Error Sintáctico", pos + 1,
                            "No hay producción para [" + tope + ", " + actual + "]", "Revisar la regla de la gramática"
                    ));
                    break;
                } else {
                    pila.pop();
                    if (!produccion.get(0).equals("ε")) {
                        for (int i = produccion.size() - 1; i >= 0; i--) pila.push(produccion.get(i));
                    }
                    System.out.println(tope + " → " + String.join(" ", produccion));
                }
            }
        }

        boolean aceptado = pila.isEmpty() && pos == tokensSintacticos.size();

        if (aceptado) System.out.println("\n✔ ACEPTADO");
        else System.out.println("\n✘ RECHAZADO");

        // ANALIZADOR SEMANTICO (siempre se ejecuta)
        System.out.println("\nANALIZADOR SEMANTICO\n");

        Set<String> marcasDefinidas = new HashSet<>();
        Set<String> marcasUsadas = new HashSet<>();
        boolean tempoOK = false, timeOK = false;
        Set<String> compasesValidos = Set.of("2/4", "3/4", "4/4", "6/8", "12/8");

        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String linea; int numLinea = 0;
            while ((linea = br.readLine()) != null) {
                numLinea++;
                linea = linea.trim();
                if (linea.isEmpty()) continue;

                List<String> partes = extraerTokens(linea);
                if (partes.isEmpty()) continue;

                String instruccion = partes.get(0);

                switch (instruccion) {
                    case "TEMPO":
                        tempoOK = true;
                        if (partes.size() != 2 || !partes.get(1).matches("\\d+")) {
                            tablaErrores.add(new ErrorLexico(generarIDError(), "Error Semántico", numLinea, "Valor inválido para TEMPO", "Debe ser número entre 20 y 300"));
                        } else {
                            int valorTempo = Integer.parseInt(partes.get(1));
                            if (valorTempo < 20 || valorTempo > 300) {
                                tablaErrores.add(new ErrorLexico(generarIDError(), "Error Semántico", numLinea, "TEMPO fuera de rango", "Use un valor entre 20 y 300"));
                            }
                        }
                        break;
                    case "TIME":
                        timeOK = true;
                        if (partes.size() != 2 || !compasesValidos.contains(partes.get(1))) {
                            tablaErrores.add(new ErrorLexico(generarIDError(), "Error Semántico", numLinea, "Compás TIME inválido: " + partes.get(1), "Use 2/4, 3/4, 4/4, 6/8 o 12/8"));
                        }
                        break;
                    case "TITLE":
                        if (partes.size() < 2 || !linea.contains("\"")) {
                            tablaErrores.add(new ErrorLexico(generarIDError(), "Error Semántico", numLinea, "Título inválido", "Use comillas dobles para el título"));
                        }
                        break;
                    case "MARK":
                        if (partes.size() != 2) {
                            tablaErrores.add(new ErrorLexico(generarIDError(), "Error Semántico", numLinea, "Nombre de MARK inválido", "Use un identificador válido"));
                        } else marcasDefinidas.add(partes.get(1));
                        break;
                    case "GOTO":
                        if (partes.size() != 2) {
                            tablaErrores.add(new ErrorLexico(generarIDError(), "Error Semántico", numLinea, "Nombre de GOTO inválido", "Use un identificador válido"));
                        } else marcasUsadas.add(partes.get(1));
                        break;
                    default:
                        break;
                }
            }

            if (!tempoOK) tablaErrores.add(new ErrorLexico(generarIDError(), "Error Semántico", 0, "Falta instrucción TEMPO", "Agregue TEMPO al inicio del archivo"));
            if (!timeOK) tablaErrores.add(new ErrorLexico(generarIDError(), "Error Semántico", 0, "Falta instrucción TIME", "Agregue TIME al inicio del archivo"));

            for (String usada : marcasUsadas) {
                if (!marcasDefinidas.contains(usada)) {
                    tablaErrores.add(new ErrorLexico(generarIDError(), "Error Semántico", 0, "GOTO a marca no definida: " + usada, "Defina la marca con MARK " + usada));
                }
            }

        } catch (IOException e) {
            System.err.println("Error al leer archivo en semántico: " + e.getMessage());
            return;
        }

        // Mostrar tabla de errores final una vez
        System.out.println("\n--- Tabla de Errores (Léxicos + Sintácticos + Semánticos) ---");
        if (tablaErrores.isEmpty()) {
            System.out.println("✔ No se encontraron errores.");
        } else {
            System.out.printf("ID     | Tipo de Error        | Línea    | Descripción                         | Acción\n");
            for (ErrorLexico e : tablaErrores) System.out.println(e);
        }
    }

    static void inicializarTabla() {
        tablaParse = new HashMap<>();
        Map<String, List<String>> rowS = new HashMap<>();
        for (String t : Arrays.asList("TEMPO", "TIME", "KEY", "TITLE", "INSTRUMENT", "R", "REPEAT", "MARK", "GOTO", "NOTA_SIMPLE", "ACORDE")) {
            rowS.put(t, Arrays.asList("BLOQUES"));
        }
        tablaParse.put("S", rowS);

        Map<String, List<String>> rowBloques = new HashMap<>();
        List<String> instBloques = Arrays.asList("INST", "BLOQUES");
        for (String t : rowS.keySet()) rowBloques.put(t, instBloques);
        rowBloques.put("}", Arrays.asList("ε"));
        rowBloques.put("$", Arrays.asList("ε"));
        tablaParse.put("BLOQUES", rowBloques);

        Map<String, List<String>> rowInst = new HashMap<>();
        rowInst.put("TEMPO", Arrays.asList("TEMPO", "NUM"));
        rowInst.put("TIME", Arrays.asList("TIME", "FRACCION"));
        rowInst.put("KEY", Arrays.asList("KEY", "NOTA_SIMPLE"));
        rowInst.put("TITLE", Arrays.asList("TITLE", "CADENA"));
        rowInst.put("INSTRUMENT", Arrays.asList("INSTRUMENT", "ID"));
        rowInst.put("R", Arrays.asList("R", "DUR"));
        rowInst.put("REPEAT", Arrays.asList("REPEAT", "NUM", "{", "BLOQUES", "}"));
        rowInst.put("MARK", Arrays.asList("MARK", "ID"));
        rowInst.put("GOTO", Arrays.asList("GOTO", "ID"));
        rowInst.put("NOTA_SIMPLE", Arrays.asList("NOTA_SIMPLE", "DUR"));
        rowInst.put("ACORDE", Arrays.asList("ACORDE", "DUR"));
        tablaParse.put("INST", rowInst);
    }

    static boolean isTerminal(String symbol) {
        return Arrays.asList("TEMPO", "NUM", "TIME", "FRACCION", "KEY", "NOTA_SIMPLE", "TITLE", "CADENA",
                "INSTRUMENT", "ID", "R", "DUR", "REPEAT", "{", "}", "MARK", "GOTO", "ACORDE", "$").contains(symbol);
    }

    static List<String> getProduccion(String noTerminal, String token) {
        Map<String, List<String>> row = tablaParse.get(noTerminal);
        if (row == null) return null;
        return row.get(token);
    }

    static String stackToString(Stack<String> s) {
        return String.join(" ", s);
    }

    static String inputToString(List<String> input, int pos) {
        return String.join(" ", input.subList(pos, input.size()));
    }
}
