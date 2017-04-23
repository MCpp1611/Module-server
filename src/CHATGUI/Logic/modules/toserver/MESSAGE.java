package CHATGUI.Logic.modules.toserver;

import CHATGUI.NetworkManager;
import CHATGUI.Logic.db.SelectedRoomDB;
import CHATGUI.Logic.items.PropertyBox;
import CHATGUI.Logic.items.PropertyBox.toSrv;
import CHATGUI.Logic.items.RoomItem;
import CHATGUI.Logic.modules.CmdAdaptor;

/**
 * Команда для внешнего источника
 * Желании пользователя отправить сообщение
 * и проводит соответствующие операции с базами данных
 */
public class MESSAGE implements ISetData {
    public final String NAME = "MESSAGE";

    @Override
    public void toServer(CmdAdaptor listener, String args) {
        System.out.print("------- to server doCommand: " + NAME + "; " + args + "; ");
        if (args != null) {
            SelectedRoomDB db = SelectedRoomDB.getInstance();
            RoomItem room = db.getActiveRoom();
            if (room != null) {
                if (room.getLogin().contains(PropertyBox.PVT_SEP)) {
                    listener.setMessage(toSrv.PRIVATE_MSG + PropertyBox.ARG_SEP
                            + room + PropertyBox.ARG_SEP + args);
                    System.out.println(room);
                } else {
                    listener.setMessage(toSrv.PUBLIC_MSG + PropertyBox.ARG_SEP
                            + room + PropertyBox.ARG_SEP + args);
                    NetworkManager.getInstance().send(toSrv.PUBLIC_MSG + PropertyBox.ARG_SEP + room + PropertyBox.ARG_SEP + args);
                    System.out.println(room);
                }
            } else {
                System.out.println("not selected room");
            }
        }
    }
}
