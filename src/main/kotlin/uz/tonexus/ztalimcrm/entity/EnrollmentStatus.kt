package uz.tonexus.ztalimcrm.entity

enum class EnrollmentStatus(private val id: Int) : io.jmix.core.metamodel.datatype.EnumClass<Int> {
    ACTIVE(0),
    COMPLETED(1),
    SUSPENDED(2),
    CANCELLED(3);

    override fun getId() = id

    companion object {
        @JvmStatic
        fun fromId(id: Int): EnrollmentStatus? = entries.find { it.id == id }
    }
}