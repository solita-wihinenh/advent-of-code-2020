package day15

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day15KtTest {

    @Test
    fun part1() {
        assertEquals(436, part1(listOf(0,3,6), 2020))
    }

    @Test
    fun part2() {
        assertEquals(175594, part1(listOf(0,3,6), 30000000))
    }
}