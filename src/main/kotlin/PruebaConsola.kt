fun main(){
    println("Cuidado con la consola y los println") // Comentando esta linea se altera el resultado
    for (i in 1..100){
        if (i %2 == 0) println("$i") else System.err.println("$i")
    }
}