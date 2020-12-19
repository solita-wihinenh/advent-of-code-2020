package day19

import java.io.File


fun main() {
    println("Day 19 - Start!")
    val input = loadData("src/main/resources/day19.txt")
    val input2 = loadData("src/main/resources/day19part2.txt")
    println("Part 1: ${part1(input)}") // 272
    println("Part 2: ${part1(input2)}") // 374
}

enum class RuleType {
    STRING,
    EXPRESSION
}

data class Rule(
    val index: Int,
    val type: RuleType,
    val expressions: List<List<Int>> = listOf(),
    val string: String = ""
)

enum class ReadMode {
    RULE,
    MESSAGE
}

data class Input(val rules: Map<Int, Rule>, val messages: List<String>)

fun loadData(fileName: String): Input {
    val rules = mutableMapOf<Int, Rule>()
    val messages = mutableListOf<String>()
    var mode = ReadMode.RULE
    File(fileName).forEachLine {
        if (it == "") {
            mode = ReadMode.MESSAGE
            return@forEachLine
        }
        when (mode) {
            ReadMode.RULE -> {
                val rule = parseRule(it)
                rules[rule.index] = rule
            }
            ReadMode.MESSAGE -> messages.add(it)
        }
    }
    return Input(rules, messages)
}

fun parseRule(string: String): Rule {
    val index = string.substringBefore(": ").toInt()
    return if (string.contains("\"")) {
        Rule(index, RuleType.STRING, string = string.split("\"")[1])
    } else {
        val tokens = string.substringAfter(": ").split(" | ")
        val expressions = mutableListOf<List<Int>>()
        tokens.forEach { token -> expressions.add(token.split(" ").map { it.toInt() }.toList()) }
        Rule(index, RuleType.EXPRESSION, expressions)
    }
}

fun part1(input: Input): Int {
    val firstRule = input.rules[0] ?: throw Error("No rules found")
    return input.messages.filter { isValid(it, listOf(firstRule), input.rules) }.count()
}

fun isValid(message: String, rulesToMatch: List<Rule>, ruleMap: Map<Int, Rule>): Boolean {
    if (message.isEmpty()) {
        return rulesToMatch.isEmpty()
    } else if (rulesToMatch.isEmpty()) {
        return false
    }
    return rulesToMatch.first().let { rule ->
        if (rule.type == RuleType.STRING) {
            if (message.startsWith(rule.string)) {
                isValid(message.drop(1), rulesToMatch.drop(1), ruleMap)
            } else false
        } else {
            rule.expressions.any { ruleList ->
                isValid(
                    message,
                    ruleList.map { ruleId -> ruleMap[ruleId] ?: throw Error("Rule not found") }
                        .toList() + rulesToMatch.drop(1),
                    ruleMap
                )
            }
        }
    }
}