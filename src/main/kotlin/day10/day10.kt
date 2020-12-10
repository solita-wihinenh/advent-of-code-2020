package day10

import java.io.File

fun main() {
    println("Day 10 - Start!")
    val adapters = loadData("src/main/resources/day10.txt")
    println("Part 1: ${part1(adapters)}")
    println("Part 2: ${part2(adapters)}")
}

fun loadData(fileName: String): List<Int> {
    return File(fileName).readLines().map { string -> string.toInt() }.toList()
}

fun part1(input: List<Int>): Int {
    var ones = 0
    var threes = 0

    input.sorted().fold(0) {
        acc, i ->
        when (i - acc) {
            1 -> ones++
            3 -> threes++
        }
        i
    }

    return ones * (threes + 1)
}

fun part2(joltages: List<Int>): Long {

    class Adapter(val joltage: Int, val possibleCombinations: Long)

    val adapters = ArrayDeque<Adapter>(3)

    fun addAdapter(c: Adapter) {
        if (adapters.size >= 3) adapters.removeFirst()
        adapters.add(c)
    }

    addAdapter(Adapter(0, 1L))
    joltages.sorted().forEach { joltage ->
        val possibleCombinations = adapters.sumOf { previous ->
            val diff = joltage - previous.joltage
            if (diff <= 3) {
                previous.possibleCombinations
            } else {
                0
            }
        }
        addAdapter(Adapter(joltage, possibleCombinations))
    }

    return adapters.last().possibleCombinations
}