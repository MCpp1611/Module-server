package CHATGUI.Logic.modules.fromserver;

import CHATGUI.Logic.db.CurrentUserLogin;
import CHATGUI.Logic.db.SelectedRoomDB;
import CHATGUI.Logic.db.UnselectedRoomDB;
import CHATGUI.Logic.items.PropertyBox;
import CHATGUI.Logic.modules.CmdTranslator;
import CHATGUI.Logic.modules.ICommand;

import java.util.List;

/**
 * Команда полученная от внешнего источника
 * Получение списка не выбранных комнат
 */
public class ROOM_LIST implements ICommand {
    public final String NAME = "ROOM_LIST";

    @Override
    public void doCommand(String command) {
        System.out.println("------- from server doCommand: " + NAME + "; " + command);
        if (!CurrentUserLogin.getInstance().isAuthorize()) {
            return;
        }

        if (command != null) {
            UnselectedRoomDB unselectedRoomDB = UnselectedRoomDB.getInstance();
            SelectedRoomDB selectedRoomDB = SelectedRoomDB.getInstance();
            List<String> commands = CmdTranslator.strToList(command);
            if (commands.remove(PropertyBox.INFO_ROOM)) {
                selectedRoomDB.create(PropertyBox.INFO_ROOM);
                selectedRoomDB.setActiveRoom(PropertyBox.INFO_ROOM);
            }
            unselectedRoomDB.create(commands, null);
        }
    }
}
