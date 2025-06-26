# Beathoven Compiler 🎵

Beathoven is a custom compiler for a simple music language that allows you to write sheet music and generate MIDI files.  
The compiler performs **lexical**, **syntactic**, and **semantic** analysis of music instructions and produces playable MIDI output and MusicXML sheet music.

## Features

- Custom music language for writing simple melodies
- Supports:
  - TEMPO
  - TIME signature
  - KEY signature
  - TITLE
  - INSTRUMENT
  - Notes with duration
- Generates:
  - `.mid` MIDI file
  - `.musicxml` file for sheet music visualization
- Basic error reporting:
  - Lexical errors
  - Syntactic errors
  - Semantic errors

## Example Language Syntax

```txt
TEMPO 144
TIME 4/4
KEY C
TITLE "Jingle Bells"
INSTRUMENT Piano

E4 Q
E4 Q
E4 H
E4 Q
E4 Q
E4 H
E4 Q
G4 Q
C4 Q
D4 Q
E4 W
```

## Usage

Write your music instructions in a `.beat` file.

Run the compiler:

```bash
java Beathoven your_song.beat
```

The compiler will generate:

- `output.mid` → Playable MIDI file
- `output.musicxml` → Sheet music
- Console output with error reports and token analysis

## Example Output

![image](https://github.com/user-attachments/assets/d634b42d-4484-436e-b554-80280364a61f)

## Project Structure

```plaintext
Beathoven/
├── src/                  → Source code (Java)
├── test/                 → Example `.mt` files
├── output.mid            → Generated MIDI (after running compiler)
├── output.musicxml       → Generated sheet music
└── README.md             → This file
```

## Requirements

- Java 8 or higher
- MIDI player (e.g., Windows Media Player, VLC, Media Player Classic)
- MusicXML viewer (e.g., MuseScore, Finale, Sibelius)

## Author

Miguel Angel Achá Boiano
