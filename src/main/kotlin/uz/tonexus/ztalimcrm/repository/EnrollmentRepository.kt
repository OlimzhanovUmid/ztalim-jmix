package uz.tonexus.ztalimcrm.repository

import io.jmix.core.repository.JmixDataRepository
import org.springframework.stereotype.Repository
import uz.tonexus.ztalimcrm.entity.Enrollment
import java.util.*

@Repository
interface EnrollmentRepository : JmixDataRepository<Enrollment, UUID>