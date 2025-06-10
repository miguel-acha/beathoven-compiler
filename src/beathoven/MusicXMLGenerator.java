package beathoven;

import java.io.*;
import java.util.*;

public class MusicXMLGenerator {

    public static void generarMusicXML(List<EventoMusical> eventos, String archivoXML,
                                       int tempo, String timeSignature, int keyFifths,
                                       String title, String instrument) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(archivoXML))) {
            // Calcular ticks por medida basado en el compás
            String[] timeParts = timeSignature.split("/");
            int beats = Integer.parseInt(timeParts[0]);
            int beatType = Integer.parseInt(timeParts[1]);
            int tickPerMeasure = (480 * 4 * beats) / beatType;

            pw.println("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>");
            pw.println("<!DOCTYPE score-partwise PUBLIC \"-//Recordare//DTD MusicXML 3.1 Partwise//EN\"");
            pw.println("  \"http://www.musicxml.org/dtds/partwise.dtd\">");
            pw.println("<score-partwise version=\"3.1\">");

            // Configuración de página y escalado profesional
            pw.println("<defaults>");
            pw.println("<scaling>");
            pw.println("<millimeters>7</millimeters>");
            pw.println("<tenths>40</tenths>");
            pw.println("</scaling>");
            pw.println("<page-layout>");
            pw.println("<page-height>1760</page-height>");
            pw.println("<page-width>1360</page-width>");
            pw.println("<page-margins type=\"both\">");
            pw.println("<left-margin>120</left-margin>");
            pw.println("<right-margin>120</right-margin>");
            pw.println("<top-margin>120</top-margin>");
            pw.println("<bottom-margin>120</bottom-margin>");
            pw.println("</page-margins>");
            pw.println("</page-layout>");
            pw.println("<system-layout>");
            pw.println("<system-margins>");
            pw.println("<left-margin>0</left-margin>");
            pw.println("<right-margin>0</right-margin>");
            pw.println("</system-margins>");
            pw.println("<system-distance>150</system-distance>");
            pw.println("<top-system-distance>200</top-system-distance>");
            pw.println("</system-layout>");
            pw.println("<staff-layout>");
            pw.println("<staff-distance>80</staff-distance>");
            pw.println("</staff-layout>");
            pw.println("</defaults>");

            // Título centrado profesionalmente
            pw.println("<credit page=\"1\">");
            pw.println("<credit-words default-x=\"680\" default-y=\"1600\" " +
                    "justify=\"center\" valign=\"top\" " +
                    "font-family=\"Times New Roman\" font-size=\"24\" " +
                    "font-weight=\"bold\">" + title + "</credit-words>");
            pw.println("</credit>");

            // Lista de partes
            pw.println("<part-list>");
            pw.println("<score-part id=\"P1\">");
            pw.printf("<part-name>%s</part-name>%n", instrument);
            pw.println("<score-instrument id=\"P1-I1\">");
            pw.printf("<instrument-name>%s</instrument-name>%n", instrument);
            pw.println("</score-instrument>");
            pw.println("</score-part>");
            pw.println("</part-list>");

            // Parte principal
            pw.println("<part id=\"P1\">");

            int measureNumber = 1;
            int tickAccum = 0;
            int measureInSystem = 0;
            boolean firstMeasure = true;

            // Procesar eventos musicales
            for (int i = 0; i < eventos.size(); i++) {
                if (tickAccum == 0) {
                    pw.printf("<measure number=\"%d\">%n", measureNumber);

                    if (firstMeasure) {
                        // Atributos iniciales
                        pw.println("<attributes>");
                        pw.println("<divisions>480</divisions>");
                        pw.printf("<key><fifths>%d</fifths></key>%n", keyFifths);
                        pw.printf("<time><beats>%s</beats><beat-type>%s</beat-type></time>%n",
                                timeParts[0], timeParts[1]);
                        pw.println("<clef><sign>G</sign><line>2</line></clef>");
                        pw.println("</attributes>");

                        // Indicación de tempo
                        pw.println("<direction placement=\"above\">");
                        pw.println("<direction-type>");
                        pw.println("<metronome>");
                        pw.printf("<beat-unit>quarter</beat-unit><per-minute>%d</per-minute>%n", tempo);
                        pw.println("</metronome>");
                        pw.println("</direction-type>");
                        pw.printf("<sound tempo=\"%d\"/>%n", tempo);
                        pw.println("</direction>");
                        firstMeasure = false;
                    }
                }

                EventoMusical e = eventos.get(i);

                // Manejo de duraciones y medidas
                if (tickAccum + e.duracionTicks > tickPerMeasure) {
                    // Completar la medida con silencio si es necesario
                    int remaining = tickPerMeasure - tickAccum;
                    if (remaining > 0) {
                        agregarSilencio(pw, remaining);
                        tickAccum += remaining;
                    }

                    cerrarMedida(pw, measureNumber);
                    measureNumber++;
                    measureInSystem++;
                    tickAccum = 0;

                    // Control de saltos de sistema
                    if (measureInSystem >= 4) {
                        pw.println("<print new-system=\"yes\"/>");
                        measureInSystem = 0;
                    }

                    pw.printf("<measure number=\"%d\">%n", measureNumber);
                }

                // Escribir nota/silencio
                if (e.tipo.equals("SILENCIO")) {
                    agregarSilencio(pw, e.duracionTicks);
                } else {
                    agregarNota(pw, e, i, eventos);
                }

                tickAccum += e.duracionTicks;
            }

            // Cerrar última medida
            if (tickAccum > 0) {
                cerrarMedida(pw, measureNumber);
            }

            pw.println("</part>");
            pw.println("</score-partwise>");
        }
    }

    private static void agregarNota(PrintWriter pw, EventoMusical e, int index, List<EventoMusical> eventos) {
        String[] noteInfo = Utils.midiToNoteInfo(e.pitch);

        pw.println("<note>");
        pw.println("<pitch>");
        pw.printf("<step>%s</step>%n", noteInfo[0]);
        if (!noteInfo[1].isEmpty()) {
            pw.printf("<alter>%s</alter>%n", noteInfo[1]);
        }
        pw.printf("<octave>%s</octave>%n", noteInfo[2]);
        pw.println("</pitch>");
        pw.printf("<duration>%d</duration>%n", e.duracionTicks);
        pw.printf("<type>%s</type>%n", Utils.ticksToType(e.duracionTicks));

        // Agregar dinámicas solo al inicio de frases
        if (index == 0 || eventos.get(index - 1).tipo.equals("SILENCIO")) {
            pw.println("<notations>");
            pw.println("<dynamics placement=\"below\">");
            pw.println("<mf/>");
            pw.println("</dynamics>");
            pw.println("</notations>");
        }

        pw.println("</note>");
    }

    private static void agregarSilencio(PrintWriter pw, int duracion) {
        pw.println("<note>");
        pw.println("<rest/>");
        pw.printf("<duration>%d</duration>%n", duracion);
        pw.printf("<type>%s</type>%n", Utils.ticksToType(duracion));
        pw.println("</note>");
    }

    private static void cerrarMedida(PrintWriter pw, int numMedida) {
        pw.println("<barline location=\"right\">");
        pw.println("<bar-style>light-heavy</bar-style>");
        pw.println("</barline>");
        pw.println("</measure>");
    }
}
