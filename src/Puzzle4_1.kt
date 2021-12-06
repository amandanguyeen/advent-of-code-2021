/*
* shout out to that one leetcode question I did a month ago
* that made me remember I could easily store a 2D array as a regular list
* Not a fan of the 2 `breaks` but its 3:30am and I'LL TAKE IT
* */
fun main() {
    val input = readInput("puzzle4_input")
    val allBoards = createAllBoards(input.subList(2, input.size))
    val randomDraw = input[0].split(",")
    var finalScore = 0

    for (number in randomDraw) {
        for (board in allBoards) {
            if (number in board.allNumbers) {
                val (rowNumber, columnNumber) = board.incrementRowAndColumn(number)
                board.removeElement(number)
                if (board.checkIsWinner(rowNumber, columnNumber)) {
                    finalScore = board.getFinalScore(number.toInt())
                    break
                }
            }
        }
        if (finalScore != 0) break
    }
    println(finalScore)
}

private fun createAllBoards(input: List<String>): MutableList<Board> {
    var allBoards = mutableListOf<Board>()
    var allNumbers = mutableListOf<String>()
    for (line in input) {
        if (line != "") {
            allNumbers.addAll(line.split("  ", " ").filter { it != "" })
        } else {
            allBoards.add(Board(allNumbers))
            allNumbers = mutableListOf()
        }
    }
    allBoards.add(Board(allNumbers))
    return allBoards
}

class Board(
    val allNumbers: MutableList<String>
) {
    val row = mutableMapOf(0 to 0, 1 to 0, 2 to 0, 3 to 0, 4 to 0)
    val column = mutableMapOf(0 to 0, 1 to 0, 2 to 0, 3 to 0, 4 to 0)
    private val removedNumbers = allNumbers.toMutableList()

    fun getFinalScore(recentNumber: Int): Int {
        return removedNumbers.sumOf { it.toInt() } * recentNumber
    }

    fun removeElement(element: String) {
        removedNumbers.remove(element)
    }

    fun incrementRowAndColumn(number: String): Pair<Int, Int> {
        val numberIndex = allNumbers.indexOf(number)
        val rowNumber = numberIndex.floorDiv(5)
        val columnNumber = numberIndex.rem(5)
        row[rowNumber] = row[rowNumber]!!.inc()
        column[columnNumber] = column[columnNumber]!!.inc()
        return Pair(rowNumber, columnNumber)
    }

    fun checkIsWinner(rowNumber: Int, columnNumber: Int): Boolean {
        return row[rowNumber] == 5 || column[columnNumber] == 5
    }

}