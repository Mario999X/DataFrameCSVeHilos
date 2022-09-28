package models

import org.jetbrains.kotlinx.dataframe.annotations.DataSchema
import java.io.File

@DataSchema
data class Accidente(
    val numExpediente: String,
    val fecha: String,
    val hora: String,
    val localizacion: String,
    val numero: Int,
    val distrito: String,
    val tipoAccidente: String,
    val meteorologia: String,
    val tipoVehiculo: String,
    val tipoPersona: String,
    val sexo: String,
    val positivoAlcohol: Boolean = false,
    val positivoDrogas: Boolean = false,
)

// Creamos un metodo que recibe un archivo (o File) y devuelve una lista <Model>
// Generamos una variable que sea una lista, y leemos todas las lineas del archivo pasado, saltandonos la cabecera en este caso.
// PD. La cabecera nueva (copia + consultas) recibira los nombres que escribamos en las variables de esta clase (arriba/abajo del metodo)
fun parse(csvFile: File): List<Accidente> {

    val accidentes: List<Accidente> = csvFile.readLines()
        .drop(1)
        .map { it.split(";") }
        .map {
            it.map { campo -> campo.trim() }
            Accidente(
                numExpediente = it[0],
                fecha = it[1],
                hora = it[2],
                localizacion = it[3],
                numero = getNumero(it[4]),
                distrito = it[6],
                tipoAccidente = it[7],
                meteorologia = it[8],
                tipoVehiculo = it[9],
                tipoPersona = it[10],
                sexo = it[12],
                positivoAlcohol = it[17] == "S",
                positivoDrogas = it[18] == "1"
            )
        }
    return accidentes
}

// Quiero obtener el numero entero, puede que no sea la mejor forma, y al final lo mas sencillo sea pasarlo como String
// O incluso, transformarlo luego cuando se haya leido (siendo String)

// La documentacion de la CM tampoco ayuda mucho, por lo que esta es la mejor solucion a la que he llegado, generando un pequeño "filtro"
private fun getNumero(numero: String): Int {
    var numero = numero
    var filtro = "[A-Z a-z]".toRegex()
    // Forma mas comoda de realizar un if
    // numero = if (numero.contains("+") || numero.contains(filtro)) "0" else numero

    // Forma java
    numero = if (numero.startsWith("+") || numero.contains(filtro)) {
        "0"
    } else {
        numero
    }

    return numero.toInt()
}

