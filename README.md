# Beathoven Compiler 🎼

Compilador de lenguaje Beathoven (.beat), que genera partituras en formato PDF a partir de código musical.

## Requisitos

- Java 8 o superior
- MuseScore instalado y disponible en el PATH (comando `mscore` o `mscore3`)

## Ejecución

### Desde IDE (recomendado)

1. Abrir en IntelliJ o Eclipse.
2. Ejecutar `Beathoven.java` con argumento: `beats/ejemplo.beat`

### Desde línea de comandos (Windows)

1. Ejecutar `run.bat`

### Proceso completo

1. Análisis léxico
2. Análisis sintáctico
3. Análisis semántico
4. Generación de MusicXML (`output/output.musicxml`)
5. Generación automática de PDF con la partitura (`output/output.pdf`)

## Ejemplo de archivo .beat

```plaintext
TEMPO 120
TIME 4/4
TITLE "Ejemplo de Beathoven"
C4 Q
D4 Q
E4 Q
F4 Q
R Q
G4 H
```
