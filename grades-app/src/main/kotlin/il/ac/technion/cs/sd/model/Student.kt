package il.ac.technion.cs.sd.model

import il.ac.technion.cs.sd.lib.Storable

class Student private constructor(val id: UInt, var grade: Int) : Storable {
    companion object {
        fun create(id: String, grade: String): Student? {
            val trimmedId = id.trim()
            val trimmedGrade = grade.trim()
            return if (trimmedId.isValidId() && trimmedGrade.isValidGrade()) {
                Student(trimmedId.toUInt(), trimmedGrade.toInt())
            } else {
                null
            }
        }

        private fun String.isValidId(): Boolean {
            val parsedId = this.toUIntOrNull()
            return parsedId != null && this.length in 1..9
        }

        private fun String.isValidGrade(): Boolean {
            val parsedGrade = this.toIntOrNull()
            return parsedGrade != null && parsedGrade in 0..100
        }
    }

    override fun toStorageString(): String {
        return "$id,$grade"
    }
}
