package io.github.endzeitbegins.aoc2025.day02

import io.github.endzeitbegins.aoc2025.checkSolution
import io.github.endzeitbegins.aoc2025.readInput
import kotlin.math.abs
import kotlin.math.log10

private fun part1(input: String): Long =
    readRanges(input)
        .sumOf { range ->
            val startID = range.firstNotNullOfOrNull { id ->
                "$id".takeIf { it.length % 2 == 0 }
            }
            if (startID == null)
                return@sumOf 0L

            var sum = 0L

            var number = startID.take(startID.length / 2).toLong()
            var derivedNumber = "$number$number".toLong()

            while (derivedNumber <= range.last) {
                if (derivedNumber in range) {
                    sum += derivedNumber
                }
                number += 1
                derivedNumber = "$number$number".toLong()
            }

            return@sumOf sum
        }


private fun part2(input: String): Long =
    readRanges(input)
        .sumOf { range ->
            val foundIDs = mutableSetOf<Long>()

            var number = 1
            do {
                var repeats = 2
                var derivedNumber = "$number".repeat(repeats).toLong()
                while (derivedNumber <= range.last) {
                    if (derivedNumber in range) {
                        foundIDs += derivedNumber
                    }
                    repeats += 1
                    derivedNumber = "$number".repeat(repeats).toLong()
                }
                number += 1
            } while (repeats > 2)

            return@sumOf foundIDs.sum()
        }

private fun readRanges(input: String): List<LongRange> =
    input.split(",")
        .map { range ->
            val (start, end) = range.split("-")
            start.toLong()..end.toLong()
        }


fun main() {
    val testInput = readInput("day02/test-input.txt")
    val input = readInput("day02/input.txt")

    // part 1
    checkSolution(part1(testInput), 1227775554L)
    println(part1(input))

    // part 2
    checkSolution(part2(testInput), 4174379265L)
    println(part2(input))
}