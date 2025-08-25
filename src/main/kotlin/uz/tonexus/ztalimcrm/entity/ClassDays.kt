package uz.tonexus.ztalimcrm.entity

import io.jmix.core.metamodel.datatype.EnumClass

enum class ClassDays(private val id: Int) : EnumClass<Int> {
    MondayWednesdayFriday(0),
    TuesdayThursdaySaturday(1),
    EveryDay(2);

    override fun getId() = id

    companion object {

        @JvmStatic
        fun fromId(id: Int): ClassDays? = entries.find { it.id == id }
    }
}