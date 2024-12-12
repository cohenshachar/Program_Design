package il.ac.technion.cs.sd.lib

interface Storable {
    fun toStorageString(): String
}

interface UniquelyIdentifiedStorable :  Storable{
    fun getId(): String
}