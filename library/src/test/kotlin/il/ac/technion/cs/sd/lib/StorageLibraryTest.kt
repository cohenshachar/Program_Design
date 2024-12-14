package il.ac.technion.cs.sd.lib

import org.junit.jupiter.api.*

// added these
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MockItem(private val id: String, private val data: String, private val line: Int) : UniquelyIdentifiedStorable {
    override fun getId(): String = id
    override fun toStorageString(): String = "$id,$data,$line"
}

/** Use this class to test your library implementation */

class StorageLibraryTest {


    private val genericGradeManagertest = GenericGradeManagerTest()

    @BeforeEach
    fun setUp() {
       StorageLibrary.clearStorage()
    }
    @Test
    fun testingGenericGradeMangaer() {
        genericGradeManagertest.testGetGradeForNonExistingId()
        genericGradeManagertest.testInsertWithDifferentGenericTypes()
        genericGradeManagertest.testInsertNewEntry()
        genericGradeManagertest.testGetAllDataSorted()
        genericGradeManagertest.testInsertUpdateExistingEntry()
        genericGradeManagertest.testGetAllDataEmpty()

    }


        @Test
    fun storeUniqueShouldAddItemsToTheStorage() {
        val item = MockItem("1", "Dddd",10)
        StorageLibrary.storeUnique(listOf(item))

        val retrievedItem = StorageLibrary.retrieveById("1")
        assertNotNull(retrievedItem)
        assertEquals("1,Dddd,10", retrievedItem)
    }

    @Test
    fun getLineShouldReturnTheCorrectLineNumberForAGivenId() {
        val item0 = MockItem("0","Data 0", 0)
        val item1 = MockItem("1","Data 1", 1)
        StorageLibrary.storeUnique(listOf(item0,item1))


        val line = StorageLibrary.getLine("1")
        assertNotNull(line)
        assertEquals(1, line)
    }

    @Test
    fun retrieveByIdShouldReturnNullIfItemIsNotFound() {
        val result = StorageLibrary.retrieveById("21")
        assertNull(result)
        val result1 = StorageLibrary.retrieveById("zxc21zxcsda")
        assertNull(result1)
        val result2 = StorageLibrary.retrieveById("zxcsda")
        assertNull(result2)
    }

    @Test
    fun clearStorageShouldRemoveAllItemsFromStorage() {
        val item1 = MockItem("3", "Data 3", 30)
        val item2 = MockItem("4", "Data 4", 40)
        StorageLibrary.storeUnique(listOf(item1, item2))
        StorageLibrary.clearStorage()
        assertNull(StorageLibrary.retrieveById("3"))
        assertNull(StorageLibrary.retrieveById("4"))
    }


}

class GenericGradeManagerTest {

    @Test
    fun testInsertNewEntry() {
        val manager = GenericGradeManager<String>()
        manager.insert(1, "A")
        assertEquals("A", manager.getGrade(1))
    }

    @Test
    fun testInsertUpdateExistingEntry() {
        val manager = GenericGradeManager<String>()
        manager.insert(1, "A")
        manager.insert(1, "B")
        assertEquals("B", manager.getGrade(1))
    }

    @Test
    fun testGetGradeForNonExistingId() {
        val manager = GenericGradeManager<String>()
        assertNull(manager.getGrade(999))
    }

    @Test
    fun testGetAllDataEmpty() {
        val manager = GenericGradeManager<String>()
        val allData = manager.getAllData()
        assertTrue(allData.isEmpty())
    }

    @Test
    fun testGetAllDataSorted() {
        val manager = GenericGradeManager<String>()
        manager.insert(2, "B")
        manager.insert(1, "A")
        manager.insert(3, "C")

        val expected = listOf(
                1 to "A",
                2 to "B",
                3 to "C"
        )

        assertEquals(expected, manager.getAllData())
    }
    @Test
    fun testUpdating() {
        val manager = GenericGradeManager<String>()
        manager.insert(2, "B")
        manager.insert(1, "A")
        manager.insert(3, "C")
        manager.insert(1,"K")

        val expected = listOf(
            1 to "K",
            2 to "B",
            3 to "C"
        )
        val tocheck=manager.getAllData()
        assertEquals(expected, manager.getAllData())
    }

    @Test
    fun testInsertWithDifferentGenericTypes() {
        val intManager = GenericGradeManager<Int>()
        intManager.insert(1, 100)
        assertEquals(100, intManager.getGrade(1))

        val doubleManager = GenericGradeManager<Double>()
        doubleManager.insert(1, 99.9)
        assertEquals(99.9, doubleManager.getGrade(1))

        val stringManager = GenericGradeManager<String>()
        stringManager.insert(1, "Excellent")
        assertEquals("Excellent", stringManager.getGrade(1))
    }
}
