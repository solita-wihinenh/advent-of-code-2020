package day10

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day10KtTest {

    private val part1sample1 = loadData("src/test/resources/day10part1sample1.txt")
    private val part1sample2 = loadData("src/test/resources/day10part1sample2.txt")

    @Test
    fun part1() {
        assertEquals(7*5,part1(part1sample1))
        assertEquals(22*10,part1(part1sample2))
    }

    @Test
    fun part2() {
        assertEquals(8.toBigInteger(),part2(part1sample1))
        assertEquals(19208.toBigInteger(),part2(part1sample2))
    }
}