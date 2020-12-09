package day9

import java.io.File
import java.math.BigInteger

fun main() {
    println("Day 9 - Start!")
    val numbers = loadData("src/main/resources/day9.txt")
    println("Part 1: ${part1(numbers, 25)}")
    println("Part 2: ${part2(numbers, 25)}")
}

fun loadData(fileName: String): List<BigInteger> {
    return File(fileName).readLines().map { string -> string.toBigInteger() }.toList()
}

fun part1(numbers: List<BigInteger>, preambleLength: Int): BigInteger {
    return findInvalidNumberInList(numbers, preambleLength) ?: throw Error("No invalid number found")
}

fun findInvalidNumberInList(numbers: List<BigInteger>, preambleLength: Int): BigInteger? {
    for (i in preambleLength + 1 until numbers.size) {
        if (!validateIndex(numbers, preambleLength, i)) {
            return numbers[i]
        }
    }
    return null
}

fun validateIndex(numbers: List<BigInteger>, preambleLength: Int, index: Int): Boolean {
    for (i in index - preambleLength until index) {
        for (j in i + 1 until index) {
            if (numbers[i] + numbers[j] == numbers[index]) {
                return true
            }
        }
    }
    return false
}

fun part2(numbers: List<BigInteger>, preambleLength: Int): BigInteger {
    val invalidNumber = findInvalidNumberInList(numbers, preambleLength) ?: throw Error("No invalid number found")
    val contiguousRange = findContiguousRange(numbers, invalidNumber)
    val min = contiguousRange.minOrNull() ?: throw Error("Set is empty");
    val max = contiguousRange.maxOrNull() ?: throw Error("Set is empty");
    return min + max
}

fun findContiguousRange(numbers: List<BigInteger>, targetSum: BigInteger): List<BigInteger> {
    for (i in numbers.indices) {
        var sum = BigInteger.ZERO
        for (j in i until numbers.size) {
            sum += numbers[j]
            if (sum == targetSum) {
                return numbers.subList(i, j)
            }
        }
    }
    return emptyList()
}