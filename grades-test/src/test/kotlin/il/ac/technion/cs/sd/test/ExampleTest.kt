package il.ac.technion.cs.sd.test

/** JUnit5 imports. Add more if needed */
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions

/**
 * Example imports for more declarative testing.
 * Recommended, but not mandatory.
 * Use the web to figure out what to import for your specific tests.
 */

import java.io.FileNotFoundException

import il.ac.technion.cs.sd.app.GradesReader
import il.ac.technion.cs.sd.app.GradesInitializer
import kotlin.system.measureTimeMillis
import il.ac.technion.cs.sd.lib.StorageLibrary


class ExampleTest {

    private fun getGradesReader(fileName: String): GradesReader {
        StorageLibrary.clearStorage()
        val fileContents: String =
            javaClass.getResource(fileName)?.readText() ?:
            throw FileNotFoundException("Could not open file $fileName")

        val gradesInitializer = GradesInitializer()
        gradesInitializer.setup(fileContents)

        return GradesReader()
    }

    @Test
    fun `small file should return grade`() {
        val gradesReader = getGradesReader("small")
        Assertions.assertEquals(100, gradesReader.getGrade("123"))
    }


    @Test
    fun `small file should return null`() {
        val gradesReader = getGradesReader("small")
        Assertions.assertEquals(null, gradesReader.getGrade("1234"))
    }

    @Test
    fun `large file should return grade`() {
        val gradesReader = getGradesReader("large")
        Assertions.assertEquals(100, gradesReader.getGrade("123"))
    }

    @Test
    fun `should return grade`() {
        val elapsed = measureTimeMillis {

            val gradesReader = getGradesReader("onemillion_no_id")
            Assertions.assertEquals(null, gradesReader.getGrade("123456789"))
        }
        print("-----------------")
        print(elapsed )
        print(" milliseconds")
        print("which means "+String.format("%.3f", (elapsed.toDouble()/1000))+"second -------------------")
        Assertions.assertTrue(elapsed<10000)
    }

    @Test
    fun `should not return`() {
        val elapsed = measureTimeMillis {

            val gradesReader = getGradesReader("onemillion")
            Assertions.assertEquals(72, gradesReader.getGrade("52606543"))
        }

        print("-----------------")
        print(elapsed )
        print(" milliseconds")
        print("which means "+String.format("%.3f", (elapsed.toDouble()/1000))+"second -------------------")
        Assertions.assertTrue(elapsed<10000)
    }
    @Test
    fun `should return the last grade`() {
        val elapsed = measureTimeMillis {

            val gradesReader = getGradesReader("onemillion_more_than_one_grade")
            Assertions.assertEquals(72, gradesReader.getGrade("52606543"))
        }
        print("-----------------")
        print(elapsed )
        print(" milliseconds")
        print("which means "+String.format("%.3f", (elapsed.toDouble()/1000))+"second -------------------")
        Assertions.assertTrue(elapsed<10000)
    }
    @Test
    fun `added new line at the end`() {
        val elapsed = measureTimeMillis {

            val gradesReader = getGradesReader("addedEmptysAtTheEnd")
            Assertions.assertEquals(90, gradesReader.getGrade("123"))
        }
        print("-----------------")
        print(elapsed)
        print(" milliseconds")
        print("which means "+String.format("%.3f", (elapsed.toDouble()/1000))+"second -------------------")
        Assertions.assertTrue(elapsed<10000)
    }

    @Test
    fun `big_grades`() {
        val elapsed = measureTimeMillis {

            val gradesReader = getGradesReader("bigGrades")
            Assertions.assertEquals(900, gradesReader.getGrade("25"))
        }
        print("-----------------")
        print(elapsed)
        print(" milliseconds")
        print("which means "+String.format("%.3f", (elapsed.toDouble()/1000))+"second -------------------")
        Assertions.assertTrue(elapsed<10000)

        val elapsed1 = measureTimeMillis {

            val gradesReader = getGradesReader("bigGrades")
            Assertions.assertEquals(100, gradesReader.getGrade("12"))
        }
        print("-----------------")
        print(elapsed1)
        print(" milliseconds")
        print("which means "+String.format("%.3f", (elapsed1.toDouble()/1000))+"second -------------------")
        Assertions.assertTrue(elapsed1<10000)

        val elapsed2 = measureTimeMillis {

            val gradesReader = getGradesReader("bigGrades")
            Assertions.assertEquals(999, gradesReader.getGrade("2"))
        }
        print("-----------------")
        print(elapsed2)
        print(" milliseconds")
        print("which means "+String.format("%.3f", (elapsed2.toDouble()/1000))+"second -------------------")
        Assertions.assertTrue(elapsed2<10000)
    }
}