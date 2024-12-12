package il.ac.technion.cs.sd.app

import il.ac.technion.cs.sd.lib.StorageLibrary

import il.ac.technion.cs.sd.model.Student

class GradesReader{
    /** Returns the grade associated with 'id', or null */
    fun getGrade(id: String): Int? {
        val studentData = StorageLibrary.retrieveById(id) ?: return null
        val students = DataToStudentParser().parse(studentData)
        val student = students.firstOrNull()
        return student?.grade
    }
}
