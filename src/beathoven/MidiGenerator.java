package beathoven;

import javax.sound.midi.*;
import java.io.File;
import java.util.List;

public class MidiGenerator {

    public static void generarMidi(List<EventoMusical> eventos, String archivoMidi, int tempoBPM) throws Exception {
        // Crear secuencia MIDI con resolución de 480 ticks por negra
        Sequence sequence = new Sequence(Sequence.PPQ, 480);
        Track track = sequence.createTrack();

        int tick = 0;

        // Tempo meta message
        MetaMessage tempoMeta = new MetaMessage();
        int mpqn = 60000000 / tempoBPM; // microsegundos por negra
        byte[] data = {
                (byte) ((mpqn >> 16) & 0xFF),
                (byte) ((mpqn >> 8) & 0xFF),
                (byte) (mpqn & 0xFF)
        };
        tempoMeta.setMessage(0x51, data, 3);
        track.add(new MidiEvent(tempoMeta, tick));

        // Instrument change → canal 0 → por defecto Piano (instrumento 0)
        ShortMessage programChange = new ShortMessage();
        programChange.setMessage(ShortMessage.PROGRAM_CHANGE, 0, 0, 0);
        track.add(new MidiEvent(programChange, tick));

        // Notas y silencios
        for (EventoMusical e : eventos) {
            if (e.tipo.equals("SILENCIO")) {
                // silencio → solo avanzar tick
                tick += e.duracionTicks;
                continue;
            }

            int pitch = e.pitch;
            int velocity = 90; // volumen (0-127)

            // NOTE_ON
            ShortMessage noteOn = new ShortMessage();
            noteOn.setMessage(ShortMessage.NOTE_ON, 0, pitch, velocity);
            track.add(new MidiEvent(noteOn, tick));

            // NOTE_OFF después de duración
            ShortMessage noteOff = new ShortMessage();
            noteOff.setMessage(ShortMessage.NOTE_OFF, 0, pitch, 0);
            track.add(new MidiEvent(noteOff, tick + e.duracionTicks));

            // avanzar tick
            tick += e.duracionTicks;
        }

        // Meta: End of track
        MetaMessage endOfTrack = new MetaMessage();
        endOfTrack.setMessage(0x2F, new byte[0], 0);
        track.add(new MidiEvent(endOfTrack, tick + 1));

        // Guardar en archivo
        MidiSystem.write(sequence, 1, new File(archivoMidi));

        System.out.println("✔ MIDI generado en " + archivoMidi);
    }
}
