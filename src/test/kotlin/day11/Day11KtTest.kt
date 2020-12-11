package day11

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day11KtTest {

    private val part1testData = loadData("src/test/resources/day11part1.txt")

    @Test
    fun part1() {
        assertEquals(37, part1(part1testData))
    }

    @Test
    fun part2() {
        assertEquals(26, part2(part1testData))
    }
}