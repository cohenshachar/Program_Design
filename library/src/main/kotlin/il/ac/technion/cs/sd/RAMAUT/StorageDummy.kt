package il.ac.technion.cs.sd.RAMAUT

class StorageDummy {
    private val storage = mutableListOf<String>()

    /** Appends a string to the end of the collection */
    fun append(item: String) {
        storage.add(item)
    }

    /** Returns the number of items in the collection */
    fun size(): Int {
        return storage.size
    }

    /** Returns the item at the specified index */
    fun get(index: Int): String? {
        return if (index in 0 until storage.size) {
            storage[index]
        } else {
            null
        }
    }

    /** Clears the collection */
    fun clear() {
        storage.clear()
    }
}
