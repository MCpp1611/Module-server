package CHATGUI.Logic.modules.fromserver;

import CHATGUI.Logic.db.CurrentUserLogin;
import CHATGUI.Logic.db.UserDB;
import CHATGUI.Logic.modules.ICommand;

/**
 * Команда полученная от внешнего источника
 * Удаление указанного пользователя из общего списка пользователей
 */
public class EXIT_USER implements ICommand {
    public final String NAME = "EXIT_USER";

    @Override
    public void doCommand(String command) {
        System.out.println("------- from server doCommand: " + NAME + "; " + command);

        if (!CurrentUserLogin.getInstance().isAuthorize()) {
            return;
        }

        if (command != null) {
            UserDB userDB = UserDB.getInstance();
            userDB.delete(command);
        }
    }
}
