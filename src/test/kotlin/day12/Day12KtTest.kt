package day12

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day12KtTest {

    val input = loadData("src/test/resources/day12part1.txt")

    @Test
    fun part1() {
        assertEquals(25, part1(input))
    }

    @Test
    fun part2() {
        assertEquals(286, part2(input))
    }
}