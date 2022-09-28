package controllers

import models.Accidente
import models.parse
import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.*
import org.jetbrains.kotlinx.dataframe.io.readCSV
import org.jetbrains.kotlinx.dataframe.io.writeCSV
import java.io.File

object ControllerAccidentes {

    private val pathFile = "resources" + File.separator + "2022_Accidentalidad.csv"
    private val workingDirectory: String = System.getProperty("user.dir")
    private val fs = File.separator

    fun procesarCopiasAccidentes() {

        // Realizando directamente con el metodo readCSV de DataFrame da como resultado una lectura no precisa.
        // Esto puede servir para un csv enano, como en el caso de products.csv
        val fileRead = DataFrame.readCSV(workingDirectory + fs + pathFile)
        fileRead.cast<Accidente>()

        println("Realizando copia rapida accidentes")
        fileRead.writeCSV(workingDirectory + fs + "data" + fs + "copiaAccidentes.csv")

        // Por lo tanto, necesitamos precisar la lectura. Usamos el metodo creado en el modelo
        val fileToRead = parse(File(workingDirectory + fs + pathFile))
        val castFile = fileToRead.toDataFrame()
        castFile.cast<Accidente>() // Aqui es opcional, no hacemos nada con ello.

        println("Realizando copia precisa accidentes")
        castFile.writeCSV(File(workingDirectory + fs + "data" + fs + "copiaAccidentesParse.csv"))
    }

    fun procesarConsultas() {
        val fileToRead = parse(File(workingDirectory + fs + pathFile))
        val df = fileToRead.toDataFrame()
        df.cast<Accidente>()

        //println(df.schema())

        val numCalleMax = df.max("numero").toString()
        println("Numero maximo detectado: $numCalleMax")
        print("Introduzca un número existente: ")
        val numCalle = readln().toIntOrNull()

        if (numCalle != null) {
            val primeraConsulta = df.filter { it["numero"] == numCalle }

            primeraConsulta.writeCSV(File(workingDirectory + fs + "data" + fs + "ConsultaNumCalle$numCalle.csv"))
            println("Consulta numCalle $numCalle realizada")
        } else print("Error")
    }

}
