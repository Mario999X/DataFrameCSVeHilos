import controllers.ControllerAccidentes
import controllers.ControllerProducts
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

fun main() {

    println("Ejecutando App \n")

    // Comprobacion del directorio
    val workingDirectory: String = System.getProperty("user.dir")
    val path = Paths.get(workingDirectory + File.separator + "data")

    val isDir = Files.isDirectory(path)

    if (!isDir) Files.createDirectory(path)

    // Ejecucion de los controladores
    ControllerProducts.procesarCopiaProductos()
    ControllerAccidentes.procesarCopiasAccidentes()

    ControllerAccidentes.procesarConsultas()

    println("Cerrando App")
}