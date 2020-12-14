package day14

import java.io.File
import java.math.BigInteger

fun main() {
    println("Day 14 - Start!")
    val input = loadData("src/main/resources/day14.txt")
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}

enum class OperationType {
    MASK,
    MEM
}

data class Instruction(val operationType: OperationType, val mask: String = "", val memAdd: Long = 0, val memVal: Long = 0)

fun loadData(fileName: String): List<Instruction> {
    val instructions = mutableListOf<Instruction>()
    File(fileName).forEachLine {
        if (it.startsWith("mask = ")) {
            instructions.add(Instruction(OperationType.MASK, mask = it.substringAfter("mask = ")))
        } else if (it.startsWith("mem[")) {
            val memAdd = it.substringAfter("[").substringBefore("]").toLong()
            val memVal = it.substringAfter(" = ").toLong()
            instructions.add(Instruction(OperationType.MEM, memAdd = memAdd, memVal = memVal))
        }
    }
    return instructions
}

fun part1(input: List<Instruction>): Long {
    var mask = input[0].mask
    val mem = mutableMapOf<Long, Long>()
    for (instruction in input) {
        when (instruction.operationType) {
            OperationType.MASK -> mask = instruction.mask
            OperationType.MEM -> mem[instruction.memAdd] = maskMemVal(mask, instruction.memVal)
        }
    }
    return mem.map { it.value }.sum()
}


fun maskMemVal(mask: String, memVal: Long): Long {
    val bits = (memVal.toString(2)).padStart(36, '0')
    var resultBits = bits
    for (i in mask.indices) {
        if (mask[i] != 'X') {
            resultBits = resultBits.replaceRange(i,i+1, mask[i].toString())
        }
    }
    val result = BigInteger(resultBits, 2)
    println("value:  $bits  (decimal $memVal)")
    println("mask:   $mask")
    println("result: $resultBits  (decimal $result)")
    println()
    return result.toLong()
}

fun part2(input: List<Instruction>): Long {
    var mask = input[0].mask
    val mem = mutableMapOf<Long, Long>()
    for (instruction in input) {
        when (instruction.operationType) {
            OperationType.MASK -> mask = instruction.mask
            OperationType.MEM -> {
                val maskedMemAdds = maskMemAdd(mask, instruction.memAdd)
                for (memAdd in maskedMemAdds) {
                    mem[memAdd] = instruction.memVal
                }
            }
        }
    }
    return mem.map { it.value }.sum()
}

fun maskMemAdd(mask: String, memAdd: Long): List<Long> {
    val bits = (memAdd.toString(2)).padStart(36, '0')
    var resultBitsList = mutableListOf(bits)
    for (i in mask.indices) {
        var nextList = mutableListOf<String>()
        for (resultBits in resultBitsList) {
            when (mask[i]) {
                '1' -> nextList.add(resultBits.replaceRange(i,i+1, "1"))
                '0' -> nextList.add(resultBits)
                else -> {
                    nextList.add(resultBits.replaceRange(i,i+1, "1"))
                    nextList.add(resultBits.replaceRange(i,i+1, "0"))
                }
            }
        }
        resultBitsList = nextList
    }

    println("value:  $bits  (decimal $memAdd)")
    println("mask:   $mask")
    var resultList = mutableListOf<Long>()
    for (resultBits in resultBitsList) {
        var result = BigInteger(resultBits, 2).toLong()
        resultList.add(result)
        println("result: $resultBits  (decimal $result)")
    }
    println()
    return resultList
}