import controllers.ControllerAccidentes
import controllers.ControllerProducts

fun main() {

    println("Ejecutando App \n")

    ControllerProducts.procesarCopiaProductos()
    ControllerAccidentes.procesarCopiasAccidentes()

    ControllerAccidentes.procesarConsultas()

    println("Cerrando App")
}