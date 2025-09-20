package uz.tonexus.ztalimcrm.repository

import io.jmix.core.repository.JmixDataRepository
import org.springframework.stereotype.Repository
import uz.tonexus.ztalimcrm.entity.Appointment
import uz.tonexus.ztalimcrm.entity.AppointmentStatus
import java.util.*

@Repository
interface AppointmentRepository : JmixDataRepository<Appointment, UUID> {
    fun updateStatus(appointment: Appointment, appointmentStatus: AppointmentStatus): Appointment {
        return save(
            appointment.apply { setAppointmentStatus(appointmentStatus) }
        )
    }
}