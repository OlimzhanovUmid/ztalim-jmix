package uz.tonexus.ztalimcrm.entity

import io.jmix.core.metamodel.datatype.EnumClass

enum class ScheduleStatus(private val id: Int) : EnumClass<Int> {
    SCHEDULED(0),
    COMPLETED(1),
    CANCELLED(2),
    RESCHEDULED(3);

    override fun getId() = id

    companion object {

        @JvmStatic
        fun fromId(id: Int): ScheduleStatus? = entries.find { it.id == id }
    }
}