package day5

import java.io.File

fun main(args: Array<String>) {
    println("Day 5 - Start!")
    val seatCodes = loadData("src/main/resources/day5.txt")
    val seatIds = seatCodes.map{seatCode -> decodeSeatId(seatCode)}.toList().sorted()

    println("Part 1: ${seatIds.max()}")

    for (i in 0 until seatIds.size) {
        if (seatIds[i] + 2 == seatIds[i+1]) {
            println("Part 2: ${seatIds[i] + 1}")
            break;
        }
    }
}

fun loadData(fileName: String): List<String> {
    val list = mutableListOf<String>()
    File(fileName).forEachLine {
        list.add(it)
    }
    return list
}

fun decodeSeatRow(seatCode: String): Int {
    val rowStr = seatCode.substring(0, 7).replace('F', '0').replace('B', '1');
    return Integer.parseInt(rowStr, 2)
}

fun decodeSeatColumn(seatCode: String): Int {
    val rowStr = seatCode.substring(7).replace('L', '0').replace('R', '1');
    return Integer.parseInt(rowStr, 2)
}

fun decodeSeatId(seatCode: String): Int {
    return decodeSeatRow(seatCode) * 8 + decodeSeatColumn(seatCode)
}

