package org.beathoven.validation;

import org.eclipse.xtext.validation.Check;
import org.beathoven.beathoven.NoteStmt;
import org.beathoven.beathoven.Tempo;

public class BeathovenValidator extends AbstractBeathovenValidator {

    public static final String INVALID_NOTE = "invalidNote";

    @Check
    public void checkValidNote(NoteStmt note) {
        String pitch = note.getPitch().getValue();
        if (!pitch.matches("[A-G](#|b)?[0-8]?")) {
            error("Nota inválida: " + pitch, note, 
                note.eClass().getEStructuralFeature("pitch"), 
                INVALID_NOTE);
        }
    }

    @Check
    public void checkTempoRange(Tempo tempo) {
        int bpm = tempo.getValue();
        if (bpm < 40 || bpm > 300) {
            error("El tempo debe estar entre 40 y 300 BPM", tempo, 
                tempo.eClass().getEStructuralFeature("value"), 
                "invalidTempo");
        }
    }
}
