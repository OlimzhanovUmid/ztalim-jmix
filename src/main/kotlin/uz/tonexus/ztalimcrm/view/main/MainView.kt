package uz.tonexus.ztalimcrm.view.main

import com.vaadin.flow.router.Route
import io.jmix.flowui.app.main.StandardMainView
import io.jmix.flowui.component.checkboxgroup.JmixCheckboxGroup
import io.jmix.flowui.component.grid.DataGrid
import io.jmix.flowui.kit.component.button.JmixButton
import io.jmix.flowui.view.ViewComponent
import io.jmix.flowui.view.ViewController
import io.jmix.flowui.view.ViewDescriptor
import uz.tonexus.ztalimcrm.entity.Appointment

@Route("")
@ViewController(id = "MainView")
@ViewDescriptor(path = "main-view.xml")
open class MainView : StandardMainView() {

    @ViewComponent
    private lateinit var addAppointmentButton: JmixButton
}
