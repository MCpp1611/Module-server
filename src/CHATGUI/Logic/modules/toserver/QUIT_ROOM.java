package CHATGUI.Logic.modules.toserver;

import CHATGUI.NetworkManager;
import CHATGUI.Logic.db.SelectedRoomDB;
import CHATGUI.Logic.db.UnselectedRoomDB;
import CHATGUI.Logic.items.PropertyBox;
import CHATGUI.Logic.items.RoomItem;
import CHATGUI.Logic.items.PropertyBox.toSrv;
import CHATGUI.Logic.modules.CmdAdaptor;

/**
 * Команда для внешнего источника
 * Сообщает о выходе пользователя комнаты в списке выбранных комнат
 * и проводит соответствующие операции с базами данных
 */
public class QUIT_ROOM implements ISetData {
    public final String NAME = "QUIT_ROOM";

    @Override
    public void toServer(CmdAdaptor listener, String args) {
        System.out.print("------- to server doCommand: " + NAME + "; ");
        SelectedRoomDB selectedRoomDB = SelectedRoomDB.getInstance();
        UnselectedRoomDB unselectedRoomDB = UnselectedRoomDB.getInstance();
        RoomItem room = selectedRoomDB.getActiveRoom();

        if (room != null) {
            if (!room.getLogin().equals(PropertyBox.INFO_ROOM)) {
                selectedRoomDB.delete(room);
                if (!room.getLogin().contains(PropertyBox.PVT_SEP)) {
                    unselectedRoomDB.create(room);
                }
                listener.setMessage(PropertyBox.toSrv.QUIT_ROOM +
                        PropertyBox.ARG_SEP + room);
                NetworkManager.getInstance().send(PropertyBox.toSrv.QUIT_ROOM + PropertyBox.ARG_SEP + room);
            } else {
                room = null;
            }
        }
        System.out.println(room);
    }
}
