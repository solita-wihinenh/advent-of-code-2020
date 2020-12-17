package day17

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day17KtTest {

    val part1data = loadData("src/test/resources/day17part1.txt")

    @Test
    fun part1() {
        assertEquals(11, part1(part1data, 1))
        assertEquals(21, part1(part1data, 2))
        assertEquals(38, part1(part1data, 3))
        assertEquals(112, part1(part1data, 6))
    }

    @Test
    fun part2() {
    }
}