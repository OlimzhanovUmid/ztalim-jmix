package uz.tonexus.ztalimcrm.view.group

import com.vaadin.flow.router.Route
import io.jmix.flowui.view.*
import uz.tonexus.ztalimcrm.entity.Group
import uz.tonexus.ztalimcrm.view.main.MainView


@Route(value = "groups", layout = MainView::class)
@ViewController(id = "Group_.list")
@ViewDescriptor(path = "group-list-view.xml")
@LookupComponent("groupsDataGrid")
@DialogMode(width = "64em")
class GroupListView : StandardListView<Group>() {
}