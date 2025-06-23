package uz.tonexus.ztalimcrm.entity

import io.jmix.core.annotation.DeletedBy
import io.jmix.core.annotation.DeletedDate
import io.jmix.core.entity.annotation.JmixGeneratedValue
import io.jmix.core.metamodel.annotation.InstanceName
import io.jmix.core.metamodel.annotation.JmixEntity
import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import java.time.OffsetDateTime
import java.util.*

@JmixEntity
@Table(name = "PARENT")
@Entity
open class Parent {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    var id: UUID? = null

    @InstanceName
    @Column(name = "FULL_NAME", nullable = false)
    @NotNull
    var fullName: String? = null

    @Column(name = "PHONE_NUMBER", nullable = false)
    @NotNull
    var phoneNumber: String? = null

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "parent")
    var student: Student? = null

    @DeletedBy
    @Column(name = "DELETED_BY")
    var deletedBy: String? = null

    @DeletedDate
    @Column(name = "DELETED_DATE")
    var deletedDate: OffsetDateTime? = null

}