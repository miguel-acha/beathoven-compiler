package clase;

import java.util.*;

public class AnalizadorPredictivo {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";

    static List<String> input;
    static int pos;
    static Stack<String> stack;
    static Map<String, Map<String, List<String>>> tablaParse;

    public static void main(String[] args) {
        //input = Arrays.asList("TEMPO", "NUM", "$");
        //input = Arrays.asList("TIME", "FRACCION", "$");  // ejemplo: TIME 4/4
        //input = Arrays.asList("NOTA_SIMPLE", "DUR", "$");  // ejemplo: C4 Q
        //input = Arrays.asList("ACORDE", "DUR", "$");  // ejemplo: [C4 E4 G4] H
        /*input = Arrays.asList(
            "REPEAT", "NUM", "{",
            "NOTA_SIMPLE", "DUR",
            "NOTA_SIMPLE", "DUR",
            "}",
            "$"
        );*/ // Ejemplo: REPEAT 2 { C4 Q D4 Q }
        input = Arrays.asList(
                "TEMPO", "NUM",
                "TIME", "FRACCION",
                "KEY", "TONALIDAD",
                "TITLE", "CADENA",
                "INSTRUMENT", "ID",
                "R", "DUR",
                "REPEAT", "NUM", "{",
                "NOTA_SIMPLE", "DUR",
                "NOTA_SIMPLE", "DUR",
                "MARK", "ID",
                "GOTO", "ID",
                "}",
                "$"
        );

        pos = 0;
        stack = new Stack<>();
        inicializarTabla();

        stack.push("$");
        stack.push("S");

        List<String> stacks = new ArrayList<>();
        List<String> inputs = new ArrayList<>();
        List<String> outputs = new ArrayList<>();

        // Proceso de análisis
        while (!stack.isEmpty()) {
            String tope = stack.peek();
            String tokenActual = input.get(pos);

            String stackStr = stackToString();
            String inputStr = inputToString();

            if (tope.equals(tokenActual)) {
                stack.pop();
                pos++;
                stacks.add(stackStr);
                inputs.add(inputStr);
                outputs.add("(match " + tokenActual + ")");
            } else if (isTerminal(tope)) {
                stacks.add(stackStr);
                inputs.add(inputStr);
                outputs.add("Error: token inesperado '" + tokenActual + "'");
                break;
            } else {
                List<String> produccion = getProduccion(tope, tokenActual);
                if (produccion == null) {
                    stacks.add(stackStr);
                    inputs.add(inputStr);
                    outputs.add("Error: no hay producción para [" + tope + ", " + tokenActual + "]");
                    break;
                } else {
                    stack.pop();
                    if (!(produccion.size() == 1 && produccion.get(0).equals("ε"))) {
                        for (int i = produccion.size() - 1; i >= 0; i--) {
                            stack.push(produccion.get(i));
                        }
                    }
                    stacks.add(stackStr);
                    inputs.add(inputStr);
                    outputs.add(tope + " → " + String.join(" ", produccion));
                }
            }
        }

        // Agregar fila final con mensaje ACEPTADO o RECHAZADO
        if (stack.isEmpty() && pos == input.size()) {
            stacks.add("$");
            inputs.add("$");
            outputs.add(ANSI_GREEN + "ACEPTADO" + ANSI_RESET);
        } else {
            stacks.add("$");
            inputs.add("$");
            outputs.add(ANSI_RED + "RECHAZADO" + ANSI_RESET);
        }

        imprimirTablaDinamica(stacks, inputs, outputs);
    }

