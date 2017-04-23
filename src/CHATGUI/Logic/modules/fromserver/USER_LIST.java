package CHATGUI.Logic.modules.fromserver;

import CHATGUI.Logic.db.CurrentUserLogin;
import CHATGUI.Logic.db.UserDB;
import CHATGUI.Logic.modules.CmdTranslator;
import CHATGUI.Logic.modules.ICommand;

import java.util.List;

/**
 * Команда полученная от внешнего источника
 * Получение списка пользователей
 */
public class USER_LIST implements ICommand {
    public final String NAME = "USER_LIST";

    @Override
    public void doCommand(String command) {
        System.out.println("------- from server doCommand: " + NAME + "; " + command);
        if (!CurrentUserLogin.getInstance().isAuthorize()) {
            return;
        }

        if (command != null && !command.isEmpty()) {
            UserDB userDB = UserDB.getInstance();
            List<String> commands = CmdTranslator.strToList(command);
            userDB.create(commands, null);
        }
    }
}
