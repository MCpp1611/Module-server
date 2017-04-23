package CHATGUI.Logic.modules.fromserver;

import CHATGUI.Logic.db.CurrentUserLogin;
import CHATGUI.Logic.db.SelectedRoomDB;
import CHATGUI.Logic.items.Msg;
import CHATGUI.Logic.items.RoomItem;
import CHATGUI.Logic.items.User;
import CHATGUI.Logic.modules.CmdTranslator;
import CHATGUI.Logic.modules.ICommand;

import java.util.List;

/**
 * Команда полученная от внешнего источника
 * Получение сообщения для публичной комнаты
 */
public class PUBLIC_MSG implements ICommand {
    public final String NAME = "PUBLIC_MSG";

    @Override
    public void doCommand(String command) {
        System.out.println("------- from server doCommand: " + NAME + "; " + command);
        if (!CurrentUserLogin.getInstance().isAuthorize()) {
            return;
        }

        if (command != null) {
            SelectedRoomDB selectedRoomDB = SelectedRoomDB.getInstance();
            List<String> commands = CmdTranslator.strToList(command);
            RoomItem roomItem = selectedRoomDB.readFromLogin(commands.remove(0));
            roomItem.addMessage(new Msg(new User(commands.remove(0)), commands.remove(0)));
        }
    }
}
