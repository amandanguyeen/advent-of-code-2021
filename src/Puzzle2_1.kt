/*
* Time: O(n)
* Space: O(1)
* */
fun main() {
    val input = readInput("puzzle2_input")
    var depth = 0
    var horizontal = 0
    val regex = "[\\d]\$".toRegex()
    for (line in input) {
        val value = regex.find(line)!!.value.toInt()
        if (line.contains("forward")) {
            horizontal = horizontal.plus(value)
        }
        else if (line.contains("down")) {
            depth = depth.plus(value)
        } else if (line.contains("up")) {
            depth = depth.minus(value)
        }
    }
    println(horizontal * depth)
}
