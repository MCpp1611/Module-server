package CHATGUI.Logic.modules.toserver;

import CHATGUI.Logic.modules.ICommand;

/**
 * Команда для внешнего источника
 * Зарезервированная команда для разбана пользователя
 */
public class UNBAN implements ICommand {
    public final String NAME = "UNBAN";
    @Override
    public void doCommand(String command) {
        System.out.println("------- to server doCommand: "+NAME);
        System.out.println("!!!! NO REALIZED !!!");
    }
}
