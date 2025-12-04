package io.github.endzeitbegins.aoc2025.day04

import io.github.endzeitbegins.aoc2025.checkSolution
import io.github.endzeitbegins.aoc2025.readInput

private fun part1(input: String): Int {
    val paperRollPositions = determinePaperRollPositions(input)

    return determineAccessiblePaperRollPositions(paperRollPositions).size
}

private fun part2(input: String): Int {
    val initialPaperRollPositions = determinePaperRollPositions(input)
    val paperRollPositions = initialPaperRollPositions.toMutableSet()

    do {
        val paperRollsToRemove = determineAccessiblePaperRollPositions(paperRollPositions)

        paperRollPositions -= paperRollsToRemove
    } while (paperRollsToRemove.isNotEmpty())

    return initialPaperRollPositions.size - paperRollPositions.size
}

fun main() {
    val testInput = readInput("day04/test-input.txt")
    val input = readInput("day04/input.txt")

    // part 1
    checkSolution(part1(testInput), 13)
    println(part1(input))

    // part 2
    checkSolution(part2(testInput), 43)
    println(part2(input))
}

private typealias Position = Pair<Int, Int>

private fun determinePaperRollPositions(input: String): Set<Position> = buildSet {
    for ((y, line) in input.lines().withIndex()) {
        for ((x, symbol) in line.withIndex()) {
            if (symbol == '@') {
                this += x to y
            }
        }
    }
}

private fun determineAccessiblePaperRollPositions(paperRollPositions: Set<Position>): List<Position> =
    paperRollPositions.filter { position ->
        position.adjacentPositions
            .count { (x, y) ->
                x to y in paperRollPositions
            } < 4
    }

private val Position.adjacentPositions: Set<Position>
    get() {
        val (x, y) = this

        val above = y - 1
        val below = y + 1
        val left = x - 1
        val right = x + 1

        return setOf(
            left to above,
            x to above,
            right to above,
            left to y,
            right to y,
            left to below,
            x to below,
            right to below,
        )
    }