package org.beathoven.ui.contentassist;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.ui.editor.contentassist.ContentAssistContext;
import org.eclipse.xtext.ui.editor.contentassist.ICompletionProposalAcceptor;

/**
 * Clase de autocompletado para el lenguaje Beathoven.
 */
public class BeathovenProposalProvider extends AbstractBeathovenProposalProvider {

    // Sugerencias para notas musicales
    public void completeNoteValue_Value(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
        List<String> notas = List.of(
            "C4", "C#4", "D4", "D#4", "E4", "F4", "F#4",
            "G4", "G#4", "A4", "A#4", "B4",
            "C5", "D5", "E5", "F5", "G5"
        );
        for (String nota : notas) {
            acceptor.accept(createCompletionProposal(nota, context));
        }
    }

    // Sugerencias para duraciones
    public void completeDuration_Value(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
        List<String> duraciones = List.of("W", "H", "Q", "E", "S");
        for (String dur : duraciones) {
            acceptor.accept(createCompletionProposal(dur, context));
        }
    }

    // Tempos comunes sugeridos
    public void completeTempo_Value(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
        List<String> tempos = List.of("60", "90", "100", "120", "140", "160", "180", "200");
        for (String t : tempos) {
            acceptor.accept(createCompletionProposal(t, context));
        }
    }

    // Compases comunes sugeridos
    public void completeTime_Value(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
        List<String> tiempos = List.of("2/4", "3/4", "4/4", "6/8", "12/8");
        for (String t : tiempos) {
            acceptor.accept(createCompletionProposal(t, context));
        }
    }

    // Instrumentos comunes sugeridos
    public void completeInstrument_Name(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
        List<String> instrumentos = List.of("Piano", "Guitar", "Violin", "Drums", "Flute");
        for (String i : instrumentos) {
            acceptor.accept(createCompletionProposal(i, context));
        }
    }

    // Palabras clave del lenguaje sugeridas
    @Override
    public void completeKeyword(Keyword keyword, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
        String kw = keyword.getValue();
        List<String> sugerencias = List.of(
            "TEMPO", "TIME", "KEY", "TITLE", "INSTRUMENT",
            "REPEAT", "MARK", "GOTO", "R",
            "{", "}", "[", "]"
        );
        if (sugerencias.contains(kw)) {
            acceptor.accept(createCompletionProposal(kw, context));
        }
    }

    // Sugerencias de acordes básicos
    public void completeChord_Chord(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
        List<String> acordes = List.of(
            "[C4,E4,G4]", "[D4,F#4,A4]", "[E4,G#4,B4]",
            "[F4,A4,C5]", "[G4,B4,D5]", "[A4,C#5,E5]", "[B4,D#5,F#5]"
        );
        for (String acorde : acordes) {
            acceptor.accept(createCompletionProposal(acorde, context));
        }
    }
}
