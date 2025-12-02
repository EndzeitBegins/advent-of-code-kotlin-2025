package io.github.endzeitbegins.aoc2025.day01

import io.github.endzeitbegins.aoc2025.checkSolution
import io.github.endzeitbegins.aoc2025.readInput

private val dialStart = 50
private val dialSize = 100

private fun part1(input: String): Int =
    input.lineSequence()
        .runningFold(dialStart) { dial, rotation ->
            val direction = rotation[0]
            val steps = rotation.drop(1).toInt()

            var adjustedDial = if (direction == 'L') dial - steps else dial + steps
            while (adjustedDial < 0) adjustedDial += dialSize
            adjustedDial % dialSize
        }
        .count { dial -> dial == 0 }

private fun part2(input: String): Int {
    var dial = dialStart
    var clicks = 0

    for (rotation in input.lines()) {
        val direction = rotation[0]
        val steps = rotation.drop(1).toInt()

        val additionalRounds = steps / dialSize
        clicks += additionalRounds

        val adjustedSteps = steps % dialSize
        val adjustedDial = if (direction == 'L') dial - adjustedSteps else dial + adjustedSteps
        if ((adjustedDial <= 0 || adjustedDial >= dialSize) && dial != 0)
            clicks += 1

        dial = (adjustedDial + dialSize) % dialSize
    }

    return clicks
}

fun main() {
    val testInput = readInput("day01/test-input.txt")
    val input = readInput("day01/input.txt")

    // part 1
    checkSolution(part1(testInput), 3)
    println(part1(input))

    // part 2
    checkSolution(part2(testInput), 6)
    println(part2(input))
}