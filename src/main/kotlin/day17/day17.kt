package day17

import java.io.File

fun main() {
    println("Day 17 - Start!")
    val input = loadData("src/main/resources/day17.txt")
    println("Part 1: ${part1(input, 6)}")
    println("Part 2: ${part2(input, 6)}")
}

data class Point3D(val x: Int, val y: Int, val z: Int)

fun loadData(fileName: String): Set<Point3D> {
    val cube = mutableSetOf<Point3D>()
    val lines = File(fileName).readLines()
    for (y in lines.indices) {
        for (x in lines[y].indices) {
            if (lines[y][x] == '#') {
                cube.add(Point3D(x, y, 0))
            }
        }
    }
    return cube
}

fun part1(input: Set<Point3D>, cycles: Int): Int {
    return simulate3D(input, cycles).size
}

fun simulate3D(cube: Set<Point3D>, cycles: Int): Set<Point3D> {
    var simulatedCube = cube
    repeat(cycles) {
        simulatedCube = simulateOneCycle3D(simulatedCube)
    }
    return simulatedCube
}

fun simulateOneCycle3D(cube: Set<Point3D>): Set<Point3D> {
    val resultCube = mutableSetOf<Point3D>()

    val minX = cube.minByOrNull { it.x }?.x ?: 0
    val minY = cube.minByOrNull { it.y }?.y ?: 0
    val minZ = cube.minByOrNull { it.z }?.z ?: 0
    val maxX = cube.maxByOrNull { it.x }?.x ?: 0
    val maxY = cube.maxByOrNull { it.y }?.y ?: 0
    val maxZ = cube.maxByOrNull { it.z }?.z ?: 0

    for (x in minX - 1..maxX + 1) {
        for (y in minY - 1..maxY + 1) {
            for (z in minZ - 1..maxZ + 1) {
                val point = Point3D(x, y, z)
                val active = cube.contains(point)
                val neighbours = getActiveNeigbourCount3D(point, cube)
                if (active && (neighbours == 2 || neighbours == 3)) {
                    resultCube.add(point)
                } else if (!active && neighbours == 3) {
                    resultCube.add(point)
                }
            }
        }
    }

    return resultCube
}

fun getActiveNeigbourCount3D(point: Point3D, cube: Set<Point3D>): Int {
    val activeNeighbours = mutableSetOf<Point3D>()
    for (x in point.x -1..point.x+1 ) {
        for (y in point.y - 1..point.y + 1) {
            for (z in point.z - 1..point.z + 1) {
                if (x == point.x && y == point.y && z == point.z) continue
                val neighbourPoint = Point3D(x,y,z)
                if (cube.contains(neighbourPoint)) {
                    activeNeighbours.add(neighbourPoint)
                }
            }
        }
    }
    return activeNeighbours.size
}

data class Point4D(val x: Int, val y: Int, val z: Int, val w: Int)

fun part2(input: Set<Point3D>, cycles: Int): Int {
    val cube = mutableSetOf<Point4D>()
    input.forEach { cube.add(Point4D(it.x, it.y, it.z, 0)) }
    return simulate4D(cube, cycles).size
}

fun simulate4D(cube: Set<Point4D>, cycles: Int): Set<Point4D> {
    var simulatedCube = cube
    repeat(cycles) {
        simulatedCube = simulateOneCycle4D(simulatedCube)
    }
    return simulatedCube
}

fun simulateOneCycle4D(cube: Set<Point4D>): Set<Point4D> {
    val resultCube = mutableSetOf<Point4D>()

    val minX = cube.minByOrNull { it.x }?.x ?: 0
    val minY = cube.minByOrNull { it.y }?.y ?: 0
    val minZ = cube.minByOrNull { it.z }?.z ?: 0
    val minW = cube.minByOrNull { it.w }?.w ?: 0
    val maxX = cube.maxByOrNull { it.x }?.x ?: 0
    val maxY = cube.maxByOrNull { it.y }?.y ?: 0
    val maxZ = cube.maxByOrNull { it.z }?.z ?: 0
    val maxW = cube.maxByOrNull { it.w }?.w ?: 0

    for (x in minX - 1..maxX + 1) {
        for (y in minY - 1..maxY + 1) {
            for (z in minZ - 1..maxZ + 1) {
                for (w in minW - 1..maxW + 1) {
                    val point = Point4D(x, y, z, w)
                    val active = cube.contains(point)
                    val neighbours = getActiveNeigbourCount4D(point, cube)
                    if (active && (neighbours == 2 || neighbours == 3)) {
                        resultCube.add(point)
                    } else if (!active && neighbours == 3) {
                        resultCube.add(point)
                    }
                }
            }
        }
    }

    return resultCube
}

fun getActiveNeigbourCount4D(point: Point4D, cube: Set<Point4D>): Int {
    val activeNeighbours = mutableSetOf<Point4D>()
    for (x in point.x -1..point.x+1 ) {
        for (y in point.y - 1..point.y + 1) {
            for (z in point.z - 1..point.z + 1) {
                for (w in point.w - 1..point.w + 1) {
                    if (x == point.x && y == point.y && z == point.z && w == point.w) continue
                    val neighbourPoint = Point4D(x, y, z, w)
                    if (cube.contains(neighbourPoint)) {
                        activeNeighbours.add(neighbourPoint)
                    }
                }
            }
        }
    }
    return activeNeighbours.size
}