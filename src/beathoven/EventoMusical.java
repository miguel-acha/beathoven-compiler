package beathoven;

public class EventoMusical {
    public String tipo; // "NOTA" o "SILENCIO"
    public int pitch;
    public int duracionTicks;

    public EventoMusical(String tipo, int pitch, int duracionTicks) {
        this.tipo = tipo;
        this.pitch = pitch;
        this.duracionTicks = duracionTicks;
    }
}
