/*
* Time: O(n)
* Space: O(1)
* */
fun main() {
    val input = readInput("puzzle1_input")
    val pointers = mutableListOf(0, 1)
    var counter = 0
    while (pointers[1] < input.size) {
        if (input[pointers[1]].toInt() > input[pointers[0]].toInt()) {
            counter += 1
        }
        pointers.mapInPlace { it + 1 }
    }
    println(counter)
}
