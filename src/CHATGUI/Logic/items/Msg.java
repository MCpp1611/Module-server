package CHATGUI.Logic.items;

/**
 * Сущнсть - сообщение
 * содержит ссылку на отправившего его пользователя
 * и тело сообщения
 */
public class Msg {
    private final User user;
    private final String text;

    public Msg(User user, String text) {
        this.user = user;
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public String getText() {
        return text;
    }
}
