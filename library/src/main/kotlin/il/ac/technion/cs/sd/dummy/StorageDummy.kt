package il.ac.technion.cs.sd.dummy

object StorageDummy {

    private val storage = mutableListOf<String>()
    fun append(item: String) {
        storage.add(item)
    }
    /**number of lines inserted*/
    fun size(): Int {
        return storage.size
    }

    /** Returns the item at the specified index-line */
    fun get(index: Int): String? {
        return if (index in 0 until storage.size) {
            storage[index]
        } else {
            null
        }
    }
    /** clears the collection - to use between tests */
    fun clear() {
        storage.clear()
    }
}
