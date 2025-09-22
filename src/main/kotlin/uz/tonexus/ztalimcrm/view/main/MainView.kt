package uz.tonexus.ztalimcrm.view.main

import com.vaadin.flow.component.ClickEvent
import com.vaadin.flow.router.Route
import io.jmix.flowui.app.main.StandardMainView
import io.jmix.flowui.kit.component.button.JmixButton
import io.jmix.flowui.view.Subscribe
import io.jmix.flowui.view.ViewComponent
import io.jmix.flowui.view.ViewController
import io.jmix.flowui.view.ViewDescriptor

@Route("")
@ViewController(id = "MainView")
@ViewDescriptor(path = "main-view.xml")
open class MainView : StandardMainView() {

    @ViewComponent
    private lateinit var addAppointmentButton: JmixButton

    @Subscribe(id = "addAppointmentButton", subject = "clickListener")
    private fun onAddAppointmentButtonClick(event: ClickEvent<JmixButton>) {
        
    }
}
