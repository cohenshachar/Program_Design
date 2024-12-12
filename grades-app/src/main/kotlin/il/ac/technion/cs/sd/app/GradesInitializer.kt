package il.ac.technion.cs.sd.app

import il.ac.technion.cs.sd.lib.StorageLibrary

/** This class will be instantiated once per test */
class Student private constructor(val id: UInt, var grade: Int) {
    companion object {
        fun create(id: String, grade: String): Student? {
            return if (validId(id) && validGrade(grade)) {
                Student(id.trim().toUInt(), grade.trim().toInt())
            } else {
                null
            }
        }
        private fun validId(id: String): Boolean {
            val parsedId = id.trim().toUIntOrNull()
            return parsedId != null && id.trim().length in 1..9
        }

        private fun validGrade(grade: String): Boolean {
            val parsedGrade = grade.trim().toIntOrNull()
            return parsedGrade != null && parsedGrade in 0..100
        }
    }
}

interface DataParser<T> {
    fun parse(data: String): List<T>
}

class DataToStudentParser : DataParser<Student> {
    override fun parse(data: String): List<Student> {
        val students = mutableListOf<Student>()
        data.lines().forEach { line ->
            val parts = line.split(",")
            if (parts.size == 2) {
                val student = Student.create(parts[0], parts[1])
                if (student != null) {
                    students.add(student)
                } else {
                    println("Invalid data: $line")
                }
            } else {
                println("Invalid line format: $line")
            }
        }
        return students
    }
}

class GradesInitializer {
    /** Saves csvData persistently, so that it could be run using GradesRunner. */
    fun setup(csvData: String) {
        val students = DataToStudentParser().parse(csvData)
        val storage = StorageLibrary().store(students)
    }
}
