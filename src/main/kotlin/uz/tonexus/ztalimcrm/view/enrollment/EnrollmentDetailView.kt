package uz.tonexus.ztalimcrm.view.enrollment

import com.vaadin.flow.router.Route
import io.jmix.core.LoadContext
import io.jmix.core.SaveContext
import io.jmix.core.repository.JmixDataRepositoryUtils.extractEntityId
import io.jmix.flowui.view.*
import io.jmix.flowui.view.Target
import uz.tonexus.ztalimcrm.entity.Enrollment
import uz.tonexus.ztalimcrm.repository.EnrollmentRepository
import uz.tonexus.ztalimcrm.view.main.MainView

@Route(value = "enrollments/:id", layout = MainView::class)
@ViewController(id = "Enrollment.detail")
@ViewDescriptor(path = "enrollment-detail-view.xml")
@EditedEntityContainer("enrollmentDc")
class EnrollmentDetailView(private val repository: EnrollmentRepository) : StandardDetailView<Enrollment>() {

    @Install(target = Target.DATA_CONTEXT)
    private fun saveDelegate(saveContext: SaveContext): Set<Any> {
        return mutableSetOf(repository.save(editedEntity))
    }

    @Install(to = "enrollmentDl", target = Target.DATA_LOADER)
    private fun loadDelegate(context: LoadContext<Enrollment>): Enrollment {
        return repository.getById(extractEntityId(context), context.fetchPlan)
    }
}