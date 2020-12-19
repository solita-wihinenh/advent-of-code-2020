package day19

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day19KtTest {

    val input = loadData("src/test/resources/day19part1.txt")
    val inputPart2unchanged = loadData("src/test/resources/day19part2unchanged.txt")
    val inputPart2changed = loadData("src/test/resources/day19part2changed.txt")

    @Test
    fun part1() {
        assertEquals(2,part1(input))
        assertEquals(3,part1(inputPart2unchanged))
        assertEquals(12,part1(inputPart2changed))
    }
}