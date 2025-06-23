package uz.tonexus.ztalimcrm.view.parent

import com.vaadin.flow.router.Route
import io.jmix.flowui.view.*
import uz.tonexus.ztalimcrm.entity.Parent
import uz.tonexus.ztalimcrm.view.main.MainView


@Route(value = "parents", layout = MainView::class)
@ViewController(id = "Parent.list")
@ViewDescriptor(path = "parent-list-view.xml")
@LookupComponent("parentsDataGrid")
@DialogMode(width = "64em")
class ParentListView : StandardListView<Parent>() {
}