package CHATGUI.Logic.modules.toserver;

import CHATGUI.NetworkManager;
import CHATGUI.Logic.db.SelectedRoomDB;
import CHATGUI.Logic.db.UnselectedRoomDB;
import CHATGUI.Logic.items.PropertyBox;
import CHATGUI.Logic.items.PropertyBox.toSrv;
import CHATGUI.Logic.items.RoomItem;
import CHATGUI.Logic.modules.CmdAdaptor;

/**
 * Команда для внешнего источника
 * Сообщает о выборе пользователем комнаты
 * и проводит соответствующие операции с базами данных
 */
public class CONNECT_ROOM implements ISetData {
    public final String NAME = "CONNECT_ROOM";

    @Override
    public void toServer(CmdAdaptor listener, String args) {
        System.out.println("------- to server doCommand: " + NAME + "; " + args);
        if (args != null) {
            SelectedRoomDB selectedRoomDB = SelectedRoomDB.getInstance();
            UnselectedRoomDB unselectedRoomDB = UnselectedRoomDB.getInstance();
            if (!args.contains(PropertyBox.PVT_SEP)) {
                listener.setMessage(toSrv.CONNECT_ROOM + PropertyBox.ARG_SEP + args);
                NetworkManager.getInstance().send(toSrv.CONNECT_ROOM + PropertyBox.ARG_SEP + args);
            }
            RoomItem room;
            if ((room = unselectedRoomDB.readFromLogin(args)) != null) {
                unselectedRoomDB.delete(room);
                selectedRoomDB.create(room);
                selectedRoomDB.setActiveRoom(room.toString());
            } else if ((room = selectedRoomDB.readFromLogin(args)) != null) {
                selectedRoomDB.setActiveRoom(room.toString());
                room.setCountOfMissingMsgNull();
            }
        }
    }
}
