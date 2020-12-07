package day07

import parse


typealias Bag = String
typealias Contained = Pair<Bag, Int>

fun main() {


    val input = parse("src/main/kotlin/day07/input") { input ->
        val bagTree = mutableMapOf<Bag, Set<Contained>>()
        // light red bags contain 1 bright white bag, 2 muted yellow bags
        input.forEach { s ->
            val parent = s.split(" bags contain ")[0] // light red
            val child = s.split(" bags contain ")[1] // 1 bright white bag, 2 muted yellow bags

            val childrenAsList = if (child == "no other bags.") {
                emptySet()
            } else {
                child.dropLast(2).split(", ") // drop "s."
                        .map {
                            // 1 bright white bag,
                            Contained(
                                    it.split(" ")[1] + " " + it.split(" ")[2],
                                    it.split(" ")[0].toInt()
                            )
                        }.toSet()
            }

            bagTree[parent] = childrenAsList
        }
        bagTree
    }

    println(part1(input))
    println(part2(input))
}

fun part1(bagMap: Map<Bag, Set<Contained>>) = bagMap
        .map {
            if (it.key != "shiny gold") {
                traversePart1(it.key, bagMap)
            } else 0
        }.count { it > 0 }

fun traversePart1(currentBag: Bag, bagMap: Map<Bag, Set<Contained>>): Int {
    // TODO: Abort when first shiny golden bag found
    //       Currently this solution counts all the occurrences of shiny golden bags
    if (currentBag == "shiny gold") {
        return 1
    }
    var count = 0
    bagMap.getValue(currentBag).forEach {
        count += traversePart1(it.first, bagMap)
    }
    return count
}

fun part2(bagMap: Map<Bag, Set<Contained>>): Int = traversePart2("shiny gold", bagMap)

fun traversePart2(currentBag: Bag, bagMap: Map<Bag, Set<Contained>>): Int {
    var count = 0
    bagMap.getValue(currentBag).forEach {
        count += it.second + it.second * traversePart2(it.first, bagMap)
    }
    return count
}