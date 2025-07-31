package uz.tonexus.ztalimcrm.entity

import io.jmix.core.DeletePolicy
import io.jmix.core.MetadataTools
import io.jmix.core.annotation.DeletedBy
import io.jmix.core.annotation.DeletedDate
import io.jmix.core.entity.annotation.JmixGeneratedValue
import io.jmix.core.entity.annotation.OnDelete
import io.jmix.core.entity.annotation.OnDeleteInverse
import io.jmix.core.metamodel.annotation.DependsOnProperties
import io.jmix.core.metamodel.annotation.InstanceName
import io.jmix.core.metamodel.annotation.JmixEntity
import io.jmix.eclipselink.lazyloading.NotInstantiatedList
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
@Table(name = "STUDENT", indexes = [
    Index(name = "IDX_STUDENT_PARENT", columnList = "PARENT_ID")
])
@Entity
open class Student {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    var id: UUID? = null

    @Column(name = "FIRST_NAME", nullable = false, length = 50)
    @NotNull
    var firstName: String? = null

    @Column(name = "LAST_NAME", nullable = false)
    @NotNull
    var lastName: String? = null

    @Column(name = "DATE_OF_BIRTH", nullable = false)
    @NotNull
    var dateOfBirth: LocalDate? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDeleteInverse(DeletePolicy.CASCADE)
    @JoinColumn(name = "PARENT_ID")
    var parent: Parent? = null

    @Column(name = "GENDER", nullable = false)
    @NotNull
    private var gender: Int? = null

    @Column(name = "STATUS", nullable = false)
    @NotNull
    private var status: Int? = StudentStatus.ACTIVE.id

    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "student")
    var comment: MutableList<Comment> = NotInstantiatedList()

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

    @JoinTable(
        name = "GROUP_STUDENT_LINK",
        joinColumns = [JoinColumn(name = "STUDENT_ID")],
        inverseJoinColumns = [JoinColumn(name = "GROUP_ID")]
    )
    @OrderBy("name")
    @ManyToMany
    var groups: MutableList<Group> = NotInstantiatedList()

    fun getGender(): Gender? = gender?.let { Gender.fromId(it) }

    fun setGender(gender: Gender?) {
        this.gender = gender?.id
    }

    fun getStatus(): StudentStatus? = status?.let { StudentStatus.fromId(it) }

    fun setStatus(status: StudentStatus?) {
        this.status = status?.id
    }

    @InstanceName
    @DependsOnProperties("firstName", "lastName")
    fun getInstanceName(metadataTools: MetadataTools): String =
        "${metadataTools.format(firstName)} ${metadataTools.format(lastName)}".trim()
}