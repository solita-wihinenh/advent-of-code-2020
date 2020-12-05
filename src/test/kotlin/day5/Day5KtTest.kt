package day5

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day5KtTest {

    @Test
    fun decodeSeatRow() {
        assertEquals(44, decodeSeatRow("FBFBBFFRLR"));
        assertEquals(70, decodeSeatRow("BFFFBBFRRR"));
        assertEquals(14, decodeSeatRow("FFFBBBFRRR"));
        assertEquals(102, decodeSeatRow("BBFFBBFRLL"));
    }

    @Test
    fun decodeSeatColumn() {
        assertEquals(5, decodeSeatColumn("FBFBBFFRLR"));
        assertEquals(7, decodeSeatColumn("BFFFBBFRRR"));
        assertEquals(7, decodeSeatColumn("FFFBBBFRRR"));
        assertEquals(4, decodeSeatColumn("BBFFBBFRLL"));

    }

    @Test
    fun decodeSeatId() {
        assertEquals(357, decodeSeatId("FBFBBFFRLR"));
        assertEquals(567, decodeSeatId("BFFFBBFRRR"));
        assertEquals(119, decodeSeatId("FFFBBBFRRR"));
        assertEquals(820, decodeSeatId("BBFFBBFRLL"));

    }
}