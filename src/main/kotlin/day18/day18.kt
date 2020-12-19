package day18

import java.io.File

fun main() {
    println("Day 18 - Start!")
    val input = loadData("src/main/resources/day18.txt")
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}

fun loadData(fileName: String): List<String> {
    return File(fileName).readLines().map { "($it)" }
}

fun part1(expressions: List<String>): Long {
    return expressions.map{evaluate(it)}.sum()
}

fun evaluate(expression: String, precedences: List<String> = listOf()): Long {
    var result = expression
    val regex = "\\([^()]+\\)".toRegex()
    var match : MatchResult? = regex.find(expression)
    while (match != null) {
        var evaluated = match.value.substring(1,match.value.length-1)
        if (precedences.isEmpty()) {
            evaluated = solveExpression(evaluated, precedences)
        } else {
            precedences.forEach{
                evaluated = solveExpression(evaluated, listOf(it))
            }
        }

        result = result.replace(match.value, evaluated)
        match = regex.find(result)
    }
    return result.toLong()
}

fun part2(expressions: List<String>): Long {
    val results = expressions.map{evaluate(it, listOf("+", "*"))}
    return results.sum()
}

fun evaluateNumberExpression(num1: Long, operator: String, num2: Long): Long {
    var result = num1
    when (operator) {
        "*" -> result *= num2
        "+" -> result += num2
        else -> throw Error("Invalid operator $operator")
    }
    return result
}

fun solveExpression(expression: String, operators: List<String>): String {
    var result = expression
    var usedOperators = if (operators.isEmpty()) listOf("*","+") else operators
    val regex = "(^|[ (])\\d+ [${usedOperators.joinToString(separator = "")}] \\d+(\$|[ )])".toRegex()
    var match : MatchResult? = regex.find(expression)
    while (match != null) {
        val matchedPair = match.value.substring(if (match.value[0] in listOf(' ', '(')) 1 else 0, match.value.length - if (match.value[match.value.length - 1] in listOf(' ', ')')) 1 else 0)
        val tokens = matchedPair.split(" ")
        val evaluated = evaluateNumberExpression(tokens[0].toLong(), tokens[1], tokens[2].toLong())
        result = result.replaceFirst(matchedPair, evaluated.toString())
        match = regex.find(result)
    }
    return result
}


