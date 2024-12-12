package il.ac.technion.cs.sd.lib

import il.ac.technion.cs.sd.grades.external.LineStorage

/**
 * Implement your library here. Feel free to change the class name,
 * but note that if you choose to change the class name,
 * you will need to update the import statements in GradesInitializer.kt
 * and in GradesReader.kt.
 */

class StorageLibrary {
    companion object {

        private val idToLineNumMap = mutableMapOf<Int, Int>() // last line the id was saved in

        fun storeUnique(items: List<UniquelyIdentifiedStorable>) {
            items.forEach { item ->
                val id = item.getId()
                LineStorage.appendLine(item.toStorageString()) // Add item's string to LineStorage
                val lineNum = LineStorage.numberOfLines()
                idToLineNumMap[id] = lineNum // Map the ID to its line number

            }
        }

        fun hasId(id: Int): Boolean {
            return if (id in idToLineNumMap) {
                true
            } else {
                println("ID $id not found.")
                false
            }
        }
        fun getLine(id:Int): Int?{
            return if (id in idToLineNumMap) {
                idToLineNumMap[id]
            }else {
                return -1
            }


        }
        fun retrieveById(id: Int):String{
            val linenum=getLine(id)
            return if(linenum==-1)
            {
                ""
            }else{
                LineStorage.read(linenum)

            }

        }

    }

}
