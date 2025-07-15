package uz.tonexus.ztalimcrm.view.studyroom

import com.vaadin.flow.router.Route
import io.jmix.flowui.view.EditedEntityContainer
import io.jmix.flowui.view.StandardDetailView
import io.jmix.flowui.view.ViewController
import io.jmix.flowui.view.ViewDescriptor
import uz.tonexus.ztalimcrm.entity.StudyRoom
import uz.tonexus.ztalimcrm.view.main.MainView

@Route(value = "study-rooms/:id", layout = MainView::class)
@ViewController(id = "StudyRoom.detail")
@ViewDescriptor(path = "study-room-detail-view.xml")
@EditedEntityContainer("studyRoomDc")
class StudyRoomDetailView : StandardDetailView<StudyRoom>() {
}