package day15

import java.time.LocalDateTime

fun main() {
    println("Day 15 - Start!")
    println("Part 1: ${part1(listOf(11, 0, 1, 10, 5, 19), 2020)}")
    println("Part 2: ${part1(listOf(11, 0, 1, 10, 5, 19), 30000000)}")
}

fun part1(input: List<Int>, iterations: Int): Int {
    val saidNumbers = mutableMapOf<Int, Int>()
    var lastSaidDiff: Int? = null
    var said = -1
    for (i in input.indices) {
        said = input[i]
        val lastSaidTurn = saidNumbers[said]
        lastSaidDiff = if (lastSaidTurn == null) null else i - lastSaidTurn
        saidNumbers[said] = i + 1
    }

    for (i in input.size + 1..iterations) {
        said = lastSaidDiff ?: 0
        val lastSaidTurn = saidNumbers[said]
        lastSaidDiff = if (lastSaidTurn == null) null else i - lastSaidTurn
        saidNumbers[said] = i
    }
    return said
}