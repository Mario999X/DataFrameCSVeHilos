import controllers.ControllerAccidentes
import controllers.ControllerDir
import controllers.ControllerProducts

fun main() {

    println("Ejecutando App \n")

    // Comprobacion del directorio
    ControllerDir.comprobarDirData()

    // Ejecucion de los controladores
    ControllerProducts.procesarCopiaProductos()
    ControllerAccidentes.procesarCopiasAccidentes()

    ControllerAccidentes.procesarConsultas()

    println("Cerrando App")
}