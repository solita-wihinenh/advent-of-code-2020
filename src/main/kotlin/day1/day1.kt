package day1

import java.io.File

fun main() {
    println("Day 1 - Start!")
    val numbers = loadData("src/main/resources/day1.txt")
    println("Part 1: " + findProductForSum(numbers, 2, 2020))
    println("Part 2: " + findProductForSum(numbers, 3, 2020))
}

fun loadData(fileName: String): List<Int> {
    return File(fileName).readLines().map { string -> string.toInt() }.toList()
}

fun findProductForSum(
    numbers: List<Int>,
    numbersToUse: Int,
    targetSum: Int,
    testedNumbers: MutableList<Int> = mutableListOf(),
    startIndex: Int = 0
): Int {
    for (currentIndex in startIndex until numbers.size) {
        testedNumbers.add(numbers[currentIndex])
        val sum = testedNumbers.sum()
        if (sum == targetSum && numbersToUse == testedNumbers.count()) {
            return (testedNumbers.reduce { acc, i -> acc * i })
        } else if (sum < targetSum && testedNumbers.count() < numbersToUse) {
            val result = findProductForSum(numbers, numbersToUse, targetSum, testedNumbers, currentIndex + 1)
            if (result != -1) {
                return result
            }
        }
        testedNumbers.removeLast()
    }
    return -1
}