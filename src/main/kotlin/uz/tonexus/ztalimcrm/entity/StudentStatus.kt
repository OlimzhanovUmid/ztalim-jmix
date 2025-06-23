package uz.tonexus.ztalimcrm.entity

import io.jmix.core.metamodel.datatype.EnumClass

enum class StudentStatus(private val id: Int) : EnumClass<Int> {
    INACTIVE(0),
    ACTIVE(1),;

    override fun getId() = id

    companion object {

        @JvmStatic
        fun fromId(id: Int): StudentStatus? = entries.find { it.id == id }
    }
}