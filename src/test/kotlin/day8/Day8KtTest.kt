package day8

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day8KtTest {

    private val part1testData = loadData("src/test/resources/day8part1.txt")
    private val part2testData = loadData("src/test/resources/day8part2.txt")


    @Test
    fun part1() {
        assertEquals(5, part1(part1testData))
    }

    @Test
    fun part2() {
        assertEquals(8, part2(part2testData))
    }
}