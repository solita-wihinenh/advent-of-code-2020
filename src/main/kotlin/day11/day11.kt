package day11

import java.io.File

fun main() {
    println("Day 11 - Start!")
    val input = loadData("src/main/resources/day11.txt")
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}

fun loadData(fileName: String): List<String> {
    return File(fileName).readLines()
}

fun part1(input: List<String>): Int {
    return countSeatsAfterSimulation(input, ::seatWillBeOccupiedPart1)
}

fun part2(input: List<String>): Int {
    return countSeatsAfterSimulation(input, ::seatWillBeOccupiedPart2)
}

fun countSeatsAfterSimulation(input: List<String>, seatingFunction: (Int, Int, List<String>) -> Boolean): Int {
    var previous: List<String>
    var modified = input
    var iterations = 0
    do {
        iterations++
        previous = modified.toList()
        modified = simulateRound(previous, seatingFunction)
        if (iterations > 10000) throw Error("Infinite loop")
    } while (modified != previous)
    return countOccupiedSeats(modified)
}

fun countOccupiedSeats(seats: List<String>): Int {
    return seats.sumBy { row -> row.filter { c -> c == '#' }.count() }
}

fun simulateRound(source: List<String>, seatingFunction: (Int, Int, List<String>) -> Boolean): List<String> {
    val result = source.toMutableList()
    for (row in source.indices) {
        for (col in source[row].indices) {
            if (source[row][col] != '.') {
                if (seatingFunction(row, col, source)) {
                    result[row] = result[row].replaceRange(IntRange(col, col), "#")
                } else {
                    result[row] = result[row].replaceRange(IntRange(col, col), "L")
                }
            }
        }
    }
    return result
}

fun seatWillBeOccupiedPart1(row: Int, col: Int, seats: List<String>): Boolean {
    val occupiedNeighbors = countOccupiedNeighbors(row, col, seats, 1)
    if (seats[row][col] == 'L') {
        if (occupiedNeighbors == 0) {
            return true
        }
    } else if (seats[row][col] == '#') {
        if (occupiedNeighbors >= 4) {
            return false
        }
    }
    return (seats[row][col] == '#')
}

fun countOccupiedNeighbors(row: Int, col: Int, seats: List<String>, range: Int): Int {
    var occupiedNeighbors = 0
    for (i in -1..1) {
        for (j in -1..1) {
            if (i == 0 && j == 0) {
                continue
            }
            if (directionIsOccupied(row, col, seats, range, i, j)) {
                occupiedNeighbors++
            }
        }
    }
    return occupiedNeighbors
}

fun directionIsOccupied(row: Int, col: Int, seats: List<String>, range: Int, rowChange: Int, colChange: Int): Boolean {
    var testedRow = row + rowChange
    var testedCol = col + colChange
    var distance = 0
    while (positionExists(testedRow, testedCol, seats) && (range == -1 || distance < range)) {
        if (hasSeat(testedRow, testedCol, seats)) {
            return seatIsOccupied(testedRow, testedCol, seats)
        }
        distance++
        testedRow += rowChange
        testedCol += colChange
    }
    return false
}

fun positionExists(row: Int, col: Int, seats: List<String>): Boolean {
    return row >= 0 && col >= 0 && row < seats.size && col < seats[row].length
}

fun hasSeat(row: Int, col: Int, seats: List<String>): Boolean {
    if (positionExists(row, col, seats)) {
        val seatValue = seats[row][col]
        if (seatValue == '#' || seatValue == 'L') {
            return true
        }
    }
    return false
}

fun seatIsOccupied(row: Int, col: Int, seats: List<String>): Boolean {
    if (!hasSeat(row, col, seats)) {
        throw Error("Position doesn't have a seat!")
    }
    return when (seats[row][col]) {
        '#' -> true
        'L' -> false
        else -> false
    }
}

fun seatWillBeOccupiedPart2(row: Int, col: Int, seats: List<String>): Boolean {
    val seenOccupiedSeats = countOccupiedNeighbors(row, col, seats, -1)
    when (seats[row][col]) {
        'L' -> if (seenOccupiedSeats == 0) {
            return true
        }
        '#' -> if (seenOccupiedSeats >= 5) {
            return false
        }
    }
    return (seats[row][col] == '#')
}