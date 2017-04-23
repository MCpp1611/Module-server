package CHATGUI.Logic.modules.toserver;

import CHATGUI.Logic.modules.ICommand;

/**
 * Команда для внешнего источника
 * Зарезерфированная команда для бана юзера
 */
public class BAN implements ICommand {
    public final String NAME = "BAN";
    @Override
    public void doCommand(String command) {
        System.out.println("------- to server doCommand: "+NAME);
        System.out.println("!!!! NO REALIZED !!!");
    }
}