    static void inicializarTabla() {
        tablaParse = new HashMap<>();

        Map<String, List<String>> rowS = new HashMap<>();
        for (String t : Arrays.asList("TEMPO", "TIME", "KEY", "TITLE", "INSTRUMENT", "R", "REPEAT",
                "MARK", "GOTO", "NOTA_SIMPLE", "ACORDE")) {
            rowS.put(t, Arrays.asList("BLOQUES"));
        }
        tablaParse.put("S", rowS);

        Map<String, List<String>> rowBloques = new HashMap<>();
        List<String> instBloques = Arrays.asList("INST", "BLOQUES");
        for (String t : Arrays.asList("TEMPO", "TIME", "KEY", "TITLE", "INSTRUMENT", "R", "REPEAT",
                "MARK", "GOTO", "NOTA_SIMPLE", "ACORDE")) {
            rowBloques.put(t, instBloques);
        }
        rowBloques.put("}", Arrays.asList("ε"));
        rowBloques.put("$", Arrays.asList("ε"));
        tablaParse.put("BLOQUES", rowBloques);

        Map<String, List<String>> rowInst = new HashMap<>();
        rowInst.put("TEMPO", Arrays.asList("TEMPO", "NUM"));
        rowInst.put("TIME", Arrays.asList("TIME", "FRACCION"));
        rowInst.put("KEY", Arrays.asList("KEY", "TONALIDAD"));
        rowInst.put("TITLE", Arrays.asList("TITLE", "CADENA"));
        rowInst.put("INSTRUMENT", Arrays.asList("INSTRUMENT", "ID"));
        rowInst.put("R", Arrays.asList("R", "DUR"));
        rowInst.put("REPEAT", Arrays.asList("REPEAT", "NUM", "{", "BLOQUES", "}"));
        rowInst.put("MARK", Arrays.asList("MARK", "ID"));
        rowInst.put("GOTO", Arrays.asList("GOTO", "ID"));
        rowInst.put("NOTA_SIMPLE", Arrays.asList("NOTA", "DUR"));
        rowInst.put("ACORDE", Arrays.asList("NOTA", "DUR"));
        tablaParse.put("INST", rowInst);

        Map<String, List<String>> rowNota = new HashMap<>();
        rowNota.put("NOTA_SIMPLE", Arrays.asList("NOTA_SIMPLE"));
        rowNota.put("ACORDE", Arrays.asList("ACORDE"));
        tablaParse.put("NOTA", rowNota);
    }

    static boolean isTerminal(String symbol) {
        Set<String> terminals = new HashSet<>(Arrays.asList(
                "TEMPO", "NUM", "TIME", "FRACCION", "KEY", "TONALIDAD", "TITLE", "CADENA",
                "INSTRUMENT", "ID", "R", "DUR", "REPEAT", "{", "}", "MARK", "GOTO",
                "NOTA_SIMPLE", "ACORDE", "$"
        ));
        return terminals.contains(symbol);
    }

    static List<String> getProduccion(String noTerminal, String token) {
        Map<String, List<String>> row = tablaParse.get(noTerminal);
        if (row == null) return null;
        return row.get(token);
    }

    static String stackToString() {
        return String.join(" ", stack);
    }

    static String inputToString() {
        return String.join(" ", input.subList(pos, input.size()));
    }

    static void imprimirTablaDinamica(List<String> stacks, List<String> inputs, List<String> outputs) {
        int maxStack = "Stack".length();
        int maxInput = "Input".length();
        int maxOutput = "Output".length();

        for (String s : stacks) if (s.length() > maxStack) maxStack = s.length();
        for (String s : inputs) if (s.length() > maxInput) maxInput = s.length();
        for (String s : outputs) {
            String clean = s.replaceAll("\u001B\\[[;\\d]*m", "");
            if (clean.length() > maxOutput) maxOutput = clean.length();
        }

        String formatLeft = String.format("| %%-%ds | %%-%ds | %%-%ds |%n", maxStack, maxInput, maxOutput);
        String formatRight = String.format("| %%-%ds | %%-%ds | %%%ds |%n", maxStack, maxInput, maxOutput);
        String linea = "+" + "-".repeat(maxStack + 2) + "+" + "-".repeat(maxInput + 2) + "+" + "-".repeat(maxOutput + 2) + "+";

        System.out.println(linea);
        System.out.printf(formatLeft, "Stack", "Input", "Output");
        System.out.println(linea);

        for (int i = 0; i < stacks.size(); i++) {
            String output = outputs.get(i);
            if (i == stacks.size() - 1 && (output.contains("ACEPTADO") || output.contains("RECHAZADO"))) {
                imprimirFilaFinal(stacks.get(i), inputs.get(i), output, maxOutput, maxInput, maxStack);
            } else {
                System.out.printf(formatLeft, stacks.get(i), inputs.get(i), output);
            }
        }

        System.out.println(linea);
    }

    static void imprimirFilaFinal(String stack, String input, String output, int maxOutput, int maxInput, int maxStack) {
        // Limpiar secuencias ANSI para colores
        String cleanOutput = output.replaceAll("\u001B\\[[;\\d]*m", "");
        int paddingTotal = maxOutput - cleanOutput.length();
        int paddingLeft = paddingTotal / 2;
        int paddingRight = paddingTotal - paddingLeft;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < paddingLeft; i++) sb.append(' ');
        sb.append(output);
        for (int i = 0; i < paddingRight; i++) sb.append(' ');

        System.out.printf("| %-"+maxStack+"s | %-"+maxInput+"s | %s |\n", stack, input, sb.toString());
    }
}
