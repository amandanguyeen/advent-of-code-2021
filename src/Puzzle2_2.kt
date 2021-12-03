/*
* Time: O(n)
* Space: O(1)
* */
fun main() {
    val input = readInput("puzzle2_input")
    var depth = 0
    var horizontal = 0
    var aim = 0
    val regex = "[\\d]\$".toRegex()

    for (line in input) {
        val value = regex.find(line)!!.value.toInt()
        if (line.contains("forward")) {
            horizontal = horizontal.plus(value)
            depth = depth.plus(value * aim)
        }
        else if (line.contains("down")) {
            aim = aim.plus(value)
        } else if (line.contains("up")) {
            aim = aim.minus(value)
        }
    }
    println(horizontal * depth)
}
