package uz.tonexus.ztalimcrm.security

import io.jmix.security.role.annotation.ResourceRole

@ResourceRole(name = "Teacher", code = TeacherRole.CODE)
interface TeacherRole {

    companion object {
        const val CODE = "teacher"
    }
}