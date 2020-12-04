package day2

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day2KtTest {

    @Test
    fun parsePassword() {
        assertEquals(parsePassword("1-3 a: abcde"), Password("abcde", "a", 1, 3));
        assertEquals(parsePassword("1-3 b: cdefg"), Password("cdefg", "b", 1, 3));
        assertEquals(parsePassword("2-9 c: ccccccccc"), Password("ccccccccc", "c", 2, 9));
    }

    @Test
    fun part1validatePassword() {
        assertTrue(part1validatePassword(Password("abcde", "a", 1, 3)))
        assertFalse(part1validatePassword(Password("cdefg", "b", 1, 3)))
        assertTrue(part1validatePassword(Password("ccccccccc", "c", 2, 9)))
    }

    @Test
    fun part2validatePassword() {
        assertTrue(part2validatePassword(Password("abcde", "a", 1, 3)))
        assertFalse(part2validatePassword(Password("cdefg", "b", 1, 3)))
        assertFalse(part2validatePassword(Password("ccccccccc", "c", 2, 9)))
    }
}