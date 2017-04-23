package CHATGUI.Logic.modules.fromserver;

import CHATGUI.Logic.modules.ICommand;
import CHATGUI.Logic.db.CurrentUserLogin;

/**
 * Команда полученная от внешнего источника
 * Подтверждает авторизацию клиента
 */
public class EXIST implements ICommand {
    public final String NAME = "EXIST";

    @Override
    public void doCommand(String command) {
        System.out.println("------- from server doCommand: " + NAME + "; argument=" + command);
        if (command != null) {
            CurrentUserLogin login = CurrentUserLogin.getInstance();

            if (command != null && !command.isEmpty()) {
                login.setLogin(command);
                login.setAuthorize(true);
            } else {
                login.setLogin("");
                login.setAuthorize(false);
            }
        }
    }
}
