package CHATGUI.Logic.modules.toserver;

import CHATGUI.Logic.modules.ICommand;

/**
 * Команда для внешнего источника
 * Зарезервированная команда о желании пользователя покинуть общение
 */
public class EXIT_USER implements ICommand {
    public final String NAME = "EXIT_USER";
    @Override
    public void doCommand(String command) {
        System.out.println("------- to server doCommand: "+NAME);
        System.out.println("!!!! NO REALIZED !!!");
    }
}
