package uz.tonexus.ztalimcrm.view.main

import com.vaadin.flow.component.ClickEvent
import com.vaadin.flow.router.Route
import io.jmix.flowui.ViewNavigators
import io.jmix.flowui.app.main.StandardMainView
import io.jmix.flowui.kit.component.button.JmixButton
import io.jmix.flowui.view.Subscribe
import io.jmix.flowui.view.ViewComponent
import io.jmix.flowui.view.ViewController
import io.jmix.flowui.view.ViewDescriptor
import org.springframework.beans.factory.annotation.Autowired
import uz.tonexus.ztalimcrm.entity.Appointment

@Route("")
@ViewController(id = "MainView")
@ViewDescriptor(path = "main-view.xml")
open class MainView : StandardMainView() {
    @Autowired
    private lateinit var viewNavigators: ViewNavigators

    @ViewComponent
    private lateinit var addAppointmentButton: JmixButton

    @Subscribe(id = "addAppointmentButton", subject = "clickListener")
    private fun onAddAppointmentButtonClick(event: ClickEvent<JmixButton>) {
        viewNavigators.detailView(this, Appointment::class.java)
            .newEntity()
            .navigate()
    }
}
