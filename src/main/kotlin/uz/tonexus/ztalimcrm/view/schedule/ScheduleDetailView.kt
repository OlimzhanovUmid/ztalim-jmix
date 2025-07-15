package uz.tonexus.ztalimcrm.view.schedule

import com.vaadin.flow.router.Route
import io.jmix.flowui.view.EditedEntityContainer
import io.jmix.flowui.view.StandardDetailView
import io.jmix.flowui.view.ViewController
import io.jmix.flowui.view.ViewDescriptor
import uz.tonexus.ztalimcrm.entity.Schedule
import uz.tonexus.ztalimcrm.view.main.MainView

@Route(value = "schedules/:id", layout = MainView::class)
@ViewController(id = "Schedule.detail")
@ViewDescriptor(path = "schedule-detail-view.xml")
@EditedEntityContainer("scheduleDc")
class ScheduleDetailView : StandardDetailView<Schedule>() {
}