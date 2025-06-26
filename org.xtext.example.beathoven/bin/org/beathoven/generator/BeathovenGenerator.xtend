package org.beathoven.generator

import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.IFileSystemAccess2
import org.eclipse.xtext.generator.IGeneratorContext
import org.eclipse.xtext.generator.AbstractGenerator
import org.eclipse.xtext.serializer.ISerializer
import com.google.inject.Inject
import java.io.File
import java.io.BufferedWriter
import java.io.FileWriter

class BeathovenGenerator extends AbstractGenerator {

    @Inject
    ISerializer serializer

    override void doGenerate(Resource resource, IFileSystemAccess2 fsa, IGeneratorContext context) {
        try {
            val inputPath = "C:\\Users\\Usuario\\IdeaProjects\\Beathoven\\beats\\ejemplo.beat"
            val content = serializer.serialize(resource.contents.head)

            // 📝 Guardar archivo en carpeta externa (proyecto de IntelliJ)
            val file = new File(inputPath)
            val writer = new BufferedWriter(new FileWriter(file, false))
            writer.write(content)
            writer.close
            println("📝 Archivo .beat guardado en: " + inputPath)

            // 🚀 Ejecutar el script BAT
            val batPath = "C:\\Users\\Usuario\\IdeaProjects\\Beathoven\\run.bat"
            val processBuilder = new ProcessBuilder("cmd.exe", "/c", batPath)
            processBuilder.directory(new File("C:\\Users\\Usuario\\IdeaProjects\\Beathoven"))
            processBuilder.inheritIO() // Esto muestra la consola real
            val process = processBuilder.start
            println("🚀 Ejecutando run.bat...")

        } catch (Exception e) {
            println("❌ Error al ejecutar el generador:")
            e.printStackTrace
        }
    }
}
