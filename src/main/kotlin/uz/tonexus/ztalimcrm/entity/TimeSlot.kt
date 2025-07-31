package uz.tonexus.ztalimcrm.entity

import io.jmix.core.metamodel.datatype.EnumClass

enum class TimeSlot(private val id: Int) : EnumClass<Int> {
    TimeSlot1(0),
    TimeSlot2(1),
    TimeSlot3(2),
    TimeSlot4(3),
    TimeSlot5(4),
    TimeSlot6(5),
    TimeSlot7(6);

    override fun getId() = id

    companion object {

        @JvmStatic
        fun fromId(id: Int): TimeSlot? = entries.find { it.id == id }
    }
}