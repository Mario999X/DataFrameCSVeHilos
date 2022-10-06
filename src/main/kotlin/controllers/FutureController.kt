package controllers

import models.Accidente
import models.parse
import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.toDataFrame
import org.jetbrains.kotlinx.dataframe.io.readCSV
import java.io.File
import java.util.concurrent.CompletableFuture

object FutureController {

    private val fs = File.separator
    private val workingDirectory: String = System.getProperty("user.dir")
    private val pathProducts = workingDirectory + fs + "resources" + fs + "products.csv"
    private val pathAccidentes = workingDirectory + fs + "resources" + fs + "2022_Accidentalidad.csv"

    fun procesarFutures() {
        leerCSVs()
        leerCSVEsperando()
    }

    private fun leerCSVs() {
        val futureProducts = CompletableFuture.supplyAsync {
            println(DataFrame.readCSV(pathProducts))
        }

        val futureAccidentes = CompletableFuture.supplyAsync {
            println(parse(File(pathAccidentes)).toDataFrame())
        }

        futureProducts.get()
        futureAccidentes.get()

    }

    private fun leerCSVEsperando() {

        val futureProducts = CompletableFuture.supplyAsync {
            println(doReadProducts()?.get().toString())
        }

        val futureAccidentes = CompletableFuture.supplyAsync {
            println(doReadAccidentes()?.get().toString())
        }

        val futureRandom = CompletableFuture.supplyAsync {
            doReadProducts()?.get() ?: throw Exception ("Error en productos")
            doReadAccidentes()?.get() ?: throw Exception("Error en accidentes")
            println(numRandom())
        }

        futureProducts.get()
        futureAccidentes.get()
        futureRandom.get()

    }

    private fun doReadAccidentes(): CompletableFuture<DataFrame<Accidente>>? {
        val lectura = CompletableFuture.supplyAsync {
            parse(File(pathAccidentes)).toDataFrame()
        }
        return lectura
    }

    private fun doReadProducts(): CompletableFuture<DataFrame<*>>? {
        val lectura = CompletableFuture.supplyAsync {
            DataFrame.readCSV(pathProducts)
        }
        return lectura
    }

    private fun numRandom(): Int {
        return (1..1000).random()
    }
}