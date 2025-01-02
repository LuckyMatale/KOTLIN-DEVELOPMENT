fun main() {
    println("Please enter a number: ")
    val input = readLine()?.toIntOrNull()

    if(input != null) {
        if (input.isPrime()) {
            print("$input is a prime number")
        } else {
            print("$input is not a prime number")
        }
    }
}

fun Int.isPrime(): Boolean {
    for(i in 2 until this - 1){
        if(this % i == 0) {
            return false
        }
    }
    return true
}