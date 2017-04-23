package CHATGUI.Logic.modules.toserver;

import CHATGUI.NetworkManager;
import CHATGUI.Logic.items.PropertyBox;
import CHATGUI.Logic.items.PropertyBox.toSrv;
import CHATGUI.Logic.modules.CmdAdaptor;

/**
 * Команда для внешнего источника
 * Сообщает о желании пользователя создать комнату
 */
public class NEW_ROOM implements ISetData {
    public final String NAME = "NEW_ROOM";

    @Override
    public void toServer(CmdAdaptor listener, String args) {
        System.out.println("------- to server doCommand: " + NAME);
        if (args != null) {
            listener.setMessage(PropertyBox.toSrv.NEW_ROOM +
                    PropertyBox.ARG_SEP + args);
            NetworkManager.getInstance().send(toSrv.NEW_ROOM + PropertyBox.ARG_SEP + args);
        }
    }
}
