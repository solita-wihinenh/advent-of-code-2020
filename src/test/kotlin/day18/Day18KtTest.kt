package day18

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day18KtTest {

    @Test
    fun part1() {
        assertEquals(71,evaluate("(1 + 2 * 3 + 4 * 5 + 6)"))
        assertEquals(51,evaluate("(1 + (2 * 3) + (4 * (5 + 6)))"))
        assertEquals(26,evaluate("(2 * 3 + (4 * 5))"))
        assertEquals(437,evaluate("(5 + (8 * 3 + 9 + 3 * 4 * 3))"))
        assertEquals(12240,evaluate("(5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4)))"))
        assertEquals(13632,evaluate("(((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2)"))
    }

    @Test
    fun part2() {
        val precedences = listOf("+", "*")
        assertEquals(231,evaluate("(1 + 2 * 3 + 4 * 5 + 6)", precedences))
        assertEquals(51,evaluate("(1 + (2 * 3) + (4 * (5 + 6)))", precedences))
        assertEquals(46,evaluate("(2 * 3 + (4 * 5))", precedences))
        assertEquals(1445,evaluate("(5 + (8 * 3 + 9 + 3 * 4 * 3))", precedences))
        assertEquals(669060,evaluate("(5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4)))", precedences))
        assertEquals(23340,evaluate("(((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2)", precedences))
    }
}