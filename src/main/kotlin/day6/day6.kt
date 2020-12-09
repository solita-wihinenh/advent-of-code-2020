package day6

import java.io.File

fun main() {
    println("Day 6 - Start!")
    val answersByGroup = loadData("src/main/resources/day6.txt")

    println("Part 1: ${part1(answersByGroup)}")

    println("Part 2: ${part2(answersByGroup)}")
}

fun loadData(fileName: String): List<List<String>> {
    val answersByGroup = mutableListOf<MutableList<String>>()
    var answersByPerson = mutableListOf<String>()
    File(fileName).forEachLine {
        if (it == "" && answersByPerson.isNotEmpty()) {
            answersByGroup.add(answersByPerson)
            answersByPerson = mutableListOf<String>()
        } else {
            answersByPerson.add(it)
        }
    }
    answersByGroup.add(answersByPerson)
    return answersByGroup
}


fun part1(answersByGroup: List<List<String>>): Int {
    val uniqueCharsByGroup = findUniqueCharsByGroup(answersByGroup)
    val uniqueCharAmountByGroup = uniqueCharsByGroup.map { uniqueChars -> uniqueChars.count() }
    return uniqueCharAmountByGroup.sum()
}

fun part2(answersByGroup: List<List<String>>): Int {
    val commonCharsByGroup = findCommonCharsByGroup(answersByGroup)
    val commonCharAmountByGroup = commonCharsByGroup.map { commonChars -> commonChars.count() }
    return commonCharAmountByGroup.sum()
}

fun findUniqueCharsByGroup(answersByGroup: List<List<String>>): List<Set<Char>> {
    val uniqueCharsByGroup = mutableListOf(mutableSetOf<Char>())
    for (answersByPerson in answersByGroup) {
        val uniqueChars = mutableSetOf<Char>()
        for (answer in answersByPerson) {
            for (char in answer) {
                uniqueChars.add(char)
            }
        }
        uniqueCharsByGroup.add(uniqueChars)
    }
    return uniqueCharsByGroup
}

fun findCommonCharsByGroup(answersByGroup: List<List<String>>): List<Set<Char>> {
    val commonCharsByGroup = mutableListOf(setOf<Char>())
    for (answersByPerson in answersByGroup) {
        var commonChars = setOf<Char>()
        for (answer in answersByPerson) {
            commonChars = if (commonChars.isEmpty()) {
                answer.toSet()
            } else {
                commonChars.intersect(answer.toList())
            }
            if (commonChars.isEmpty()) {
                break
            }
        }
        commonCharsByGroup.add(commonChars)
    }
    return commonCharsByGroup
}