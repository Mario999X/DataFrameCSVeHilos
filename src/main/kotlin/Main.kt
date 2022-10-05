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
    //AccidentesController.procesarCopiasAccidentes()

    //AccidentesController.procesarConsultas()

    // --- Ejecucion de los controladores (Hilos) ---
    //ProductsController.procesamientoHilos()
    //AccidentesController.procesamientoHilos()

    // --- Ejecucion de future ---
    FutureController.procesarFutures()

    println("Cerrando App")
}