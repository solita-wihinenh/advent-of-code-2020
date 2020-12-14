package day14

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day14KtTest {

    val part1data = loadData("src/test/resources/day14part1.txt")
    val part2data = loadData("src/test/resources/day14part2.txt")

    @Test
    fun part1() {
        assertEquals(165, part1(part1data))
    }

    @Test
    fun part2() {
        assertEquals(208, part2(part2data))
    }
}