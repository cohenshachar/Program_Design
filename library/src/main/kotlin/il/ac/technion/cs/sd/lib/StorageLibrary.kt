package il.ac.technion.cs.sd.lib
import il.ac.technion.cs.sd.dummy.StorageDummy
import il.ac.technion.cs.sd.grades.external.LineStorage

/**
 * Implement your library here. Feel free to change the class name,
 * but note that if you choose to change the class name,
 * you will need to update the import statements in GradesInitializer.kt
 * and in GradesReader.kt.
 */

class TrieNode<T : UniquelyIdentifiedStorable>(val value: Char, var isEndOfWord: Boolean = false, var payload: String? = null) {
    val children = mutableMapOf<Char, TrieNode<T>>()
}
class Trie<T : UniquelyIdentifiedStorable> {
    private val root = TrieNode<T>(' ')

    fun insert(item: UniquelyIdentifiedStorable) {
        var currentNode = root
        val word = item.getId()
        for (char in word) {
            currentNode = currentNode.children.computeIfAbsent(char) { TrieNode(char) }
        }
        currentNode.isEndOfWord = true
        currentNode.payload = item.toStorageString()
    }

    fun insertAll(items: List<T>) {
        for (item in items) {
            insert(item)
        }
    }

    fun getAsFormatedStringspaced(): List<String> {
        val result = mutableListOf<String>()
        formatNode(root, result, "")
        return result
    }

    private fun formatNode(node: TrieNode<T>, result: MutableList<String>, prefix: String) {
        if (node != root) {
            result.add("$prefix${node.value}")
        }
        for ((char, child) in node.children) {
            result.add("$prefix${node.value} -> $char")
            formatNode(child, result, "$prefix${node.value} -> ")
        }
        if (node.isEndOfWord) {
            result.add("$prefix${node.value} (end)")
        }
    }
}

class StorageLibrary {
    companion object {

        private val idToLineNumMap = mutableMapOf<Int, Int>() // last line the id was saved in

        fun storeUnique(items: List<UniquelyIdentifiedStorable>) {
            items.forEach { item ->
                val id = item.getId()
                LineStorage.appendLine(item.toStorageString()) // Add item's string to LineStorage
                val lineNum = LineStorage.numberOfLines()
                idToLineNumMap[id.toInt()] = lineNum // Map the ID to its line number
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
        fun retrieveById(id: String):String{
            val linenum=getLine(id.toInt())
            return if(linenum == null || linenum==-1)
            {
                ""
            }else{
                LineStorage.read(linenum)

            }

        }
        fun clearStorage(){
            StorageDummy.clear()
            idToLineNumMap.clear()
        }

    }

}
