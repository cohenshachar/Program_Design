package il.ac.technion.cs.sd.test

/** JUnit5 imports. Add more if needed */
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions

/**
 * Example imports for more declarative testing.
 * Recommended, but not mandatory.
 * Use the web to figure out what to import for your specific tests.
 */
import com.natpryce.hamkrest.assertion.assertThat
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

import java.io.FileNotFoundException

import il.ac.technion.cs.sd.app.GradesReader
import il.ac.technion.cs.sd.app.GradesInitializer


class ExampleTest {

    private fun getGradesReader(fileName: String): GradesReader {
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
}