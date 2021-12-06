/*
* Input: text file of bits
* Output: gamma rate * epsilon rate (converted into ints)
* 1. Initialize an empty list with its size the same as the length of 5 (index of each bit)
* 2. Iterate through each line of the file and break apart the string into a list
* 3. Iterate through this new list, and add the value with respect to the index in the first initialized list
*       ie. [1,0,0,0,0] => initialList[0] += 1, initialList[1] += 0
* 4. I know that the number of bits in my file is the length of input
* 5. If I look at the index of my initialized list and the number is more than half of the length of the input
*    I know that the most common bit at that index was 1
*    ex. [400,600,200,800,501] index 1, 3, 4 have more 1 bits (file size is 1000 lines)
* */
fun main() {
    val input = readInput("puzzle3_input")
    val countedBits = mutableMapOf<Int, Int>()
    for (line in input) {
        val splitBits = line.chunked(1)
        for (i in splitBits.indices) {
             countedBits[i] = (countedBits[i] ?: 0) + splitBits[i].toInt()
        }
    }
    val gammaEpsilonPair = getGammaAndEpsilonInt(countedBits, input.size / 2)
    println(gammaEpsilonPair.first * gammaEpsilonPair.second)
}

private fun getGammaAndEpsilonInt(countedBits: MutableMap<Int, Int>, fileSize: Int): Pair<Int, Int> {
    var gamma = ""
    var epsilon = ""
    for ((key, value) in countedBits) {
        if (value > fileSize) {
            gamma = gamma.plus("1")
            epsilon = epsilon.plus("0")
        } else {
            gamma = gamma.plus("0")
            epsilon = epsilon.plus("1")
        }
    }
    return Pair(convertBinaryToDecimal(gamma.toLong()), convertBinaryToDecimal(epsilon.toLong()))

}

private fun convertBinaryToDecimal(num: Long): Int {
    var num = num
    var decimalNumber = 0
    var i = 0
    var remainder: Long

    while (num.toInt() != 0) {
        remainder = num % 10
        num /= 10
        decimalNumber += (remainder * Math.pow(2.0, i.toDouble())).toInt()
        ++i
    }
    return decimalNumber
}
