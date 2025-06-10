package beathoven;

import java.io.*;
import java.util.*;

public class Beathoven {

    static int tokenCounter = 1, errorCounter = 1;
    static List<String> tokensSintacticos = new ArrayList<>();
    static Stack<String> pila = new Stack<>();
    static Map<String, Map<String, List<String>>> tablaParse;
    static Map<String, Simbolo> tablaSimbolos = new LinkedHashMap<>();
    static List<ErrorLexico> tablaErrores = new ArrayList<>();
    static List<EventoMusical> eventosMusicales = new ArrayList<>();

    static int tempoValor = 120;
    static String timeSignatureString = "4/4";
    static int keyFifths = 0;
    static String titleString = "Untitled";
    static String instrumentString = "Piano";

    static String generarIDToken() {
        return String.format("T%03d", tokenCounter++);
    }

    static String generarIDError() {
        return String.format("E%03d", errorCounter++);
    }

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
                    tokens.add(actual.toString());
                    actual.setLength(0);
                }
            } else actual.append(c);
        }
        if (actual.length() > 0) tokens.add(actual.toString());
        return tokens;
    }

    public static void main(String[] args) {
        String rutaArchivo = "beats/ejemplo.beat";
        System.out.println("Usando archivo de entrada: " + rutaArchivo);

        System.out.println("\nANALIZADOR LEXICO\n");

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            int numeroLinea = 0;
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
                pila.pop();
                pos++;
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

        if (aceptado) System.out.println("\n✓ ACEPTADO");
        else System.out.println("\n✘ RECHAZADO");

        // ANALIZADOR SEMANTICO
        System.out.println("\nANALIZADOR SEMANTICO\n");

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            int numLinea = 0;
            while ((linea = br.readLine()) != null) {
                numLinea++;
                linea = linea.trim();
                if (linea.isEmpty()) continue;

                List<String> partes = extraerTokens(linea);
                if (partes.isEmpty()) continue;

                String instruccion = partes.get(0);

                switch (instruccion) {
                    case "TEMPO":
                        if (partes.size() == 2) tempoValor = Integer.parseInt(partes.get(1));
                        break;
                    case "TIME":
                        if (partes.size() == 2) timeSignatureString = partes.get(1);
                        break;
                    case "KEY":
                        if (partes.size() == 2) keyFifths = Utils.keyToFifths(partes.get(1));
                        break;
                    case "TITLE":
                        if (partes.size() >= 2) titleString = linea.substring(linea.indexOf('"')).replaceAll("\"", "");
                        break;
                    case "INSTRUMENT":
                        if (partes.size() == 2) instrumentString = partes.get(1);
                        break;
                    case "MARK":
                    case "GOTO":
                        break;
                    default:
                        if (obtenerTipo(instruccion).equals("NOTA_SIMPLE") && partes.size() == 2) {
                            int pitch = Utils.notaToMidi(instruccion);
                            int duracion = Utils.duracionToTicks(partes.get(1));
                            if (pitch == -1 || duracion == -1) {
                                tablaErrores.add(new ErrorLexico(generarIDError(), "Error Semántico", numLinea, "Nota o duración inválida", "Revisar NOTA_SIMPLE y DUR"));
                            } else {
                                eventosMusicales.add(new EventoMusical("NOTA", pitch, duracion));
                            }
                        } else if (instruccion.equals("R") && partes.size() == 2) {
                            int duracion = Utils.duracionToTicks(partes.get(1));
                            if (duracion == -1) {
                                tablaErrores.add(new ErrorLexico(generarIDError(), "Error Semántico", numLinea, "Duración de silencio inválida", "Revisar DUR"));
                            } else {
                                eventosMusicales.add(new EventoMusical("SILENCIO", 0, duracion));
                            }
                        }
                        break;
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer archivo en semántico: " + e.getMessage());
            return;
        }

        System.out.println("\n--- Tabla de Errores (Léxicos + Sintácticos + Semánticos) ---");
        if (tablaErrores.isEmpty()) {
            System.out.println("✓ No se encontraron errores.");
        } else {
            System.out.printf("ID     | Tipo de Error        | Línea    | Descripción                         | Acción\n");
            for (ErrorLexico e : tablaErrores) System.out.println(e);
        }

        // GENERACION DE PARTITURA
        System.out.println("\nGENERANDO PARTITURA EN PDF...");

        try {
            MusicXMLGenerator.generarMusicXML(eventosMusicales, "output/output.musicxml", tempoValor, timeSignatureString, keyFifths, titleString, instrumentString);

            String musescorePath = "C:\\Program Files\\MuseScore 4\\bin\\MuseScore4.exe";

            Process p = new ProcessBuilder(
                    musescorePath,
                    "output/output.musicxml",
                    "-o",
                    "output/output.pdf"
            ).inheritIO().start();

            p.waitFor();

            System.out.println("✔ Partitura generada en output/output.pdf");
        } catch (Exception e) {
            System.err.println("Error al generar la partitura: " + e.getMessage());
        }

        // GENERACION DE MIDI
        try {
            MidiGenerator.generarMidi(eventosMusicales, "output/output.mid", tempoValor);
        } catch (Exception e) {
            System.err.println("Error al generar el MIDI: " + e.getMessage());
        }

    }

    // --- FUNCIONES AUXILIARES DEL PARSER LL(1) ---
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
        return Arrays.asList(
                "TEMPO", "NUM", "TIME", "FRACCION", "KEY", "NOTA_SIMPLE", "TITLE", "CADENA",
                "INSTRUMENT", "ID", "R", "DUR", "REPEAT", "{", "}", "MARK", "GOTO", "ACORDE", "$"
        ).contains(symbol);
    }

    static String stackToString(Stack<String> s) {
        return String.join(" ", s);
    }

    static String inputToString(List<String> input, int pos) {
        return String.join(" ", input.subList(pos, input.size()));
    }

    static List<String> getProduccion(String noTerminal, String token) {
        Map<String, List<String>> row = tablaParse.get(noTerminal);
        if (row == null) return null;
        return row.get(token);
    }
}
