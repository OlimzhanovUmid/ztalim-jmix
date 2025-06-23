package uz.tonexus.ztalimcrm.security

import io.jmix.security.role.annotation.ResourceRole
import io.jmix.securityflowui.role.annotation.MenuPolicy
import io.jmix.securityflowui.role.annotation.ViewPolicy

@ResourceRole(name = "Administrator", code = AdministratorRole.CODE)
interface AdministratorRole {

    companion object {
        const val CODE = "administrator"
    }

    @MenuPolicy(menuIds = ["Student.list"])
    @ViewPolicy(viewIds = ["Student.detail", "Student.list"])
    fun screens()
}