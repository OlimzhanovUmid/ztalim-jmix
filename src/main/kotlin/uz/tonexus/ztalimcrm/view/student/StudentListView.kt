package uz.tonexus.ztalimcrm.view.student

import com.vaadin.flow.router.Route
import io.jmix.flowui.view.*
import uz.tonexus.ztalimcrm.entity.Student
import uz.tonexus.ztalimcrm.view.main.MainView


@Route(value = "students", layout = MainView::class)
@ViewController(id = "Student.list")
@ViewDescriptor(path = "student-list-view.xml")
@LookupComponent("studentsDataGrid")
@DialogMode(width = "64em")
class StudentListView : StandardListView<Student>() {
}