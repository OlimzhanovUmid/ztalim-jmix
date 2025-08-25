package uz.tonexus.ztalimcrm.view.student

import com.vaadin.flow.router.Route
import io.jmix.flowui.view.DialogMode
import io.jmix.flowui.view.EditedEntityContainer
import io.jmix.flowui.view.StandardDetailView
import io.jmix.flowui.view.ViewController
import io.jmix.flowui.view.ViewDescriptor
import uz.tonexus.ztalimcrm.entity.Student
import uz.tonexus.ztalimcrm.view.main.MainView

@Route(value = "students/:id", layout = MainView::class)
@ViewController(id = "Student.detail")
@ViewDescriptor(path = "student-detail-view.xml")
@EditedEntityContainer("studentDc")
@DialogMode(width = "40em")
class StudentDetailView : StandardDetailView<Student>()