package uz.tonexus.ztalimcrm.view.schedule

import com.vaadin.flow.router.Route
import io.jmix.flowui.view.*
import uz.tonexus.ztalimcrm.entity.LessonSchedule
import uz.tonexus.ztalimcrm.view.main.MainView


@Route(value = "schedules", layout = MainView::class)
@ViewController(id = "Schedule.list")
@ViewDescriptor(path = "schedule-list-view.xml")
@LookupComponent("schedulesDataGrid")
@DialogMode(width = "64em")
class ScheduleListView : StandardListView<LessonSchedule>() {
}