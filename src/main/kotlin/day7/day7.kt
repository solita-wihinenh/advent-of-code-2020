package day7

import java.io.File

fun main() {
    println("Day 7 - Start!")
    val bags = loadData("src/main/resources/day7.txt")

    println("Part 1: ${part1(bags, "shiny gold")}")

    println("Part 2: ${part2(bags, "shiny gold")}")
}

data class Content(val bag: Bag, val amount: Int) {
    override fun toString(): String = bag.color
}
data class Bag(val color: String, val contents: MutableList<Content> = mutableListOf(), var isContentOf: MutableList<Bag> = mutableListOf()) {
    override fun hashCode(): Int = this.color.hashCode()
}

fun loadData(fileName: String): List<Bag> {
    val bags = mutableListOf<Bag>()
    File(fileName).forEachLine {
        var color = it.substringBefore(" bag")
        var parentBag = bags.find{ bag -> bag.color == color}
        if (parentBag == null) {
            parentBag = Bag(color)
            bags.add(parentBag)
        }
        var childTokens = it.substringAfter(" contain ").substringBefore(".").split(", ")
        if (childTokens.isNotEmpty() && childTokens[0] != "no other bags") {
            for (childToken in childTokens) {
                val childAmount = childToken.substringBefore(" ").toInt()
                val childColor = childToken.substringAfter(" ").substringBefore(" bag")
                var childBag = bags.find{ bag -> bag.color == childColor}
                if (childBag == null) {
                    childBag = Bag(childColor)
                    bags.add(childBag)
                }
                parentBag.contents.add(Content(childBag, childAmount))
                childBag.isContentOf.add(parentBag)
            }
        }
        bags.add((parentBag))
    }
    return bags
}

fun part1(bags: List<Bag>, color: String): Int {
    val bag = findBag(bags, color)
    return getBagParents(bag).count()
}

fun findBag(bags: List<Bag>, color: String) : Bag {
    return bags.find { bag -> bag.color == color } ?: throw Error("No bag with given color")
}

fun getBagParents(bag: Bag) : Set<Bag> {
    var parents = mutableSetOf<Bag>()
    for (parent in bag.isContentOf) {
        parents.add(parent)
        parents.addAll(getBagParents(parent))
    }
    return parents
}

fun part2(bags: List<Bag>, color: String): Int {
    val bag = findBag(bags, color)
    return getBagChildren(bag);
}

fun getBagChildren(bag: Bag) : Int {
    var children = 0
    for (child in bag.contents) {
        children += child.amount
        children += getBagChildren(child.bag) * child.amount
    }
    return children
}