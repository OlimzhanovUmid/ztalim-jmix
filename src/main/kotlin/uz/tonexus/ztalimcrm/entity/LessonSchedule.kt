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
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.util.*

@JmixEntity
@Table(name = "LESSON_SCHEDULE", indexes = [
    Index(name = "IDX_SCHEDULE_GROUP", columnList = "GROUP_ID"),
    Index(name = "IDX_SCHEDULE_TEACHER", columnList = "TEACHER_ID"),
    Index(name = "IDX_SCHEDULE_ROOM", columnList = "ROOM_ID"),
    Index(name = "IDX_SCHEDULE_TOPIC", columnList = "TOPIC_ID")
])
@Entity
open class LessonSchedule {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    var id: UUID? = null

    @Column(name = "STATUS", nullable = false)
    @NotNull
    var status: Int? = null

    @Column(name = "START_TIME", nullable = false)
    @NotNull
    var startTime: LocalDateTime? = null

    @Column(name = "END_TIME", nullable = false)
    @NotNull
    var endTime: LocalDateTime? = null

    @JoinColumn(name = "GROUP_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    var group: Group? = null

    @JoinColumn(name = "TEACHER_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    var teacher: User? = null

    @JoinColumn(name = "ROOM_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    var room: StudyRoom? = null

    @JoinColumn(name = "TOPIC_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    var topic: Topic? = null

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

    @Column(name = "VERSION", nullable = false)
    @Version
    var version: Int? = null

    fun getStatus(): ScheduleStatus? = status?.let { ScheduleStatus.fromId(it) }

    fun setStatus(status: ScheduleStatus?) {
        this.status = status?.id
    }

    @InstanceName
    @DependsOnProperties("room", "teacher", "topic")
    fun getInstanceName(metadataTools: MetadataTools): String =
        "${metadataTools.format(room?.name)} ${metadataTools.format(teacher?.firstName)} ${metadataTools.format(topic?.name)}".trim()
}