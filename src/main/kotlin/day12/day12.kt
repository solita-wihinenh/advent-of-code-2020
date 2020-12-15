package day12

import java.awt.Point
import java.io.File
import java.lang.StrictMath.abs

fun main() {
    println("Day 12 - Start!")
    val input = loadData("src/main/resources/day12.txt")
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}

enum class Operation {
    NORTH,
    EAST,
    SOUTH,
    WEST,
    RIGHT,
    LEFT,
    FORWARD,
}

data class Instruction(val operation: Operation, val argument: Int)

fun loadData(fileName: String): List<Instruction> {
    val instructions = mutableListOf<Instruction>()
    File(fileName).forEachLine {
        val operation = when (it[0]) {
            'N' -> Operation.NORTH
            'E' -> Operation.EAST
            'S' -> Operation.SOUTH
            'W' -> Operation.WEST
            'R' -> Operation.RIGHT
            'L' -> Operation.LEFT
            'F' -> Operation.FORWARD
            else -> throw Error("Unknown operation")
        }
        val argument = it.substring(1).toInt()
        instructions.add(Instruction(operation, argument))
    }
    return instructions
}

data class Ship(var x: Int = 0, var y: Int = 0, var heading: Int = 90, var waypoint: Point = Point(10, 1)) {

    fun executeInstruction(instruction: Instruction) {
        when (instruction.operation) {
            Operation.NORTH -> x -= instruction.argument
            Operation.SOUTH -> x += instruction.argument
            Operation.EAST -> y += instruction.argument
            Operation.WEST -> y -= instruction.argument
            Operation.RIGHT -> changeHeading(instruction.argument)
            Operation.LEFT -> changeHeading(-instruction.argument)
            Operation.FORWARD -> sailForward(instruction.argument)
        }
    }

    fun executeWaypointInstruction(instruction: Instruction) {
        when (instruction.operation) {
            Operation.NORTH -> waypoint.y += instruction.argument
            Operation.SOUTH -> waypoint.y -= instruction.argument
            Operation.EAST -> waypoint.x += instruction.argument
            Operation.WEST -> waypoint.x -= instruction.argument
            Operation.RIGHT -> rotateWaypoint(instruction.argument)
            Operation.LEFT -> rotateWaypoint(-instruction.argument)
            Operation.FORWARD -> moveTowardsWaypoint(instruction.argument)
        }
    }

    private fun changeHeading(angle: Int) {
        heading += angle % 360
    }

    private fun sailForward(amount: Int) {
        when (heading) {
            90 -> x += amount
            180 -> y += amount
            270 -> x -= amount
            0 -> y -= amount
        }
    }

    private fun rotateWaypoint(angle: Int) {

        val direction = if (angle > 0) 1 else -1
        repeat(abs(angle / 90)) {
            waypoint = Point(direction * waypoint.y, direction * -waypoint.x)
        }
    }

    private fun moveTowardsWaypoint(amount: Int) {
        x += waypoint.x * amount
        y += waypoint.y * amount
    }
}

fun part1(input: List<Instruction>): Int {
    val ship = Ship()
    input.forEach { ship.executeInstruction(it) }
    return abs(ship.x) + abs(ship.y)
}

fun part2(input: List<Instruction>): Int {
    val ship = Ship()
    input.forEach { ship.executeWaypointInstruction(it) }
    return abs(ship.x) + abs(ship.y)
}