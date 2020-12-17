package day16

import java.io.File

fun main() {
    println("Day 16 - Start!")
    val input = loadData("src/main/resources/day16.txt")
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}

data class Input(val rules: Map<String, List<IntRange>>, val myTicket: List<Int>, val nearbyTickets: List<List<Int>>)

fun loadData(fileName: String): Input {
    val rules = mutableMapOf<String, List<IntRange>>()
    val myTicket = mutableListOf<Int>()
    val nearbyTickets = mutableListOf<List<Int>>()

    val lines = File(fileName).readLines()
    var i = 0
    while (lines[i] != "") {
        val name = lines[i].substringBefore(": ")
        val ranges = lines[i].substringAfter(": ").split(" or ").map { parseRange(it) }
        rules[name] = ranges
        i++
    }
    i += 2 // Skip to your ticket
    myTicket.addAll(readTicket(lines[i]))
    i += 3 // Skip to nearby tickets
    while (i < lines.size) {
        nearbyTickets.add(readTicket(lines[i]))
        i++
    }

    return Input(rules, myTicket, nearbyTickets)
}

fun parseRange(string: String): IntRange {
    val tokens = string.split("-")
    return IntRange(tokens[0].toInt(), tokens[1].toInt())
}

fun readTicket(string: String): List<Int> {
    return string.split(",").map { it.toInt() }
}

fun part1(input: Input): Int {
    val intRanges = input.rules.map { it.value }.flatten()
    return findAllInvalidValues(input.nearbyTickets, intRanges).sum()
}

fun findInvalidValues(ticket: List<Int>, intRanges: List<IntRange>): List<Int> {
    val invalids = mutableListOf<Int>()
    for (value in ticket) {
        if (intRanges.none { it.contains(value) }) {
            invalids.add(value)
        }
    }
    return invalids
}

fun findAllInvalidValues(tickets: List<List<Int>>, intRanges: List<IntRange>): List<Int> {
    val invalids = mutableListOf<Int>()
    for (ticket in tickets) {
        val invalidsForTicket = findInvalidValues(ticket, intRanges)
        invalids.addAll(invalidsForTicket)
    }
    return invalids
}

fun part2(input: Input): Int {
    val intRanges = input.rules.map { it.value }.flatten()
    val validTickets = findValidTickets(input.nearbyTickets, intRanges)
    val decodedFields = decodeFields(validTickets, input.rules)
    val departureFieldIndexes = decodedFields.filter { it.key.startsWith("departure") }.map { it.value }
    val myTicketDepartureValues =
        input.myTicket.filter { input.myTicket.indexOf(it) in departureFieldIndexes }
    println("All tickets: " + input.nearbyTickets.count())
    println("Validated tickets: " + validTickets.count())
    println("Invalids: " +findInvalidTickets(decodedFields, input.nearbyTickets, input.rules).count())
    return myTicketDepartureValues.reduce { acc, i -> acc * i }
}

fun findInvalidTickets(
    dict: Map<String, Int>,
    tickets: List<List<Int>>,
    rules: Map<String, List<IntRange>>
): List<List<Int>> {
    return tickets.filter { !ticketIsValid(dict, it, rules) }
}

fun ticketIsValid(dict: Map<String, Int>, ticket: List<Int>, rules: Map<String, List<IntRange>>): Boolean {
    for (entry in dict) {
        val test = rules[entry.key]
        val list = mutableListOf<String>()
        if (test != null && test.contains(ticket[entry.value])) {
            return false
        }
        list.add(entry.key)
    }
    return true
}

fun findValidTickets(tickets: List<List<Int>>, intRanges: List<IntRange>): List<List<Int>> {
    val validTickets = mutableListOf<List<Int>>()
    for (ticket in tickets) {
        if (findInvalidValues(ticket, intRanges).isEmpty()) {
            validTickets.add(ticket)
        }
    }
    return validTickets
}

fun getIndexedValues(tickets: List<List<Int>>): List<List<Int>> {
    val indexedValues = mutableListOf<List<Int>>()
    for (i in tickets[0].indices) {
        val valueList = mutableListOf<Int>()
        for (ticket in tickets) {
            valueList.add(ticket[i])
        }
        indexedValues.add(valueList)
    }
    return indexedValues
}

fun decodeFields(tickets: List<List<Int>>, rules: Map<String, List<IntRange>>): Map<String, Int> {
    val encodedValuesPerIndex = getIndexedValues(tickets).toMutableList()
    val decodedValues = mutableListOf<List<Int>>()
    val rulesToDecode = rules.toMutableMap()
    val decodedFields = mutableMapOf<String, Int>()

    while (encodedValuesPerIndex.size != decodedValues.size) {

        encodedValuesPerIndex.forEach { encodedValue ->
            if (encodedValue in decodedValues) return@forEach

            val matchedRules = mutableSetOf<String>()
            rulesToDecode.forEach { rule ->
                if (encodedValue.all { value -> rule.value.any { range -> range.contains(value) } }) {
                    matchedRules.add(rule.key)
                }
            }
            if (matchedRules.size == 1) {
                rulesToDecode.remove(matchedRules.first())
                decodedValues.add(encodedValue)
                decodedFields[matchedRules.first()] = encodedValuesPerIndex.indexOf(encodedValue)
            }
        }
    }

    return decodedFields
}