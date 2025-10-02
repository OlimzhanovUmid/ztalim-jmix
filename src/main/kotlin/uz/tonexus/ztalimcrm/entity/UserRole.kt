package uz.tonexus.ztalimcrm.entity

import io.jmix.core.metamodel.datatype.EnumClass

enum class UserRole(private val id: String) : EnumClass<String> {
    Superadmin("Superadmin"),
    Teacher("Teacher");

    override fun getId() = id

    companion object {

        @JvmStatic
        fun fromId(id: String): UserRole? = entries.find { it.id == id }
    }
}