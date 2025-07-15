package uz.tonexus.ztalimcrm.services

import io.jmix.core.DataManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import uz.tonexus.ztalimcrm.entity.Group
import uz.tonexus.ztalimcrm.entity.Schedule
import uz.tonexus.ztalimcrm.entity.ScheduleStatus
import uz.tonexus.ztalimcrm.entity.StudyRoom
import uz.tonexus.ztalimcrm.entity.Topic
import uz.tonexus.ztalimcrm.entity.User
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
    ): Schedule {
        // Проверка конфликтов
        validateScheduleConflicts(teacher, room, startTime, endTime)

        val schedule = dataManager.create(Schedule::class.java)
        schedule.group = group
        schedule.teacher = teacher
        schedule.room = room
        schedule.startTime = startTime
        schedule.endTime = endTime
        schedule.topic = topic
        schedule.status = ScheduleStatus.SCHEDULED.id

        return dataManager.save(schedule)
    }

    fun getScheduleForWeek(startDate: LocalDate): List<Schedule> {
        val endDate = startDate.plusDays(6)
        return dataManager.load(Schedule::class.java)
            .query("select s from Schedule s where s.startTime >= :start and s.startTime <= :end")
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
        val teacherConflicts = dataManager.load(Schedule::class.java)
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
        val roomConflicts = dataManager.load(Schedule::class.java)
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