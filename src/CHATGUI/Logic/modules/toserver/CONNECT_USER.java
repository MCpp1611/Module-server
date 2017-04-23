package CHATGUI.Logic.modules.toserver;

import CHATGUI.NetworkManager;
import CHATGUI.Logic.items.PropertyBox;
import CHATGUI.Logic.items.PropertyBox.toSrv;
import CHATGUI.Logic.modules.CmdAdaptor;

/**
 * Команда для внешнего источника
 * Сообщает о желании текущего пользователя организовать приватный чат с выбранным
 */
public class CONNECT_USER implements ISetData {
    public final String NAME = "CONNECT_USER";

    @Override
    public void toServer(CmdAdaptor listener, String args) {
        System.out.println("------- to server doCommand: " + NAME + "; " + args);
        if (args != null) {
            listener.setMessage(toSrv.CONNECT_USER + PropertyBox.ARG_SEP + args);
            NetworkManager.getInstance().send(toSrv.CONNECT_USER + PropertyBox.ARG_SEP + args);
        }
    }
}
