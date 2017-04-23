package CHATGUI.Logic.modules.toserver;

import CHATGUI.Logic.modules.CmdAdaptor;
import CHATGUI.Logic.items.PropertyBox.toSrv;

import static CHATGUI.Logic.items.PropertyBox.ARG_SEP;

import CHATGUI.NetworkManager;
import CHATGUI.Logic.items.PropertyBox;

/**
 * Команда для внешнего источника
 * Сообщает о желании авторизоваться/зарегистрироваться
 */
public class NEW_USER implements ISetData {
    public final String NAME = "NEW_USER";

    @Override
    public void toServer(CmdAdaptor listener, String args) {
        System.out.println("------- to server doCommand: " + NAME + ";" + args);
        if (args != null) {
            listener.setMessage(toSrv.NEW_USER + ARG_SEP + args);
//            NetworkManager.getInstance().send(toSrv.CONNECT_ROOM + PropertyBox.ARG_SEP + args);
            NetworkManager.getInstance().send(toSrv.NEW_USER + PropertyBox.ARG_SEP + args);
        }
    }
}
