package io.github.endzeitbegins.aoc2025.day03

import io.github.endzeitbegins.aoc2025.checkSolution
import io.github.endzeitbegins.aoc2025.readInput

private fun part1(input: String): Long =
    input
        .lineSequence()
        .sumOf { bank ->
            selectBatteries(bank, batteryCount = 2)
                .determineJoltage(bank)
        }

private fun part2(input: String): Long =
    input
        .lineSequence()
        .sumOf { bank ->
            selectBatteries(bank, batteryCount = 12)
                .determineJoltage(bank)
        }

private fun determineBatteryIndices(bank: String): Map<Int, List<Int>> =
    bank.indices.groupBy { index -> bank[index].digitToInt() }

private fun indexOfLargestBattery(
    batteryIndices: Map<Int, List<Int>>,
    lowerIndexLimit: Int,
    upperIndexLimit: Int,
): Int = (9 downTo 0).firstNotNullOf { joltage ->
    batteryIndices[joltage]?.firstOrNull { index ->
        lowerIndexLimit < index && index < upperIndexLimit
    }
}

private fun selectBatteries(
    bank: String,
    batteryCount: Int,
): List<Int> {
    val batteryIndices = determineBatteryIndices(bank)

    return buildList {
        val indices = this

        repeat(batteryCount) {
            indices += indexOfLargestBattery(
                batteryIndices = batteryIndices,
                lowerIndexLimit = indices.lastOrNull() ?: -1,
                upperIndexLimit = bank.length - batteryCount + indices.size + 1
            )
        }
    }
}

private fun List<Int>.determineJoltage(bank: String): Long =
    joinToString(separator = "") { index -> "${bank[index]}" }
        .toLong()

fun main() {
    val testInput = readInput("day03/test-input.txt")
    val input = readInput("day03/input.txt")

    // part 1
    checkSolution(part1(testInput), 357)
    println(part1(input))

    // part 2
    checkSolution(part2(testInput), 3121910778619)
    println(part2(input))
}