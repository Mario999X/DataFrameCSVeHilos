package controllers

import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

object ControllerDir {

    fun comprobarDirData() {
        val fs = File.separator
        val workingDirectory: String = System.getProperty("user.dir")
        val pathData = Paths.get(workingDirectory + fs + "data")
        val pathConsultas = Paths.get(workingDirectory + fs + "data" + fs + "consultas")

        val isDirData = Files.isDirectory(pathData)
        val isDirConsultas = Files.isDirectory(pathConsultas)

        if (!isDirData) Files.createDirectory(pathData) + println("Directorio Creado $pathData")
        if (!isDirConsultas) Files.createDirectory(pathConsultas) + println("Directorio Creado 2 $pathConsultas")
    }
}