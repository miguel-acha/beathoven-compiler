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
                if (!dentroComillas) {
                    dentroCorchetes = true;
                    actual.append(c);
                } else {
                    actual.append(c);
                }
            } else if (c == ']') {
                if (dentroCorchetes) {
                    actual.append(c);
                    tokens.add(actual.toString().trim());
                    actual.setLength(0);
                    dentroCorchetes = false;
                } else {
                    actual.append(c);
                }
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

    public static void main(String[] args) {
        String ruta = "C:\\Users\\Usuario\\IdeaProjects\\Beathoven\\src\\prueba.txt";

        Map<String, Simbolo> tablaSimbolos = new LinkedHashMap<>();
        List<ErrorLexico> tablaErrores = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            int numeroLinea = 0;

            while ((linea = br.readLine()) != null) {
                numeroLinea++;
                if (linea.trim().isEmpty()) {
                    tablaErrores.add(new ErrorLexico(
                            generarIDError(), "Entrada Vacía", numeroLinea,
                            "No se proporcionó entrada", "Solicitar entrada válida"
                    ));
                    continue;
                }

                List<String> tokens = extraerTokens(linea);
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

        } catch (IOException e) {
            System.err.println("❌ Error al leer el archivo: " + e.getMessage());
        }

        // Imprimir tabla de símbolos
        System.out.println("\n--- Tabla de Símbolos ---");
        System.out.println("ID     | Tipo de Token  | Valor                | Posición");
        for (Simbolo s : tablaSimbolos.values()) {
            System.out.println(s);
        }

        // Imprimir errores
        System.out.println("\n--- Tabla de Manejo de Errores ---");
        System.out.println("ID     | Tipo de Error        | Posición | Descripción                         | Acción Recomendada");
        for (ErrorLexico err : tablaErrores) {
            System.out.println(err);
        }
    }
}
