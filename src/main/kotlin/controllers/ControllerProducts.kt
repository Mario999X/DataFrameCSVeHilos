package controllers

import models.Product
import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.cast
import org.jetbrains.kotlinx.dataframe.io.readCSV
import org.jetbrains.kotlinx.dataframe.io.writeCSV
import java.io.File

object ControllerProducts {

    private val pathFile = "resources" + File.separator + "products.csv"
    private val workingDirectory: String = System.getProperty("user.dir")

    fun procesarCopiaProductos() {

        val fileToRead = DataFrame.readCSV(workingDirectory + File.separator + pathFile)
        fileToRead.cast<Product>() // Aqui es opcional, no hacemos nada con ello.
        //fileToRead.print()

        println("Realizando copia de los productos")
        fileToRead.writeCSV(File(workingDirectory + File.separator + "data" + File.separator + "copiaProducts.csv"))
    }
}