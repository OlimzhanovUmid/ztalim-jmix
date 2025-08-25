package uz.tonexus.ztalimcrm.view.group

import com.vaadin.flow.router.Route
import io.jmix.flowui.view.DialogMode
import io.jmix.flowui.view.EditedEntityContainer
import io.jmix.flowui.view.StandardDetailView
import io.jmix.flowui.view.ViewController
import io.jmix.flowui.view.ViewDescriptor
import uz.tonexus.ztalimcrm.entity.Group
import uz.tonexus.ztalimcrm.view.main.MainView

@Route(value = "groups/:id", layout = MainView::class)
@ViewController(id = "Group_.detail")
@ViewDescriptor(path = "group-detail-view.xml")
@EditedEntityContainer("groupDc")
@DialogMode(width = "40em")
class GroupDetailView : StandardDetailView<Group>()