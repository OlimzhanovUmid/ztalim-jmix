package uz.tonexus.ztalimcrm.entity

import io.jmix.core.metamodel.datatype.EnumClass

enum class Gender(private val id: Int) : EnumClass<Int> {
    Male(0),
    Female(10);

    override fun getId() = id

    companion object {

        @JvmStatic
        fun fromId(id: Int): Gender? = entries.find { it.id == id }
    }
}