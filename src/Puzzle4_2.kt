/*
* THE ONE WAS SO HARD
* the `continue` literally SAVED ME IDK WHAT IT DOES BUT IT WORKS
* */
fun main() {
    val input = readInput("puzzle4_input")
    val allBoards = createAllBoards(input.subList(2, input.size))
    val randomDraw = input[0].split(",")
    var finalScore = 0
    val remainingBoard = mutableListOf<Board>()

    for (number in randomDraw) {
        for (board in allBoards) {
            if (remainingBoard.contains(board)) continue // i bow down to you
            else {
                if (number in board.allNumbers) {
                    val (rowNumber, columnNumber) = board.incrementRowAndColumn(number)

                    if (board.checkIsWinner(rowNumber, columnNumber) && remainingBoard.size != allBoards.size - 1) {
                        remainingBoard.add(board)
                    } else if (board.checkIsWinner(rowNumber, columnNumber)) {
                        board.removeElement(number)
                        finalScore = board.getFinalScore(number.toInt())
                        break
                    } else {
                        board.removeElement(number)
                    }
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
