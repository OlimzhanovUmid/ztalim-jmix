package uz.tonexus.ztalimcrm.repository

import io.jmix.core.repository.JmixDataRepository
import org.springframework.stereotype.Repository
import uz.tonexus.ztalimcrm.entity.Appointment
import java.util.*

@Repository
interface AppointmentRepository : JmixDataRepository<Appointment, UUID>