package day11

import parse

fun main() {
    val input = parse("src/main/kotlin/day11/input") { input ->
        input.map { it.toMutableList() }
    }

    println(part1(input))
    println(part2(input))
}

fun part1(input: List<MutableList<Char>>): Int {
    var copiedState = input.toMutableList().map { it.toMutableList() }

    var changed = true
    while (changed) {
        changed = false
        val nextState = copiedState.toMutableList().map { it.toMutableList() }

        for (y in input.indices) {
            for (x in input[0].indices) {
                if (copiedState[y][x] == 'L') {
                    if (adjacentCountPart1(y, x, copiedState) == 0) {
                        nextState[y][x] = '#'
                        changed = true
                    }
                } else if (copiedState[y][x] == '#') {
                    if (adjacentCountPart1(y, x, copiedState) >= 4) {
                        nextState[y][x] = 'L'
                        changed = true
                    }
                }
            }
        }
        copiedState = nextState
    }

    return countOccupiedChairs(copiedState)
}

fun part2(input: List<MutableList<Char>>): Int {
    var copiedState = input.toMutableList().map { it.toMutableList() }
    var changed = true
    while (changed) {
        changed = false

        val nextState = copiedState.toMutableList().map { it.toMutableList() }
        for (y in input.indices) {
            for (x in input[0].indices) {
                if (copiedState[y][x] == 'L') {
                    if (adjacentCountPart2(y, x, copiedState) == 0) {
                        nextState[y][x] = '#'
                        changed = true
                    }
                } else if (copiedState[y][x] == '#') {
                    if (adjacentCountPart2(y, x, copiedState) >= 5) {
                        nextState[y][x] = 'L'
                        changed = true
                    }
                }
            }
        }
        copiedState = nextState
    }

    return countOccupiedChairs(copiedState)
}

private fun adjacentCountPart1(y: Int, x: Int, input: List<MutableList<Char>>): Int {
    var c = 0

    if (y - 1 >= 0 && input[y - 1][x] == '#') {
        c++
    }

    if (y + 1 < input.size && input[y + 1][x] == '#') {
        c++
    }

    if (x - 1 >= 0 && input[y][x - 1] == '#') {
        c++
    }

    if (x + 1 < input[0].size && input[y][x + 1] == '#') {
        c++
    }

    // diagonal

    if (y - 1 >= 0 && x + 1 < input[0].size && input[y - 1][x + 1] == '#') {
        c++
    }

    if (y - 1 >= 0 && x - 1 >= 0 && input[y - 1][x - 1] == '#') {
        c++
    }

    if (y + 1 < input.size && x + 1 < input[0].size && input[y + 1][x + 1] == '#') {
        c++
    }

    if (y + 1 < input.size && x - 1 >= 0 && input[y + 1][x - 1] == '#') {
        c++
    }

    return c
}

private fun adjacentCountPart2(y: Int, x: Int, input: List<MutableList<Char>>): Int {
    // TODO: Improve by generalizing
    var c = 0

    // go up
    var tmpY = y
    while (tmpY > 0) {
        tmpY -= 1
        if (input[tmpY][x] == 'L') {
            tmpY = 0
        } else if (input[tmpY][x] == '#') {
            c++
            tmpY = 0
        }
    }

    // go down
    tmpY = y
    while (tmpY < input.size - 1) {
        tmpY += 1
        if (input[tmpY][x] == 'L') {
            tmpY = input.size - 1
        } else if (input[tmpY][x] == '#') {
            c++
            tmpY = input.size - 1
        }
    }

    // go left
    var tmpX = x
    while (tmpX > 0) {
        tmpX -= 1
        if (input[y][tmpX] == 'L') {
            tmpX = 0
        } else if (input[y][tmpX] == '#') {
            c++
            tmpX = 0
        }
    }

    // go right
    tmpX = x
    while (tmpX < input[0].size - 1) {
        tmpX += 1
        if (input[y][tmpX] == 'L') {
            tmpX = input[0].size - 1
        } else if (input[y][tmpX] == '#') {
            c++
            tmpX = input[0].size - 1
        }
    }

    // diagonal

    // top left

    tmpX = x
    tmpY = y

    while (tmpY > 0 && tmpX > 0) {
        tmpX -= 1
        tmpY -= 1

        if (input[tmpY][tmpX] == 'L') {
            tmpX = 0
        } else if (input[tmpY][tmpX] == '#') {
            tmpX = 0
            c++
        }
    }

    // top right

    tmpX = x
    tmpY = y

    while (tmpY > 0 && tmpX < input[0].size - 1) {
        tmpX += 1
        tmpY -= 1

        if (input[tmpY][tmpX] == 'L') {
            tmpX = input[0].size - 1
        } else if (input[tmpY][tmpX] == '#') {
            tmpX = input[0].size - 1
            c++
        }
    }

    // bottom left

    tmpX = x
    tmpY = y

    while (tmpY < input.size - 1 && tmpX > 0) {
        tmpX -= 1
        tmpY += 1

        if (input[tmpY][tmpX] == 'L') {
            tmpX = 0
        } else if (input[tmpY][tmpX] == '#') {
            tmpX = 0
            c++
        }
    }

    // bottom right

    tmpX = x
    tmpY = y

    while (tmpY < input.size - 1 && tmpX < input[0].size - 1) {
        tmpX += 1
        tmpY += 1

        if (input[tmpY][tmpX] == 'L') {
            tmpX = input[0].size - 1
        } else if (input[tmpY][tmpX] == '#') {
            tmpX = input[0].size - 1
            c++
        }
    }
    return c
}

private fun countOccupiedChairs(input: List<MutableList<Char>>): Int {
    return input.map { it.count { c -> c == '#' } }.sum()
}


