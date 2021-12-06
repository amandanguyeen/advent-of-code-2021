/*
* Not my best work 🥲
* */
fun main() {
    val input = readInput("puzzle3_input")
    val lifeSupportRating = getLifeSupportRating(input)
    println(lifeSupportRating)
}

private fun getLifeSupportRating(input: List<String>): Int {
    val bitSize = input[0].length
    val splitInput = input.partition { it[0] == "1".single() }

    return if (splitInput.first.size >= splitInput.second.size) {
        getFinalOxygenRating(bitSize, splitInput.first) * getFinalCO2Rating(bitSize, splitInput.second)
    } else {
        getFinalOxygenRating(bitSize, splitInput.second) * getFinalCO2Rating(bitSize, splitInput.first)
    }

}

private fun getFinalOxygenRating(bitSize: Int, oxygenList: List<String>): Int {
    var counter = 1
    var oxygenList = oxygenList
    while (counter < bitSize && oxygenList.size != 1) {
        val splitOxygenList = oxygenList.partition { it[counter] == "1".single() }
        if (splitOxygenList.first.size >= splitOxygenList.second.size) {
            oxygenList = splitOxygenList.first
        } else {
            oxygenList = splitOxygenList.second
        }
        counter++
    }
    return convertBinaryToDecimal(oxygenList[0].toLong())
}

private fun getFinalCO2Rating(bitSize: Int, CO2List: List<String>): Int {
    var counter = 1
    var CO2List = CO2List
    while (counter < bitSize && CO2List.size != 1) {
        val splitOxygenList = CO2List.partition { it[counter] == "0".single() }
        if (splitOxygenList.first.size <= splitOxygenList.second.size) {
            CO2List = splitOxygenList.first
        } else {
            CO2List = splitOxygenList.second
        }
        counter++
    }
    return convertBinaryToDecimal(CO2List[0].toLong())

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
