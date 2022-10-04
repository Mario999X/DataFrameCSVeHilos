package controllers

import models.Accidente
import models.LectorAccidentes
import models.parse
import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.*
import org.jetbrains.kotlinx.dataframe.io.readCSV
import org.jetbrains.kotlinx.dataframe.io.writeCSV
import java.io.File
import java.util.concurrent.Executors
import kotlin.system.measureTimeMillis

object ControllerAccidentes {

    private val fs = File.separator
    private val workingDirectory: String = System.getProperty("user.dir")
    private val pathFile = workingDirectory + fs + "resources" + fs + "2022_Accidentalidad.csv"

    fun procesarCopiasAccidentes() {

        // Realizando directamente con el metodo readCSV de DataFrame da como resultado una lectura no precisa.
        // Esto puede servir para un csv enano, como en el caso de products.csv
        val fileRead = DataFrame.readCSV(pathFile)
        fileRead.cast<Accidente>()

        println("Realizando copia rapida accidentes")
        fileRead.writeCSV(workingDirectory + fs + "data" + fs + "copiaAccidentes.csv")

        // Por lo tanto, necesitamos precisar la lectura. Usamos el metodo creado en el modelo
        val fileToRead = parse(File(pathFile))
        val castFile = fileToRead.toDataFrame()
        castFile.cast<Accidente>() // Aqui es opcional, no hacemos nada con ello.

        println("Realizando copia precisa accidentes")
        castFile.writeCSV(File(workingDirectory + fs + "data" + fs + "copiaAccidentesParse.csv"))
    }


    fun procesarConsultas() {
        val fileToRead = parse(File(pathFile))
        val df = fileToRead.toDataFrame()
        df.cast<Accidente>()

        //println(df.schema())

        val numCalleMax = df.max("numero").toString()
        println("Numero maximo detectado: $numCalleMax")
        print("Introduzca un número existente: ")
        val numCalle = readln().toLongOrNull()

        if (numCalle != null) {
            if (numCalle <= numCalleMax.toInt()) {
                val primeraConsulta = df.filter { it["numero"] == numCalle }
                // hadouken
                primeraConsulta.writeCSV(File(workingDirectory + fs + "data" + fs + "consultas" + fs + "ConsultaNumCalle$numCalle.csv"))
                println("Consulta numCalle $numCalle realizada")
            } else println("Error numero maximo")
        } else println("Error Null")
    }

    // ---- HILOS ----
    fun procesamientoHilos() {
        procesarCSVHilosFijos()
        procesarExecuteRunnable()
    }

    private fun procesarCSVHilosFijos() {
        val NUM_HILOS = 5

        println("Procesando con hilos fijos: $NUM_HILOS")

        measureTimeMillis {

            val lectores = mutableListOf<Thread>()
            repeat(NUM_HILOS) {
                val lector = Thread(LectorAccidentes((File(pathFile))))
                lectores.add(lector)
                lector.start()
            }
            lectores.forEach { it.join() }
        }.also { println("Tiempo: $it ms") }
    }

    private fun procesarExecuteRunnable() {
        val NUM_HILOS = 5

        println("Procesando con una Thread Pool: $NUM_HILOS")

        val lectores = mutableListOf<String>()
        repeat((5..10).random()) {
            lectores.add("Lector ${it + 1}")
        }

        println(lectores)

        val executor = Executors.newFixedThreadPool(NUM_HILOS)
        measureTimeMillis {
            lectores.forEach { _ ->
                executor.execute(LectorAccidentes(File(pathFile)))
            }
            executor.shutdown()
        }.also {
            Thread.sleep(500)
            println("Tiempo $it ms")
        }
    }

}

