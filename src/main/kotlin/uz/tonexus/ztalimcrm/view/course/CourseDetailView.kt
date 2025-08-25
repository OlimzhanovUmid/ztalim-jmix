package uz.tonexus.ztalimcrm.view.course

import com.vaadin.flow.router.Route
import io.jmix.flowui.view.DialogMode
import io.jmix.flowui.view.EditedEntityContainer
import io.jmix.flowui.view.StandardDetailView
import io.jmix.flowui.view.ViewController
import io.jmix.flowui.view.ViewDescriptor
import uz.tonexus.ztalimcrm.entity.Course
import uz.tonexus.ztalimcrm.view.main.MainView

@Route(value = "courses/:id", layout = MainView::class)
@ViewController(id = "Course.detail")
@ViewDescriptor(path = "course-detail-view.xml")
@EditedEntityContainer("courseDc")
@DialogMode(width = "40em")
class CourseDetailView : StandardDetailView<Course>()