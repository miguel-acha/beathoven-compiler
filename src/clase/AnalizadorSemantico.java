package clase;

import java.util.*;

public class AnalizadorSemantico {

    static class Instruccion {
        String tipo;
        String[] args;
        int linea;

        public Instruccion(String tipo, int linea, String... args) {
            this.tipo = tipo;
            this.args = args;
            this.linea = linea;
        }
    }

    static class ErrorSemantico {
        String tipo, descripcion, sugerencia;
        int linea;

        public ErrorSemantico(String tipo, int linea, String descripcion, String sugerencia) {
            this.tipo = tipo;
            this.linea = linea;
            this.descripcion = descripcion;
            this.sugerencia = sugerencia;
        }

        public String toString() {
            return String.format("✘ [%s] Línea %d: %s → %s", tipo, linea, descripcion, sugerencia);
        }
    }

    public static void main(String[] args) {

        // Entrada embebida
        String[] archivo = {
                "TEMPO 120",
                "TIME 4/4",
                "KEY Bb",
                "INSTRUMENT Piano",
                "TITLE \"Oda a la Alegría\"",
                "C4 Q",
                "D4 Q",
                "REPEAT 2",
                "{",
                "E4 Q",
                "F4 Q",
                "}",
                "MARK intro",
                "GOTO intro"
        };

        List<Instruccion> instrucciones = new ArrayList<>();
        List<ErrorSemantico> errores = new ArrayList<>();
        Set<String> marcasDefinidas = new HashSet<>();
        Set<String> marcasUsadas = new HashSet<>();
        boolean tempoOK = false, timeOK = false;

        int numLinea = 0;
        for (String linea : archivo) {
            numLinea++;
            linea = linea.trim();
            if (linea.isEmpty()) continue;

            String[] partes = linea.split("\\s+");
            String instruccion = partes[0];

            switch (instruccion) {

                case "TEMPO":
                    tempoOK = true;
                    if (partes.length != 2 || !partes[1].matches("\\d+")) {
                        errores.add(new ErrorSemantico("TEMPO", numLinea, "Valor inválido para tempo", "Use un número entero positivo"));
                    } else {
                        int valorTempo = Integer.parseInt(partes[1]);
                        if (valorTempo < 20 || valorTempo > 300) {
                            errores.add(new ErrorSemantico("TEMPO", numLinea, "Valor fuera de rango para tempo", "Use un valor entre 20 y 300 BPM"));
                        }
                    }
                    break;

                case "TIME":
                    timeOK = true;
                    Set<String> compasesValidos = Set.of("2/4", "3/4", "4/4", "6/8", "12/8");
                    if (partes.length != 2 || !compasesValidos.contains(partes[1])) {
                        errores.add(new ErrorSemantico("TIME", numLinea, "Valor de compás inválido: " + partes[1], "Use uno de: 2/4, 3/4, 4/4, 6/8, 12/8"));
                    }
                    break;

                case "KEY":
                    if (partes.length != 2 || !partes[1].matches("[A-G](#|b)?")) {
                        errores.add(new ErrorSemantico("KEY", numLinea, "Tonalidad inválida", "Use como C, D#, Bb"));
                    }
                    break;

                case "INSTRUMENT":
                    if (partes.length != 2 || !partes[1].matches("[A-Za-z_][A-Za-z0-9_]*")) {
                        errores.add(new ErrorSemantico("INSTRUMENT", numLinea, "Nombre de instrumento inválido", "Use identificadores válidos sin espacios"));
                    }
                    break;

                case "TITLE":
                    if (partes.length < 2 || !linea.contains("\"")) {
                        errores.add(new ErrorSemantico("TITLE", numLinea, "Título inválido", "Use comillas dobles para el título"));
                    }
                    break;

                case "MARK":
                    if (partes.length != 2) {
                        errores.add(new ErrorSemantico("MARK", numLinea, "Nombre inválido de marca", "Use un identificador simple"));
                    } else {
                        marcasDefinidas.add(partes[1]);
                    }
                    break;

                case "GOTO":
                    if (partes.length != 2) {
                        errores.add(new ErrorSemantico("GOTO", numLinea, "Nombre inválido de destino", "Use un identificador simple"));
                    } else {
                        marcasUsadas.add(partes[1]);
                    }
                    break;

                case "REPEAT":
                    if (partes.length != 2 || !partes[1].matches("\\d+")) {
                        errores.add(new ErrorSemantico("REPEAT", numLinea, "Repeticiones inválidas", "Use un número entero positivo"));
                    }
                    break;

                case "R":
                    if (partes.length != 2 || !partes[1].matches("W|H|Q|E|S")) {
                        errores.add(new ErrorSemantico("R", numLinea, "Duración inválida", "Use duraciones como W, H, Q, E, S"));
                    }
                    break;

                default:
                    // NOTA
                    if (partes.length == 2 && partes[0].matches("[A-G](#|b)?[0-8]?") && partes[1].matches("W|H|Q|E|S")) {
                        // Nota válida
                    } else if (partes[0].startsWith("[")) {
                        // Posible acorde (no se valida contenido aquí)
                    } else if (partes[0].equals("{") || partes[0].equals("}")) {
                        // Parte de estructura REPEAT
                    } else {
                        errores.add(new ErrorSemantico("Instrucción", numLinea, "Instrucción desconocida o mal formada", "Revise la sintaxis"));
                    }
                    break;
            }

            instrucciones.add(new Instruccion(instruccion, numLinea, Arrays.copyOfRange(partes, 1, partes.length)));
        }

        // Validar TEMPO y TIME obligatorios
        if (!tempoOK)
            errores.add(new ErrorSemantico("Falta", 0, "No se definió TEMPO", "Agregue una línea TEMPO al inicio"));
        if (!timeOK)
            errores.add(new ErrorSemantico("Falta", 0, "No se definió TIME", "Agregue una línea TIME al inicio"));

        // Validar marcas GOTO que no existen
        for (String usada : marcasUsadas) {
            if (!marcasDefinidas.contains(usada)) {
                errores.add(new ErrorSemantico("GOTO", 0, "Destino no definido: " + usada, "Defina la marca antes de usar GOTO"));
            }
        }

        // Mostrar salida
        System.out.println("ANÁLISIS SEMÁNTICO:");
        if (errores.isEmpty()) {
            System.out.println("✔ No se encontraron errores semánticos.");
        } else {
            for (ErrorSemantico err : errores) System.out.println(err);
        }
    }
}
