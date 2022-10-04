package models

import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.toDataFrame
import org.jetbrains.kotlinx.dataframe.io.readCSV
import java.io.File

open class Lector(path: File) : Runnable {

    var fileCSV = DataFrame.readCSV(path)

    override fun run() {
        //println(Thread.currentThread().name)
        println(fileCSV)

    }

}

class LectorAccidentes(path: File) : Lector(path) {

    var fileCSV2 = parse(File(path.toString())).toDataFrame()
    override fun run() {
        //println(Thread.currentThread().name)
        println(fileCSV2)
    }
}
