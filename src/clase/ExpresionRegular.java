import clase.ErrorLexico;

import java.io.*;
import java.util.*;

public class ExpresionRegular {

    static int tokenCounter = 1;
    static int errorCounter = 1;

    static String generarIDToken() {
        return String.format("T%03d", tokenCounter++);
    }

    static String generarIDError() {
        return String.format("E%03d", errorCounter++);
    }

    public static String obtenerTipo(String token) {
        if (token.matches("\\d+")) return "NUMERO";
        if (token.matches("\\d+/\\d+")) return "FRACCION";
        if (token.matches("[A-G](#|b)?[0-8]?")) return "SIMBOLO";
        if (token.matches("TEMPO|TIME|KEY|INSTRUMENT|TITLE|R|REPEAT|MARK|GOTO|\\{|\\}|\\[")) return "SIMBOLO";
        if (token.matches("W|H|Q|E|S")) return "SIMBOLO";
        if (token.matches("\".*\"")) return "CADENA";
        if (token.matches("\\[.*\\]")) return "ACORDE";
        if (token.matches("[A-Za-z_][A-Za-z0-9_]*")) return "RUTA";
        return "DESCONOCIDO";
    }

    public static List<String> extraerTokens(String linea) {
        List<String> tokens = new ArrayList<>();
        StringBuilder actual = new StringBuilder();
        boolean dentroComillas = false;
        boolean dentroCorchetes = false;

        for (int i = 0; i < linea.length(); i++) {
            char c = linea.charAt(i);

            if (c == '"' && !dentroCorchetes) {
                actual.append(c);
                if (dentroComillas) {
                    tokens.add(actual.toString().trim());
                    actual.setLength(0);
                    dentroComillas = false;
                } else {
                    dentroComillas = true;
                }
            } else if (c == '[') {
                dentroCorchetes = true;
                actual.append(c);
            } else if (c == ']') {
                actual.append(c);
                tokens.add(actual.toString().trim());
                actual.setLength(0);
                dentroCorchetes = false;
            } else if (Character.isWhitespace(c) && !dentroComillas && !dentroCorchetes) {
                if (actual.length() > 0) {
                    tokens.add(actual.toString().trim());
                    actual.setLength(0);
                }
            } else {
                actual.append(c);
            }
        }

        if (actual.length() > 0) {
            tokens.add(actual.toString().trim());
        }

        return tokens;
    }

    public static String verificarSemantica(String instruccion, List<String> tokens, int linea) {
        switch (instruccion.toUpperCase()) {
            case "TEMPO":
                if (tokens.size() != 2) return "Se esperaba: TEMPO <número entre 20 y 300>";
                try {
                    int tempo = Integer.parseInt(tokens.get(1));
                    if (tempo < 20 || tempo > 300)
                        return "TEMPO fuera de rango (20-300)";
                } catch (NumberFormatException e) {
                    return "TEMPO debe ir seguido de un número válido";
                }
                break;

            case "TIME":
                if (tokens.size() != 2) return "Se esperaba: TIME <compás como 4/4>";
                if (!tokens.get(1).matches("\\d+/\\d+"))
                    return "TIME debe tener formato fracción como 4/4";
                if (!List.of("2/4", "3/4", "4/4", "6/8", "12/8").contains(tokens.get(1)))
                    return "Compás inválido. Use uno de: 2/4, 3/4, 4/4, 6/8, 12/8";
                break;

            case "KEY":
                if (tokens.size() != 2)
                    return "KEY debe ir seguido de una tonalidad (ej: C, Dm, Bb)";
                if (!tokens.get(1).matches("^[A-G](#|b)?(m)?$"))
                    return "Tonalidad inválida. Ejemplos: C, D, Bb, F#m";
                break;

            case "INSTRUMENT":
                if (tokens.size() != 2)
                    return "INSTRUMENT debe ir seguido de un nombre (ej: Piano)";
                if (!tokens.get(1).matches("^[A-Za-z]+$"))
                    return "Nombre inválido para INSTRUMENT";
                break;

            case "TITLE":
                if (tokens.size() != 2 || !tokens.get(1).startsWith("\"") || !tokens.get(1).endsWith("\""))
                    return "TITLE debe ir seguido de un texto entre comillas. Ej: \"Mi canción\"";
                break;

            case "R":
                if (tokens.size() != 2 || !tokens.get(1).matches("W|H|Q|E|S"))
                    return "Después de R debe ir una duración válida (W, H, Q, E, S)";
                break;

            case "REPEAT":
                if (tokens.size() != 3 || !tokens.get(1).matches("\\d+") || !tokens.get(2).equals("{"))
                    return "Sintaxis inválida de REPEAT. Ejemplo: REPEAT 2 {";
                break;

            case "MARK":
            case "GOTO":
                if (tokens.size() != 2 || !tokens.get(1).matches("[a-zA-Z_][a-zA-Z0-9_]*"))
                    return instruccion + " debe ir seguido de un identificador válido";
                break;

            default:
                if (instruccion.matches("^[A-G](#|b)?[0-8]?$")) {
                    if (tokens.size() < 2 || !tokens.get(1).matches("W|H|Q|E|S"))
                        return "Después de una nota debe ir una duración válida (W, H, Q, E, S)";
                } else if (instruccion.startsWith("[")) {
                    if (tokens.size() < 2 || !tokens.get(1).matches("W|H|Q|E|S"))
                        return "Después de un acorde debe ir una duración válida";
                }
        }
        return null;
    }

