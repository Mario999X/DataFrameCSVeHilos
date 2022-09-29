package controllers

import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

object ControllerDir {

    fun comprobarDirData(){
        val workingDirectory: String = System.getProperty("user.dir")
        val path = Paths.get(workingDirectory + File.separator + "data")

        val isDir = Files.isDirectory(path)

        if (!isDir) Files.createDirectory(path) + println("Directorio Creado")

    }
}