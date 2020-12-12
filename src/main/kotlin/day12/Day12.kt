package day12

import parse
import kotlin.math.abs

data class Action(val command: Char, val argument: Int)

fun main() {
    val input = parse("src/main/kotlin/day12/input") { input ->
        input.map {
            Action(it[0], it.substring(1).toInt())
        }
    }

    println(part1(input))
    println(part2(input))
}

fun part1(actions: List<Action>): Int {
    var x = 0
    var y = 0
    var direction = Direction.E

    actions.forEach {
        when (it.command) {
            'N' -> y -= it.argument
            'S' -> y += it.argument
            'E' -> x += it.argument
            'W' -> x -= it.argument

            'L' -> {
                when (it.argument) {
                    90 -> {
                        direction = when (direction) {
                            Direction.N -> Direction.W
                            Direction.E -> Direction.N
                            Direction.S -> Direction.E
                            Direction.W -> Direction.S
                        }
                    }
                    180 -> {
                        direction = when (direction) {
                            Direction.N -> Direction.S
                            Direction.E -> Direction.W
                            Direction.S -> Direction.N
                            Direction.W -> Direction.E
                        }
                    }

                    270 -> {
                        direction = when (direction) {
                            Direction.N -> Direction.E
                            Direction.E -> Direction.S
                            Direction.S -> Direction.W
                            Direction.W -> Direction.N
                        }
                    }
                    else -> error("Illegal argument: ${it.argument}")
                }
            }

            'R' -> {
                when (it.argument) {
                    90 -> {
                        direction = when (direction) {
                            Direction.N -> Direction.E
                            Direction.E -> Direction.S
                            Direction.S -> Direction.W
                            Direction.W -> Direction.N
                        }
                    }
                    180 -> {
                        direction = when (direction) {
                            Direction.N -> Direction.S
                            Direction.E -> Direction.W
                            Direction.S -> Direction.N
                            Direction.W -> Direction.E
                        }
                    }

                    270 -> {
                        direction = when (direction) {
                            Direction.N -> Direction.W
                            Direction.E -> Direction.N
                            Direction.S -> Direction.E
                            Direction.W -> Direction.S
                        }
                    }
                    else -> error("Illegal argument: ${it.argument} for command ${it.command}")
                }
            }
            'F' -> {
                when (direction) {
                    Direction.N -> y -= it.argument
                    Direction.E -> x += it.argument
                    Direction.S -> y += it.argument
                    Direction.W -> x -= it.argument
                }
            }
        }
    }
    return abs(x) + abs(y)
}

fun part2(actions: List<Action>) : Int {
    var sx = 0
    var sy = 0

    var wx = 10
    var wy = 1

    actions.forEach {
        when (it.command) {
            'N' -> wy += it.argument
            'S' -> wy -= it.argument
            'E' -> wx += it.argument
            'W' -> wx -= it.argument

            'L' -> {
                when (it.argument) {
                    90 -> {
                        val tmpWx = wx
                        wx = -wy
                        wy = tmpWx
                    }
                    180 -> {
                        wx = -wx
                        wy = -wy
                    }

                    270 -> {
                        val tmpWx = wx
                        wx = wy
                        wy = -tmpWx
                    }
                    else -> error("Illegal argument: ${it.argument}")
                }
            }

            'R' -> {
                when (it.argument) {
                    90 -> {
                        val tmpWx = wx
                        wx = wy
                        wy = -tmpWx
                    }
                    180 -> {
                        wx = -wx
                        wy = -wy
                    }

                    270 -> {
                        val tmpWx = wx
                        wx = -wy
                        wy = tmpWx
                    }
                    else -> error("Illegal argument: ${it.argument} for command ${it.command}")
                }
            }
            'F' -> {
                sx += (wx*it.argument)
                sy += (wy*it.argument)
            }
        }
    }
    return abs(sx) + abs(sy)
}

enum class Direction {
    N, E, S, W
}

