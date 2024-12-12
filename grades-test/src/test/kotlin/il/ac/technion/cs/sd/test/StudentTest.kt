package il.ac.technion.cs.sd.test

/** JUnit5 imports. Add more if needed */
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

/**
 * Example imports for more declarative testing.
 * Recommended, but not mandatory.
 * Use the web to figure out what to import for your specific tests.
 */
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.matches
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

import java.io.FileNotFoundException

import il.ac.technion.cs.sd.model.Student

class StudentTest {

    @Test
    fun `test valid student creation`() {
        val student = Student.create("123456789", "85")
        assertNotNull(student)
        assertEquals(123456789u, student?.id)
        assertEquals(85, student?.grade)
    }

    @Test
    fun `test invalid student creation with invalid ID`() {
        val student = Student.create("invalid_id", "85")
        assertNull(student)
    }

    @Test
    fun `test invalid student creation with invalid grade`() {
        val student = Student.create("123456789", "invalid_grade")
        assertNull(student)
    }

    @Test
    fun `test invalid student creation with out of range ID`() {
        val student = Student.create("1234567890", "85") // ID length is 10, which is invalid
        assertNull(student)
    }

    @Test
    fun `test invalid student creation with out of range grade`() {
        val student = Student.create("123456789", "101") // Grade is out of range
        assertNull(student)
    }

    @Test
    fun `test valid ID`() {
        assertTrue(Student.create("123456789", "85") != null)
    }

    @Test
    fun `test valid grade`() {
        assertTrue(Student.create("123456789", "85") != null)
    }

}
