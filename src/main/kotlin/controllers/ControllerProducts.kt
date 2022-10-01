package controllers

import models.Lector
import models.Product
import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.cast
import org.jetbrains.kotlinx.dataframe.io.readCSV
import org.jetbrains.kotlinx.dataframe.io.writeCSV
import java.io.File
import kotlin.system.measureTimeMillis

object ControllerProducts {

    private val fs = File.separator
    private val workingDirectory: String = System.getProperty("user.dir")
    private val pathFile = workingDirectory + fs + "resources" + fs + "products.csv"

    fun procesarCopiaProductos() {

        val fileToRead = DataFrame.readCSV(pathFile)
        fileToRead.cast<Product>() // Aqui es opcional, no hacemos nada con ello.
        //fileToRead.print()

        println("Realizando copia de los productos")
        fileToRead.writeCSV(File(workingDirectory + fs + "data" + fs + "copiaProducts.csv"))
    }

    // --- HILOS ---
    fun procesamientoHilos() {
        procesarCSVHilosFijos()
    }

    fun procesarCSVHilosFijos() {
        val NUM_HILOS = 3
        println("Procesando con hilos fijos: $NUM_HILOS")

        measureTimeMillis {

            val lectores = mutableListOf<Thread>()
            repeat(NUM_HILOS) {
                val lector = Thread(Lector(File(pathFile)))
                lectores.add(lector)
                lector.start()
            }
            lectores.forEach { it.join() }
        }.also { println("Tiempo total: $it ms") }
    }


}