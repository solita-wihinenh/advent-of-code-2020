package day9

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day9KtTest {

    private val part1testData = loadData("src/test/resources/day9part1.txt")

    @Test
    fun part1() {
        assertEquals(127.toBigInteger(), part1(part1testData, 5))
    }

    @Test
    fun part2() {
        assertEquals(62.toBigInteger(), part2(part1testData, 5))
    }
}