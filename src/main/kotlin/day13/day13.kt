package day13

import java.io.File

fun main() {
    println("Day 11 - Start!")
    val input = loadData("src/main/resources/day13.txt")
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}

data class Input(val earliestDeparture: Int, val schedules: List<String>)

fun loadData(fileName: String): Input {
    val lines = File(fileName).readLines()
    return Input(lines[0].toInt(), lines[1].split(","))
}

fun part1(input: Input): Int {
    val activeSchedules = input.schedules.filter { it != "x" }.map { it.toInt() }
    val earliestSchedule = activeSchedules.minByOrNull { getWaitTime(input.earliestDeparture, it) }
        ?: throw Error("No Schedules")
    return earliestSchedule * getWaitTime(input.earliestDeparture, earliestSchedule)
}

fun getWaitTime(earliestDeparture: Int, schedule: Int): Int {
    return schedule - (earliestDeparture % schedule)
}

fun part2(input: Input): Long {
    val indexedSchedules = input.schedules.withIndex().filter { it.value != "x" }
    val moduli = indexedSchedules.map { it.value.toLong() }
    val remainders = indexedSchedules.map { it.value.toLong() - it.index }
    return chineseRemainder(moduli, remainders)
}

/* returns x where (a * x) % b == 1 */
fun multInv(a: Long, b: Long): Long {
    if (b == 1L) return 1L
    var aa = a
    var bb = b
    var x0 = 0L
    var x1 = 1L
    while (aa > 1L) {
        val q = aa / bb
        var t = bb
        bb = aa % bb
        aa = t
        t = x0
        x0 = x1 - q * x0
        x1 = t
    }
    if (x1 < 0L) x1 += b
    return x1
}

fun chineseRemainder(n: List<Long>, a: List<Long>): Long {
    val prod = n.fold(1L) { acc, i -> acc * i }
    var sum = 0L
    for (i in 0 until n.size) {
        val p = prod / n[i]
        sum += a[i] * multInv(p, n[i]) * p
    }
    return sum % prod
}