import controllers.AccidentesController
import controllers.DirController
import controllers.FutureController
import controllers.ProductsController

fun main() {

    println("Ejecutando App \n")

    // --- Comprobacion del directorio ---
    //DirController.comprobarDirData()

    // --- Ejecucion de los controladores ---
    //ProductsController.procesarCopiaProductos()
    AccidentesController.momentoActual()
    //AccidentesController.procesarCopiasAccidentes()

    //AccidentesController.procesarConsultas()

    // --- Ejecucion de los controladores (Hilos) ---
    //ProductsController.procesamientoHilos()
    //AccidentesController.procesamientoHilos()

    // --- Ejecucion de future ---
    //FutureController.procesarFutures()

    // --- Ejecucion de graficas ---
    //AccidentesController.graficaGeneroConteo()

    println("Cerrando App")
}