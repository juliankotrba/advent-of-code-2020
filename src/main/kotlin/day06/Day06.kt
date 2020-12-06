package day06

import parse


fun main() {
    val input = parse("src/main/kotlin/day06/input") { input ->
        val result: MutableList<List<String>> = mutableListOf()

        var group: MutableList<String> = mutableListOf()
        input.forEach {
            if (it.isBlank()) {
                result.add(group)
                group = mutableListOf()
            } else {
                group.add(it)
            }
        }
        result.add(group)
        result
    }

    println(part1(input))
    println(part2(input))
}

fun part1(input: List<List<String>>) =
        input.map { group ->
            val charSet = mutableSetOf<Char>()

            group.forEach { person ->
                person.forEach { charSet.add(it) }
            }
            charSet
        }.map { it.size }.sum()


fun part2(input: List<List<String>>) =
        input.map { group ->
            val charCount = mutableMapOf<Char, Int>()

            group.forEach { person ->
                person.forEach {
                    charCount[it] = (charCount[it]?.plus(1)) ?: 1
                }
            }
            charCount.count { it.value == group.size }
        }.sum()