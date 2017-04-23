package CHATGUI.Logic.modules;

/**
 * Интерфейс описывающий способ получение команд от внешнего источника
 */
public interface ICommand {
    void doCommand(String command);
}
