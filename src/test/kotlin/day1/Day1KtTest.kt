package day1

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day1KtTest {

    @Test
    fun findProductForSum() {
        assertNotEquals(2020, findProductForSum(listOf(2020), 2, 2020))
        assertEquals(2020, findProductForSum(listOf(2020), 1, 2020))
        assertEquals(514579, findProductForSum(listOf(1721, 979, 366, 299, 675, 1456), 2, 2020))
        assertEquals(241861950, findProductForSum(listOf(1721, 979, 366, 299, 675, 1456), 3, 2020), )
        assertEquals(100000000, findProductForSum(listOf(190249, 10, 10, 1000, 1000), 4, 2020), )
    }
}