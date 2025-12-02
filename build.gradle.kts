plugins {
    kotlin("jvm") version "2.2.21"
}

sourceSets {
    main {
        kotlin.srcDir("src")
    }
}

tasks {
    wrapper {
        gradleVersion = "9.2.1"
    }

    register<Copy>("generateDay") {
        val dayNumber = project.findProperty("day") as? String
            ?: error("Please provide a day number using -Pday=<number>")
        val paddedDayNumber = dayNumber.padStart(2, '0')

        // Source and destination directories
        val sourceDir = file("src/main/kotlin/io/github/endzeitbegins/aoc2025/dayXX")
        val destinationDir = file("src/main/kotlin/io/github/endzeitbegins/aoc2025/day$paddedDayNumber")

        // Create the destination directory if it doesn't exist
        destinationDir.mkdirs()

        // Copy files, renaming "dayXX.kt" to e.g. "day04.kt"
        from(sourceDir) {
            include("*")
            rename { fileName ->
                fileName.replace("dayXX", "day$paddedDayNumber")
            }
            filter { line ->
                line.replace(
                    "dayXX",
                    "day$paddedDayNumber"
                )
            }
        }
        into(destinationDir)
    }
}
