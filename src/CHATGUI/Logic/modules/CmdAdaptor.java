package CHATGUI.Logic.modules;

/**
 * Абстрактный класс который описывает договоренность обмена информацией между
 * Пользовательским интерфейсом и внешней программой
 */
public abstract class CmdAdaptor implements ICommand {
    private String message;

    protected CmdAdaptor() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public abstract void doCommand(String command);
}
