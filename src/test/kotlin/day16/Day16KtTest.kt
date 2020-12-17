package day16

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day16KtTest {

    val part1data = loadData("src/test/resources/day16part1.txt")
    val part2data = loadData("src/test/resources/day16part2.txt")

    @Test
    fun part1() {
        assertEquals(71, part1(part1data))
    }

    @Test
    fun part2() {
        assertEquals(12*11, part2(part2data))
    }
}