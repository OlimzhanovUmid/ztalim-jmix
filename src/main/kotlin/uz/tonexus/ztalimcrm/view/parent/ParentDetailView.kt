package uz.tonexus.ztalimcrm.view.parent

import com.vaadin.flow.router.Route
import io.jmix.flowui.view.EditedEntityContainer
import io.jmix.flowui.view.StandardDetailView
import io.jmix.flowui.view.ViewController
import io.jmix.flowui.view.ViewDescriptor
import uz.tonexus.ztalimcrm.entity.Parent
import uz.tonexus.ztalimcrm.view.main.MainView

@Route(value = "parents/:id", layout = MainView::class)
@ViewController(id = "Parent.detail")
@ViewDescriptor(path = "parent-detail-view.xml")
@EditedEntityContainer("parentDc")
class ParentDetailView : StandardDetailView<Parent>() {
}