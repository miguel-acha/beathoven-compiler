# Beathoven Compiler 🎵

This repository contains the development of **Beathoven**, a custom compiler for a simple music language that lets you write sheet music and generate MIDI, PDF, and MusicXML files.

## 🧠 Branch Overview

### `main`
The original version of the Beathoven compiler built entirely in Java. It includes a manually defined music language and custom parsing logic for generating musical output files.

### `xtext`
An **experimental branch** using the **Xtext framework** to define the Beathoven language more formally and automatically generate:
- Lexer and parser from a grammar definition
- Smart editor features (autocomplete, syntax highlighting, validations)
- File generation (MIDI, PDF, MusicXML) from `.beat` files

This branch includes the full Eclipse-based Xtext project, with:
- Grammar in `Beathoven.xtext`
- Java code for semantic analysis and file generation
- Configuration for automatic execution on file save

## 🚀 Project Goal

To simplify the process of composing music through a custom domain-specific language (DSL), enabling easy conversion of text-based notation into professional sheet music and audio output.

---

> For setup instructions and usage details, check the specific README in each branch.
