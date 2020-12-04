package day1

import java.io.File

fun main(args: Array<String>) {
    println("Day 1 - Start!");
    val numbers = loadData("src/main/resources/day1.txt");

    println("Part 1 - 2 numbers, product: " + findProductForSum(numbers,2, 2020));

    println("Part 2 - 3 numbers, product: " + findProductForSum(numbers,3, 2020));
}

fun loadData(fileName: String) : MutableList<Int> {
    val list = mutableListOf<Int>()
    File(fileName).forEachLine {
        list.add(it.toInt())
    }
    return list;
}

fun findProductForSum(numbers: List<Int>, numbersToUse: Int, targetSum: Int, testedNumbers: MutableList<Int> = mutableListOf(), startIndex: Int = 0) : Int {
    for (i in startIndex until numbers.size) {
        testedNumbers.add(numbers[i]);
        val sum = testedNumbers.sum();
        if (sum == targetSum && numbersToUse == testedNumbers.count()) {
            return (testedNumbers.reduce { acc, i -> acc * i })
        } else if (sum < targetSum && testedNumbers.count() < numbersToUse) {
            val result = findProductForSum(numbers, numbersToUse, targetSum, testedNumbers , i + 1);
            if (result != -1) {
                return result;
            }
        }
        testedNumbers.removeLast();
    }
    return -1;
}