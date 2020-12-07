package day7

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day7KtTest {

    private val part1testData = loadData("src/test/resources/day7part1.txt")
    private val part2testData = loadData("src/test/resources/day7part2.txt")

    @Test
    fun part1() {
        assertEquals(4, part1(part1testData, "shiny gold"))
    }

    @Test
    fun part2() {
        assertEquals(126, part2(part2testData, "shiny gold"))
    }
}