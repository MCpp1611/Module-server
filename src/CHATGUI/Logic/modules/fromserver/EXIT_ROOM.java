package CHATGUI.Logic.modules.fromserver;

import CHATGUI.Logic.db.CurrentUserLogin;
import CHATGUI.Logic.db.SelectedRoomDB;
import CHATGUI.Logic.items.PropertyBox;
import CHATGUI.Logic.modules.ICommand;

/**
 * Команда полученная от внешнего источника
 * Удаление указанной комнаты из списка выбранных пользователем для общения
 */
public class EXIT_ROOM implements ICommand {
    public final String NAME = "EXIT_ROOM";

    @Override
    public void doCommand(String command) {
        System.out.print("------- from server doCommand: " + NAME + "; " + command);
        SelectedRoomDB selectedRoomDB = SelectedRoomDB.getInstance();
        if (!CurrentUserLogin.getInstance().isAuthorize()) {
            return;
        }

        if (command.contains(PropertyBox.PVT_SEP)) {
            selectedRoomDB.delete(command);
        }
    }
}
