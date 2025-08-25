package uz.tonexus.ztalimcrm.view.appointment

import com.vaadin.flow.router.Route
import io.jmix.flowui.model.CollectionLoader
import io.jmix.flowui.view.*
import uz.tonexus.ztalimcrm.entity.Appointment
import uz.tonexus.ztalimcrm.view.main.MainView


@Route(value = "appointments", layout = MainView::class)
@ViewController(id = "Appointment.list")
@ViewDescriptor(path = "appointment-list-view.xml")
@LookupComponent("appointmentsDataGrid")
@DialogMode(width = "64em")
class AppointmentListView : StandardListView<Appointment>() {}