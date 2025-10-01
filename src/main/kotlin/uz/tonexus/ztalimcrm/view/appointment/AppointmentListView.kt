package uz.tonexus.ztalimcrm.view.appointment

import com.vaadin.flow.component.ClickEvent
import com.vaadin.flow.component.grid.ItemDoubleClickEvent
import com.vaadin.flow.router.Route
import io.jmix.flowui.Dialogs
import io.jmix.flowui.app.inputdialog.DialogActions
import io.jmix.flowui.app.inputdialog.InputParameter.enumParameter
import io.jmix.flowui.app.inputdialog.InputParameter.stringParameter
import io.jmix.flowui.kit.component.button.JmixButton
import io.jmix.flowui.model.CollectionLoader
import io.jmix.flowui.view.*
import org.springframework.beans.factory.annotation.Autowired
import uz.tonexus.ztalimcrm.entity.Appointment
import uz.tonexus.ztalimcrm.entity.AppointmentStatus
import uz.tonexus.ztalimcrm.extensions.isOk
import uz.tonexus.ztalimcrm.repository.AppointmentRepository
import uz.tonexus.ztalimcrm.view.main.MainView

@Route(value = "appointments", layout = MainView::class)
@ViewController(id = "Appointment.list")
@ViewDescriptor(path = "appointment-list-view.xml")
@LookupComponent("appointmentsDataGrid")
@DialogMode(width = "64em")
class AppointmentListView : StandardListView<Appointment>() {
    @ViewComponent
    private lateinit var appointmentsDl: CollectionLoader<Appointment>

    @Autowired
    private lateinit var messageBundle: MessageBundle

    @Autowired
    private lateinit var appointmentRepository: AppointmentRepository

    @Autowired
    private lateinit var dialogs: Dialogs

    @Subscribe("appointmentsDataGrid")
    private fun onAppointmentsDataGridItemDoubleClick(event: ItemDoubleClickEvent<Appointment>) {
        dialogs.createInputDialog(this).apply {
            withHeader(messageBundle.getMessage("updateAppointmentStatus"))
            withParameters(
                enumParameter("appointmentStatus", AppointmentStatus::class.java).apply {
                    withLabel(messageBundle.getMessage("appointmentStatus"))
                    withDefaultValue(event.item.getAppointmentStatus())
                },
                stringParameter("appointmentComment").withLabel(messageBundle.getMessage("appointmentComment"))
            )
            withActions(DialogActions.OK_CANCEL)
            withCloseListener { closeEvent ->
                if (closeEvent.isOk) {
                    (closeEvent.values["appointmentStatus"] as? AppointmentStatus)?.let { status ->
                        appointmentRepository.save(
                            event.item.apply { setAppointmentStatus(status) }
                        )
                    }
                }
            }
        }.open()
        appointmentsDl.load()
    }

    @Subscribe(id = "showNotProcessed", subject = "clickListener")
    private fun onShowNotProcessedClick(event: ClickEvent<JmixButton>) {
        loadAppointmentStatus(AppointmentStatus.NotProcessed)
    }

    @Subscribe(id = "showFailed", subject = "clickListener")
    private fun onShowFailedClick(event: ClickEvent<JmixButton>) {
        loadAppointmentStatus(AppointmentStatus.Failed)
    }

    @Subscribe(id = "showProcessed", subject = "clickListener")
    private fun onShowProcessedClick(event: ClickEvent<JmixButton>) {
        loadAppointmentStatus(AppointmentStatus.Processed)
    }

    private fun loadAppointmentStatus(status: AppointmentStatus) {
        with(appointmentsDl) {
            setParameter("appointmentStatus", status.id)
            load()
        }
    }
}