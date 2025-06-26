package org.beathoven.generator;

import com.google.inject.Inject;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.generator.AbstractGenerator;
import org.eclipse.xtext.generator.IFileSystemAccess2;
import org.eclipse.xtext.generator.IGeneratorContext;
import org.eclipse.xtext.serializer.ISerializer;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

@SuppressWarnings("all")
public class BeathovenGenerator extends AbstractGenerator {
  @Inject
  private ISerializer serializer;

  @Override
  public void doGenerate(final Resource resource, final IFileSystemAccess2 fsa, final IGeneratorContext context) {
    try {
      final String inputPath = "C:\\Users\\Usuario\\IdeaProjects\\Beathoven\\beats\\ejemplo.beat";
      final String content = this.serializer.serialize(IterableExtensions.<EObject>head(resource.getContents()));
      final File file = new File(inputPath);
      FileWriter _fileWriter = new FileWriter(file, false);
      final BufferedWriter writer = new BufferedWriter(_fileWriter);
      writer.write(content);
      writer.close();
      InputOutput.<String>println(("\ud83d\udcdd Archivo .beat guardado en: " + inputPath));
      final String batPath = "C:\\Users\\Usuario\\IdeaProjects\\Beathoven\\run.bat";
      final ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", batPath);
      File _file = new File("C:\\Users\\Usuario\\IdeaProjects\\Beathoven");
      processBuilder.directory(_file);
      processBuilder.inheritIO();
      final Process process = processBuilder.start();
      InputOutput.<String>println("\ud83d\ude80 Ejecutando run.bat...");
    } catch (final Throwable _t) {
      if (_t instanceof Exception) {
        final Exception e = (Exception)_t;
        InputOutput.<String>println("❌ Error al ejecutar el generador:");
        e.printStackTrace();
      } else {
        throw Exceptions.sneakyThrow(_t);
      }
    }
  }
}
