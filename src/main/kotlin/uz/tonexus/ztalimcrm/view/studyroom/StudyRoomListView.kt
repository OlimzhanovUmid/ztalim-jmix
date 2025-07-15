package uz.tonexus.ztalimcrm.view.studyroom

import com.vaadin.flow.router.Route
import io.jmix.flowui.view.*
import uz.tonexus.ztalimcrm.entity.StudyRoom
import uz.tonexus.ztalimcrm.view.main.MainView

@Route(value = "study-rooms", layout = MainView::class)
@ViewController(id = "StudyRoom.list")
@ViewDescriptor(path = "study-room-list-view.xml")
@LookupComponent("studyRoomsDataGrid")
@DialogMode(width = "64em")
class StudyRoomListView : StandardListView<StudyRoom>() {

}