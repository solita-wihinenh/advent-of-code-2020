package day13

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day13KtTest {

    val testDataPart1 =  loadData("src/test/resources/day13part1.txt")

    @Test
    fun part1() {
        assertEquals(295, part1(testDataPart1))
    }

    @Test
    fun part2() {
        assertEquals(1068781, part2(testDataPart1))
    }
}