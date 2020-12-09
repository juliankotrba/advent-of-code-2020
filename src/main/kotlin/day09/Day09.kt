package day09

import parse

fun main() {


    val input = parse("src/main/kotlin/day09/input") { line ->
        line.map { s -> s.toLong() }
    }

    val part1Solution = part1(25, input)
    println(part1(25, input))
    println(part2(input, part1Solution))
}

fun part1(step: Int, input: List<Long>): Long {
    var index = step


    while (index < input.size) {

        val curr = input[index]
        val prev = input.subList(index - step, index).toSet().filter { it < curr }

        if (!prev.any { prev.contains(curr - it) }) {
            break
        }
        index++
    }
    return input[index]
}

fun part2(input: List<Long>, numberToFind: Long): Long {
    var sublist = emptyList<Long>()
    for ((i, n) in input.withIndex()) {
        if (n >= numberToFind) {
            continue
        }

        var sum = n
        var tmpIndex = i
        while (sum < numberToFind) {
            tmpIndex++
            sum += input[tmpIndex]
        }

        if (sum == numberToFind) {
            sublist = input.subList(i, tmpIndex + 1)
            break
        }
    }

    return sublist.min()!! + sublist.max()!!
}