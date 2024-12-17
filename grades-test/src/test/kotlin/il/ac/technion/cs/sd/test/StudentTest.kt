package il.ac.technion.cs.sd.test

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

import il.ac.technion.cs.sd.model.Student
import kotlin.system.measureTimeMillis
import kotlinx.coroutines.* // Use if you prefer suspending `delay`

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
        val student = Student.create("123456789", "1100") // Grade is out of range
        assertNull(student)
    }

    @Test
    fun `test valid student storage print`() {
        val studentStorageString = Student.create("123456789", "99")?.toStorageString()
        assertEquals("123456789,99", studentStorageString)
    }

    @Test
    fun `test valid student creation with minimum ID length`() {
        val student = Student.create("1", "85")
        assertNotNull(student)
        assertEquals(1u, student?.id)
        assertEquals(85, student?.grade)
    }

    @Test
    fun `test valid student creation with maximum ID length`() {
        val student = Student.create("123456789", "85")
        assertNotNull(student)
        assertEquals(123456789u, student?.id)
        assertEquals(85, student?.grade)
    }

    @Test
    fun `test invalid student creation with empty ID`() {
        val student = Student.create("", "85")
        assertNull(student)
    }

    @Test
    fun `test invalid student creation with whitespace ID`() {
        val student = Student.create("   ", "85")
        assertNull(student)
    }

    @Test
    fun `test invalid student creation with empty grade`() {
        val student = Student.create("123456789", "")
        assertNull(student)
    }

    @Test
    fun `test invalid student creation with whitespace grade`() {
        val student = Student.create("123456789", "   ")
        assertNull(student)
    }
}
