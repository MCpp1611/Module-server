package CHATGUI.Logic.modules.fromserver;

import CHATGUI.Logic.db.CurrentUserLogin;
import CHATGUI.Logic.db.SelectedRoomDB;
import CHATGUI.Logic.db.UnselectedRoomDB;
import CHATGUI.Logic.items.PropertyBox;
import CHATGUI.Logic.modules.ICommand;

/**
 * Команда полученная от внешнего источника
 * Добавление новой комнаты в список не выбранных комнат
 */
public class NEW_ROOM implements ICommand {
    public final String NAME = "NEW_ROOM";

    @Override
    public void doCommand(String command) {
        System.out.println("------- from server doCommand: " + NAME + "; " + command);

        if (!CurrentUserLogin.getInstance().isAuthorize()) {
            return;
        }

        if (command != null) {
            UnselectedRoomDB unselectedRoomDB = UnselectedRoomDB.getInstance();
            SelectedRoomDB selectedRoomDB = SelectedRoomDB.getInstance();
            if (command.equals(PropertyBox.INFO_ROOM)) {
                selectedRoomDB.create(command);
                selectedRoomDB.setActiveRoom(command);
            } else if (command.contains(PropertyBox.PVT_SEP)) {
                selectedRoomDB.create(command);
            } else {
                unselectedRoomDB.create(command);
            }
        }
    }
}
