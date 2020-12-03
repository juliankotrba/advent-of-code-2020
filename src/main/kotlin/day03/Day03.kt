package day03

import parse

fun main() {
    val input = parse("src/main/kotlin/day03/input") { it }

    println(part1(input))
    println(part2(input))
}

fun part1(input: List<String>): Long = input.treesForRoute(3, 1)

fun part2(input: List<String>): Long = input.treesForRoute(1, 1) *
        input.treesForRoute(3, 1) *
        input.treesForRoute(5, 1) *
        input.treesForRoute(7, 1) *
        input.treesForRoute(1, 2)

fun List<String>.treesForRoute(xd: Int, yd: Int): Long {
    var treeCount = 0L
    var x = 0
    var y = 0
    while (y < this.size) {
        if (this[y][x % this[0].length] == '#') {
            treeCount++
        }
        x += xd; y += yd
    }
    return treeCount
}