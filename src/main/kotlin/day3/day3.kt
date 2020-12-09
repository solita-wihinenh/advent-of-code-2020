package day3

import java.io.File

fun main() {
    println("Day 3 - Start!")
    val map = loadData("src/main/resources/day3.txt")

    println("Part 1: ${calculateTreesHit(map, 0, 0, 3, 1)}")

    val slopes = listOf<Pair<Int, Int>>(Pair(1, 1), Pair(3, 1), Pair(5, 1), Pair(7, 1), Pair(1, 2))
    val treesInSlopes = slopes.map { slope -> calculateTreesHit(map, 0, 0, slope.first, slope.second) }
    println("Part 2: ${treesInSlopes.reduce { acc, i -> acc * i }}")
}

fun loadData(fileName: String): List<String> {
    return File(fileName).readLines()
}

fun calculateTreesHit(map: List<String>, initialX: Int, initialY: Int, xSpeed: Int, ySpeed: Int): Int {
    var treesHit = 0
    var xPos = initialX
    for (yPos in initialY until map.size step ySpeed) {
        if (xPos >= map[yPos].count()) {
            xPos -= map[yPos].count()
        }
        if (map[yPos][xPos] == '#') {
            treesHit++
        }
        xPos += xSpeed
    }
    return treesHit
}