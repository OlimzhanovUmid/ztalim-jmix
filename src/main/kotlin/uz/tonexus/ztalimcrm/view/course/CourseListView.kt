package uz.tonexus.ztalimcrm.view.course

import com.vaadin.flow.router.Route
import io.jmix.flowui.view.*
import uz.tonexus.ztalimcrm.entity.Course
import uz.tonexus.ztalimcrm.view.main.MainView


@Route(value = "courses", layout = MainView::class)
@ViewController(id = "Course.list")
@ViewDescriptor(path = "course-list-view.xml")
@LookupComponent("coursesDataGrid")
@DialogMode(width = "64em")
class CourseListView : StandardListView<Course>() {
}