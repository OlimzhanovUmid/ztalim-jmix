package uz.tonexus.ztalimcrm.view.enrollment

import com.vaadin.flow.router.Route
import io.jmix.core.LoadContext
import io.jmix.core.repository.JmixDataRepositoryUtils.buildPageRequest
import io.jmix.core.repository.JmixDataRepositoryUtils.buildRepositoryContext
import io.jmix.flowui.view.*
import io.jmix.flowui.view.Target
import uz.tonexus.ztalimcrm.entity.Enrollment
import uz.tonexus.ztalimcrm.repository.EnrollmentRepository
import uz.tonexus.ztalimcrm.view.main.MainView

@Route(value = "enrollments", layout = MainView::class)
@ViewController(id = "Enrollment.list")
@ViewDescriptor(path = "enrollment-list-view.xml")
@LookupComponent("enrollmentsDataGrid")
@DialogMode(width = "64em")
class EnrollmentListView(private val repository: EnrollmentRepository) : StandardListView<Enrollment>() {

    @Install(to = "enrollmentsDl", target = Target.DATA_LOADER)
    private fun loadDelegate(context: LoadContext<Enrollment>): List<Enrollment> {
        return repository.findAll(buildPageRequest(context), buildRepositoryContext(context)).content
    }

    @Install(to = "enrollmentsDataGrid.removeAction", subject = "delegate")
    private fun enrollmentsDataGridRemoveDelegate(collection: Collection<Enrollment>) {
        repository.deleteAll(collection)
    }
}