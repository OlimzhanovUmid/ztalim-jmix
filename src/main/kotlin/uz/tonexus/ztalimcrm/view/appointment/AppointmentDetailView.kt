package uz.tonexus.ztalimcrm.view.appointment

import com.vaadin.flow.router.Route
import io.jmix.flowui.view.*
import uz.tonexus.ztalimcrm.entity.Appointment
import uz.tonexus.ztalimcrm.view.main.MainView

@Route(value = "appointments/:id", layout = MainView::class)
@ViewController(id = "Appointment.detail")
@ViewDescriptor(path = "appointment-detail-view.xml")
@EditedEntityContainer("appointmentDc")
@DialogMode(width = "40em")
class AppointmentDetailView : StandardDetailView<Appointment>()