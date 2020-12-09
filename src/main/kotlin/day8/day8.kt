package day8

import java.io.File

fun main() {
    println("Day 8 - Start!")
    val instructions = loadData("src/main/resources/day8.txt")
    println("Part 1: ${part1(instructions)}")
    println("Part 2: ${part2(instructions)}")
}

data class Instruction(var operation: String, val argument: Int, val row: Int) {
    fun isJmpNop(): Boolean {
        return this.operation == "jmp" || this.operation == "nop"
    }

    fun swapJmpNop() {
        when (this.operation) {
            "jmp" -> this.operation = "nop"
            "nop" -> this.operation = "jmp"
        }
    }
}

fun loadData(fileName: String): List<Instruction> {
    val instructions = mutableListOf<Instruction>()
    var row = 0
    File(fileName).forEachLine {
        val operation = it.substringBefore(" ")
        val argument = it.substringAfter(" ").replace("+", "").toInt()
        instructions.add(Instruction(operation, argument, row))
        row++
    }
    return instructions
}

enum class ProgramStatus {
    IN_PROGRESS, INFINITE_LOOP, SUCCESS
}

data class ProgramResult(val exitCode: ProgramStatus, val executedInstructions: List<Instruction>, val accumulator: Int)

fun part1(instructions: List<Instruction>): Int {
    return runProgram(instructions).accumulator
}

fun runProgram(instructions: List<Instruction>): ProgramResult {
    var programStatus = ProgramStatus.IN_PROGRESS
    var accumulator = 0
    var row = 0
    val executedInstructions = mutableSetOf<Instruction>()
    while (row < instructions.size) {
        if (executedInstructions.contains(instructions[row])) {
            programStatus = ProgramStatus.INFINITE_LOOP
            break
        }
        executedInstructions.add(instructions[row])
        when (instructions[row].operation) {
            "acc" -> {
                accumulator += instructions[row].argument
                row++
            }
            "jmp" -> row += instructions[row].argument
            "nop" -> row++
        }
    }

    if (programStatus != ProgramStatus.INFINITE_LOOP) {
        programStatus = ProgramStatus.SUCCESS
    }
    return ProgramResult(programStatus, executedInstructions.toList(), accumulator)
}

fun part2(instructions: List<Instruction>): Int {
    val fixedInstructions = fixInstructions(instructions)
    return runProgram(fixedInstructions).accumulator
}

fun fixInstructions(instructions: List<Instruction>): List<Instruction> {
    var fixedInstructions = instructions.toMutableList()
    var alteredStep: Int? = null
    var programResult = runProgram(instructions)
    while (programResult.exitCode == ProgramStatus.INFINITE_LOOP) {
        // If already changed something, change it back
        if (alteredStep != null) {
            fixedInstructions = instructions.toMutableList()
        }
        val corruptionCandidate = programResult.executedInstructions.findLast { instruction ->
            instruction.isJmpNop() && programResult.executedInstructions.indexOf(
                instruction
            ) < alteredStep ?: programResult.executedInstructions.size
        } ?: throw Error("Unfixable")

        alteredStep = programResult.executedInstructions.indexOf(
            corruptionCandidate
        )

        corruptionCandidate.swapJmpNop()
        fixedInstructions[corruptionCandidate.row] = corruptionCandidate

        programResult = runProgram(instructions)
    }
    return fixedInstructions
}