    public static void main(String[] args) {
        String ruta = "C:\\Users\\Usuario\\IdeaProjects\\Beathoven\\src\\prueba.txt";

        Map<String, Simbolo> tablaSimbolos = new LinkedHashMap<>();
        List<ErrorLexico> tablaErrores = new ArrayList<>();
        Stack<Integer> pilaBloques = new Stack<>();
        boolean esperandoLlave = false;

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            int numeroLinea = 0;

            while ((linea = br.readLine()) != null) {
                numeroLinea++;
                String lineaTrim = linea.trim();
                if (lineaTrim.isEmpty()) continue;

                if (lineaTrim.equals("{")) {
                    if (esperandoLlave) {
                        pilaBloques.push(numeroLinea);
                        esperandoLlave = false;
                    } else {
                        tablaErrores.add(new ErrorLexico(
                                generarIDError(), "Llave sin contexto", numeroLinea,
                                "Llave '{' sin instrucción REPEAT previa", "Verifique la estructura del bloque"
                        ));
                    }
                    continue;
                }

                if (lineaTrim.equals("}")) {
                    if (pilaBloques.isEmpty()) {
                        tablaErrores.add(new ErrorLexico(
                                generarIDError(), "Bloque mal cerrado", numeroLinea,
                                "Llave de cierre '}' sin apertura previa", "Verifique el balance de llaves"
                        ));
                    } else {
                        pilaBloques.pop();
                    }
                    continue;
                }

                List<String> tokens = extraerTokens(lineaTrim);

                if (!tokens.isEmpty()) {
                    String instruccion = tokens.get(0);
                    String errorSemantico = verificarSemantica(instruccion, tokens, numeroLinea);
                    if (errorSemantico != null) {
                        tablaErrores.add(new ErrorLexico(
                                generarIDError(), "Error Semántico", numeroLinea,
                                errorSemantico, "Revisar sintaxis musical"
                        ));
                        esperandoLlave = false;
                        continue;
                    }

                    if (instruccion.equalsIgnoreCase("REPEAT") && tokens.size() == 3 && tokens.get(2).equals("{")) {
                        pilaBloques.push(numeroLinea);
                    } else if (instruccion.equalsIgnoreCase("REPEAT") && tokens.size() == 2) {
                        esperandoLlave = true;
                    } else {
                        esperandoLlave = false;
                    }
                }

                for (String token : tokens) {
                    String tipo = obtenerTipo(token);
                    if (tipo.equals("DESCONOCIDO")) {
                        tablaErrores.add(new ErrorLexico(
                                generarIDError(), "Símbolo No Válido", numeroLinea,
                                "Token no reconocido: " + token, "Revisar ortografía o estructura"
                        ));
                    } else {
                        if (tablaSimbolos.containsKey(token)) {
                            tablaSimbolos.get(token).agregarPosicion(numeroLinea);
                        } else {
                            tablaSimbolos.put(token, new Simbolo(generarIDToken(), tipo, token, numeroLinea));
                        }
                    }
                }
            }

            while (!pilaBloques.isEmpty()) {
                int lineaSinCerrar = pilaBloques.pop();
                tablaErrores.add(new ErrorLexico(
                        generarIDError(), "Bloque sin cerrar", lineaSinCerrar,
                        "Llave de apertura '{' sin su correspondiente '}'", "Agregar cierre de bloque"
                ));
            }

        } catch (IOException e) {
            System.err.println("❌ Error al leer el archivo: " + e.getMessage());
        }

        System.out.println("\n--- Tabla de Símbolos ---");
        System.out.println("ID     | Tipo de Token  | Valor                | Posición");
        for (Simbolo s : tablaSimbolos.values()) {
            System.out.println(s);
        }

        System.out.println("\n--- Tabla de Manejo de Errores ---");
        System.out.println("ID     | Tipo de Error        | Posición | Descripción                         | Acción Recomendada");
        for (ErrorLexico err : tablaErrores) {
            System.out.println(err);
        }
    }
}
