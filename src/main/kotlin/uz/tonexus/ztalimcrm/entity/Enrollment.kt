package uz.tonexus.ztalimcrm.entity

import io.jmix.core.MetadataTools
import io.jmix.core.annotation.DeletedBy
import io.jmix.core.annotation.DeletedDate
import io.jmix.core.entity.annotation.JmixGeneratedValue
import io.jmix.core.metamodel.annotation.InstanceName
import io.jmix.core.metamodel.annotation.JmixEntity
import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import java.math.BigDecimal
import java.time.LocalDate
import java.time.OffsetDateTime
import java.util.*

@JmixEntity
@Table(name = "ENROLLMENT", indexes = [
    Index(name = "IDX_ENROLLMENT_STUDENT", columnList = "STUDENT_ID")
])
@Entity
open class Enrollment {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    var id: UUID? = null

    @JoinColumn(name = "STUDENT_ID", nullable = false)
    @NotNull
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    var student: Student? = null

    @JoinColumn(name = "COURSE_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @NotNull
    var course: Course? = null

    @Column(name = "ENROLLMENT_DATE", nullable = false)
    @NotNull
    var enrollmentDate: LocalDate? = null

    @Column(name = "TOTAL_AMOUNT", nullable = false, precision = 10, scale = 2)
    @NotNull
    var totalAmount: BigDecimal? = null

    @Column(name = "DISCOUNT_AMOUNT", precision = 10, scale = 2)
    var discountAmount: BigDecimal? = BigDecimal.ZERO

    @Column(name = "FINAL_AMOUNT", nullable = false, precision = 10, scale = 2)
    @NotNull
    var finalAmount: BigDecimal? = null

    @Column(name = "STATUS", nullable = false)
    @NotNull
    private var status: Int? = EnrollmentStatus.ACTIVE.id

    @JoinColumn(name = "APPOINTMENT_ID")
    @OneToOne(fetch = FetchType.LAZY)
    var appointment: Appointment? = null

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

    fun getStatus(): EnrollmentStatus? = status?.let { EnrollmentStatus.fromId(it) }

    fun setStatus(status: EnrollmentStatus?) {
        this.status = status?.id
    }

    @InstanceName
    fun getInstanceName(metadataTools: MetadataTools): String = "${student?.getInstanceName(metadataTools)} - ${course?.name}"
}