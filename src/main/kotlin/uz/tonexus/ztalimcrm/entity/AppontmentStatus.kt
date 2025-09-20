package uz.tonexus.ztalimcrm.entity

import io.jmix.core.metamodel.datatype.EnumClass

enum class AppointmentStatus(private val id: Int) : EnumClass<Int> {
    NotProcessed(0),
    Failed(1),
    Processed(2);

    override fun getId() = id

    companion object {

        @JvmStatic
        fun fromId(id: Int): AppointmentStatus? = entries.find { it.id == id }
    }
}