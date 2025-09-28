package uz.tonexus.ztalimcrm.services

import io.jmix.core.DataManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import uz.tonexus.ztalimcrm.entity.*
import java.time.LocalDate
import java.time.LocalDateTime

@Service
class ScheduleService {

    @Autowired
    private lateinit var dataManager: DataManager

    fun createScheduleForGroup(
        group: Group,
        teacher: User,
        room: StudyRoom,
        startTime: LocalDateTime,
        endTime: LocalDateTime,
        topic: Topic? = null
    ): LessonSchedule {
        // Проверка конфликтов
        validateScheduleConflicts(teacher, room, startTime, endTime)

        val lessonSchedule = dataManager.create(LessonSchedule::class.java)
        lessonSchedule.group = group
        lessonSchedule.teacher = teacher
        lessonSchedule.room = room
        lessonSchedule.startTime = startTime
        lessonSchedule.endTime = endTime
        lessonSchedule.topic = topic
        lessonSchedule.status = ScheduleStatus.SCHEDULED.id

        return dataManager.save(lessonSchedule)
    }

    fun getScheduleForWeek(startDate: LocalDate): List<LessonSchedule> {
        val endDate = startDate.plusDays(6)
        return dataManager.load(LessonSchedule::class.java)
            .query("select s from LessonSchedule s where s.startTime >= :start and s.startTime <= :end")
            .parameter("start", startDate.atStartOfDay())
            .parameter("end", endDate.atTime(23, 59, 59))
            .list()
    }

    private fun validateScheduleConflicts(
        teacher: User,
        room: StudyRoom,
        startTime: LocalDateTime,
        endTime: LocalDateTime
    ) {
        // Проверка занятости преподавателя
        val teacherConflicts = dataManager.load(LessonSchedule::class.java)
            .query("select s from Schedule s where s.teacher = :teacher and " +
                    "((s.startTime <= :start and s.endTime > :start) or " +
                    "(s.startTime < :end and s.endTime >= :end) or " +
                    "(s.startTime >= :start and s.endTime <= :end))")
            .parameter("teacher", teacher)
            .parameter("start", startTime)
            .parameter("end", endTime)
            .list()

        if (teacherConflicts.isNotEmpty()) {
            throw IllegalStateException("Преподаватель уже занят в это время")
        }

        // Аналогичная проверка для аудитории
        val roomConflicts = dataManager.load(LessonSchedule::class.java)
            .query("select s from Schedule s where s.room = :room and " +
                    "((s.startTime <= :start and s.endTime > :start) or " +
                    "(s.startTime < :end and s.endTime >= :end) or " +
                    "(s.startTime >= :start and s.endTime <= :end))")
            .parameter("room", room)
            .parameter("start", startTime)
            .parameter("end", endTime)
            .list()

        if (roomConflicts.isNotEmpty()) {
            throw IllegalStateException("Аудитория уже занята в это время")
        }
    }
}