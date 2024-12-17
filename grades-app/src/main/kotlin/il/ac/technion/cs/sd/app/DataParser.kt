package il.ac.technion.cs.sd.app

import il.ac.technion.cs.sd.model.Student

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
                println("Invalid line format: $line") // no need just for debugging
            }
        }
        return students
    }
}