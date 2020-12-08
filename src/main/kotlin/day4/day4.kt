package day4

import java.io.File

val part1fieldRegexes = listOf<Pair<String, Regex>>(
    Pair("byr", ".*".toRegex()),
    Pair("iyr", ".*".toRegex()),
    Pair("eyr", ".*".toRegex()),
    Pair("hgt", ".*".toRegex()),
    Pair("hcl", ".*".toRegex()),
    Pair("ecl", ".*".toRegex()),
    Pair("pid", ".*".toRegex()),
)

val part2fieldRegexes = listOf<Pair<String, Regex>>(
    Pair("byr", "^((19)[2-9][0-9]|(200)[0-2])\$".toRegex()),
    Pair("iyr", "^(20)(1[0-9]|20)\$".toRegex()),
    Pair("eyr", "^(20)(2[0-9]|30)\$".toRegex()),
    Pair("hgt", "^(1(([5-8][0-9])|(9[0-3]))(cm))|(((59)|(6[0-9])|(7[0-6]))(in))\$".toRegex()),
    Pair("hcl", "^#[0-9a-f]{6}\$".toRegex()),
    Pair("ecl", "^(amb|blu|brn|gry|grn|hzl|oth)\$".toRegex()),
    Pair("pid", "^[0-9]{9}\$".toRegex()),
)

fun main() {
    println("Day 4 - Start!")
    val passports = loadData("src/main/resources/day4.txt")

    println("Part 1: ${countValidPassports(passports, part1fieldRegexes)}")
    println("Part 2: ${countValidPassports(passports, part2fieldRegexes)}")
}

fun loadData(fileName: String): List<Map<String, String>> {
    val list = mutableListOf<Map<String, String>>()
    var passport = mutableMapOf<String, String>();
    File(fileName).forEachLine {
        if (it == "" && passport.isNotEmpty()) {
            list.add(passport)
            passport = mutableMapOf<String, String>();
        } else {
            val linePairs = parseTextIntoKeyValuePairs(it)
            linePairs.forEach{ pair -> passport[pair.first] = pair.second}
        }
    }
    list.add(passport)
    return list
}

fun parseTextIntoKeyValuePairs(text: String): List<Pair<String, String>> {
    val keyValuePairs = mutableListOf<Pair<String, String>>()
    val keyValueTokens = text.split(" ")
    for (keyValueToken in keyValueTokens) {
        val tokens = keyValueToken.split(":")
        if (tokens.count() != 2) {
            throw Error("Could not parse text into key-value token");
        }
        keyValuePairs.add(Pair(tokens[0], tokens[1]))
    }
    return keyValuePairs
}

fun countValidPassports(passports: List<Map<String, String>>, fieldRegex: List<Pair<String, Regex>>): Int {
    var validPassports = 0;
    for (passport in passports) {
        var passportIsValid = true
        for (fieldRegex in fieldRegex) {
            if (!passport.containsKey(fieldRegex.first) || !passport.getValue(fieldRegex.first)
                    .matches(fieldRegex.second)
            ) {
                passportIsValid = false
                break;
            }
        }
        if (passportIsValid) {
            validPassports++
        }
    }
    return validPassports
}