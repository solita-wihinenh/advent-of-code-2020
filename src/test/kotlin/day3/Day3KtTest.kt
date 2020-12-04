package day3

import day2.Password
import day2.parsePassword
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day3KtTest {

    @Test
    fun calculateTreesHit() {
        val map = listOf(
            "..##.......",
            "#...#...#..",
            ".#....#..#.",
            "..#.#...#.#",
            ".#...##..#.",
            "..#.##.....",
            ".#.#.#....#",
            ".#........#",
            "#.##...#...",
            "#...##....#",
            ".#..#...#.#")
        assertEquals(calculateTreesHit(map, 0, 0, 3, 1), 7);
    }
}