package il.ac.technion.cs.sd.app

import il.ac.technion.cs.sd.lib.StorageLibrary

class GradesInitializer {
    /** Saves csvData persistently, so that it could be run using GradesRunner. */
    fun setup(csvData: String) {
        val students = DataToStudentParser().parse(csvData)
        val storage = StorageLibrary.storeUnique(students)
    }
}
