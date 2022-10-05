import controllers.ControllerAccidentes
import controllers.ControllerDir
import controllers.ControllerProducts

fun main() {

    println("Ejecutando App \n")

    // --- Comprobacion del directorio ---
    ControllerDir.comprobarDirData()

    // --- Ejecucion de los controladores ---
    ControllerProducts.procesarCopiaProductos()
    ControllerAccidentes.procesarCopiasAccidentes()

    ControllerAccidentes.procesarConsultas()

    // --- Ejecucion de los controladores (Hilos) ---
    ControllerProducts.procesamientoHilos()
    ControllerAccidentes.procesamientoHilos()

    println("Cerrando App")
}