package day2

import java.io.File

fun main(args: Array<String>) {
    println("Day 2 - Start!")
    val passwords = loadData("src/main/resources/day2.txt")

    val part1validPasswords = getValidPasswords(passwords, ::part1validatePassword)
    println("Part 1 - Amount of valid passwords: ${part1validPasswords.count()}")

    val part2validPasswords = getValidPasswords(passwords, ::part2validatePassword)
    println("Part 2 - Amount of valid passwords: ${part2validPasswords.count()}")
}

data class Password(val password: String, val text: String, val firstNum: Int, val secondNum: Int)

fun loadData(fileName: String) : MutableList<Password> {
    val list = mutableListOf<Password>()
    File(fileName).forEachLine {
        list.add(parsePassword(it))
    }
    return list
}

fun parsePassword(text: String): Password {
    val destructuredRegex = "([0-9]+)-([0-9]+) ([a-z]): (.*)".toRegex()
    return destructuredRegex.matchEntire(text)
        ?.destructured
        ?.let { (firstNum, secondNum, text, password) ->
            Password(password, text, firstNum.toInt(), secondNum.toInt())
        }
        ?: throw IllegalArgumentException("Invalid input '$text'")
}

fun getValidPasswords(passwords: MutableList<Password>, validator: (Password) -> Boolean) : MutableList<Password> {
    val validPasswords = mutableListOf<Password>()
    for (password in passwords) {
        if (validator(password)) {
            validPasswords.add(password)
        }
    }
    return validPasswords
}

fun part1validatePassword(pw: Password) : Boolean {
    val req = pw.text
    val minAmount = pw.firstNum
    val maxAmount = pw.secondNum

    val regex = "^[^$req]*($req[^$req]*){$minAmount,$maxAmount}${'$'}".toRegex()
    return pw.password.contains(regex)
}

fun part2validatePassword(pw: Password) : Boolean {
    val req = pw.text
    val firstPos = pw.firstNum
    val secondPos = pw.secondNum

    val regexFirst = "^.{${firstPos-1}}[$req]".toRegex()
    val firstFound = pw.password.contains(regexFirst)

    val regexSecond = "^.{${secondPos-1}}[$req]".toRegex()
    val secondFound = pw.password.contains(regexSecond)

    return (firstFound.xor(secondFound))
}