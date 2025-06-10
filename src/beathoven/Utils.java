package beathoven;

public class Utils {

    public static int notaToMidi(String nota) {
        try {
            String base = nota.substring(0, nota.length() - 1);
            char octavaChar = nota.charAt(nota.length() - 1);
            int octava = Character.isDigit(octavaChar) ? octavaChar - '0' : 4; // default 4

            int baseMidi;
            switch (base) {
                case "C": baseMidi = 0; break;
                case "C#": case "Db": baseMidi = 1; break;
                case "D": baseMidi = 2; break;
                case "D#": case "Eb": baseMidi = 3; break;
                case "E": baseMidi = 4; break;
                case "F": baseMidi = 5; break;
                case "F#": case "Gb": baseMidi = 6; break;
                case "G": baseMidi = 7; break;
                case "G#": case "Ab": baseMidi = 8; break;
                case "A": baseMidi = 9; break;
                case "A#": case "Bb": baseMidi = 10; break;
                case "B": baseMidi = 11; break;
                default: return -1;
            }

            return 12 * (octava + 1) + baseMidi;
        } catch (Exception e) {
            return -1;
        }
    }

    public static int duracionToTicks(String duracion) {
        if (duracion.equals("W")) return 1920;
        if (duracion.equals("H")) return 960;
        if (duracion.equals("Q")) return 480;
        if (duracion.equals("E")) return 240;
        if (duracion.equals("S")) return 120;
        return -1;
    }

    public static String midiToStep(int midi) {
        int pitchClass = midi % 12;
        switch (pitchClass) {
            case 0: return "C";
            case 1: return "C";
            case 2: return "D";
            case 3: return "D";
            case 4: return "E";
            case 5: return "F";
            case 6: return "F";
            case 7: return "G";
            case 8: return "G";
            case 9: return "A";
            case 10: return "A";
            case 11: return "B";
            default: return "C";
        }
    }

    public static int midiToOctave(int midi) {
        return (midi / 12) - 1;
    }

    public static int keyToFifths(String key) {
        if (key.equals("C")) return 0;
        if (key.equals("G")) return 1;
        if (key.equals("D")) return 2;
        if (key.equals("A")) return 3;
        if (key.equals("E")) return 4;
        if (key.equals("B")) return 5;
        if (key.equals("F#")) return 6;
        if (key.equals("C#")) return 7;
        if (key.equals("F")) return -1;
        if (key.equals("Bb")) return -2;
        if (key.equals("Eb")) return -3;
        if (key.equals("Ab")) return -4;
        if (key.equals("Db")) return -5;
        if (key.equals("Gb")) return -6;
        if (key.equals("Cb")) return -7;
        return 0;
    }

    public static String[] midiToNoteInfo(int midi) {
        int pitchClass = midi % 12;
        int octave = (midi / 12) - 1;

        String step, alter;
        switch (pitchClass) {
            case 0:  step = "C"; alter = ""; break;
            case 1:  step = "C"; alter = "1"; break;  // C#
            case 2:  step = "D"; alter = ""; break;
            case 3:  step = "D"; alter = "1"; break;  // D#
            case 4:  step = "E"; alter = ""; break;
            case 5:  step = "F"; alter = ""; break;
            case 6:  step = "F"; alter = "1"; break;  // F#
            case 7:  step = "G"; alter = ""; break;
            case 8:  step = "G"; alter = "1"; break;  // G#
            case 9:  step = "A"; alter = ""; break;
            case 10: step = "A"; alter = "1"; break;  // A#
            case 11: step = "B"; alter = ""; break;
            default: step = "C"; alter = "";
        }

        return new String[]{step, alter, String.valueOf(octave)};
    }

    public static String ticksToType(int ticks) {
        if (ticks >= 1920) return "whole";
        if (ticks >= 960) return "half";
        if (ticks >= 480) return "quarter";
        if (ticks >= 240) return "eighth";
        if (ticks >= 120) return "16th";
        return "quarter"; // Valor por defecto
    }
}
