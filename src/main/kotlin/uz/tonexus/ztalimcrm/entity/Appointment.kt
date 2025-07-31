package uz.tonexus.ztalimcrm.entity

import io.jmix.core.MetadataTools
import io.jmix.core.annotation.DeletedBy
import io.jmix.core.annotation.DeletedDate
import io.jmix.core.entity.annotation.JmixGeneratedValue
import io.jmix.core.metamodel.annotation.DependsOnProperties
import io.jmix.core.metamodel.annotation.InstanceName
import io.jmix.core.metamodel.annotation.JmixEntity
import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDate
import java.time.OffsetDateTime
import java.util.*

@JmixEntity
@Table(name = "APPOINTMENT", indexes = [
    Index(name = "IDX_APPOINTMENT_PARENT", columnList = "PARENT_ID")
])
@Entity
open class Appointment {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    var id: UUID? = null

    @Column(name = "FIRST_NAME", nullable = false)
    @NotNull
    var firstName: String? = null

    @Column(name = "LAST_NAME", nullable = false)
    @NotNull
    var lastName: String? = null

    @Column(name = "PHONE_NUMBER", nullable = false)
    @NotNull
    var phoneNumber: String? = null

    @Column(name = "GENDER", nullable = false)
    @NotNull
    private var gender: Int? = null

    @Column(name = "DATE_OF_BIRTH", nullable = false)
    @NotNull
    var dateOfBirth: LocalDate? = null

    @JoinColumn(name = "PARENT_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    var parent: Parent? = null

    @Column(name = "PREFERRED_DAYS", nullable = false)
    @NotNull
    private var preferredDays: Int? = null

    @Column(name = "PREFERRED_TIME", nullable = false)
    @NotNull
    private var preferredTime: Int? = null

    @CreatedBy
    @Column(name = "CREATED_BY")
    var createdBy: String? = null

    @CreatedDate
    @Column(name = "CREATED_DATE")
    var createdDate: OffsetDateTime? = null

    @LastModifiedBy
    @Column(name = "LAST_MODIFIED_BY")
    var lastModifiedBy: String? = null

    @LastModifiedDate
    @Column(name = "LAST_MODIFIED_DATE")
    var lastModifiedDate: OffsetDateTime? = null

    @DeletedBy
    @Column(name = "DELETED_BY")
    var deletedBy: String? = null

    @DeletedDate
    @Column(name = "DELETED_DATE")
    var deletedDate: OffsetDateTime? = null

    fun getPreferredTime(): TimeSlot? = preferredTime?.let { TimeSlot.fromId(it) }

    fun setPreferredTime(preferredTime: TimeSlot?) {
        this.preferredTime = preferredTime?.id
    }

    fun getPreferredDays(): ClassDays? = preferredDays?.let { ClassDays.fromId(it) }

    fun setPreferredDays(preferredDays: ClassDays?) {
        this.preferredDays = preferredDays?.id
    }

    fun getGender(): Gender? = gender?.let { Gender.fromId(it) }

    fun setGender(gender: Gender?) {
        this.gender = gender?.id
    }

    @InstanceName
    @DependsOnProperties("firstName", "lastName")
    fun getInstanceName(metadataTools: MetadataTools): String =
        "${metadataTools.format(firstName)} ${metadataTools.format(lastName)}".trim()
}