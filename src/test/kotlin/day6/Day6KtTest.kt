package day6

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day6KtTest {

    private val testData = loadData("src/test/resources/day6.txt")

    @Test
    fun part1() {
        assertEquals(11, part1(testData))
    }

    @Test
    fun part2() {
        assertEquals(6, part2(testData))
    }
}