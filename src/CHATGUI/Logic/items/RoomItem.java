package CHATGUI.Logic.items;

import java.util.ArrayList;
import java.util.List;

/**
 * Сущнсть - комната
 * 1) содержит список сообщений относящихся к этой комнате
 * 2) помогает отслеживать пропущенные сообщения если комната выбрана
 * пользователем, но в данный момент не активна
 */
public class RoomItem {
    private final String login;
    private List<Msg> messages;
    private Boolean pvt;
    private Boolean isActive;
    private int countOMM;

    public RoomItem(String login) {
        this.login = login;
        pvt = false;
        isActive = false;
        messages = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (this == o) return true;

        RoomItem roomItem = (RoomItem) o;

        return login.equals(roomItem.login);
    }

    @Override
    public String toString() {
        return login;
    }

    @Override
    public int hashCode() {
        return login.hashCode();
    }

    public String getLogin() {
        return login;
    }

    public Boolean isPvt() {
        return pvt;
    }

    public void setPvt(Boolean pvt) {
        this.pvt = pvt;
    }

    public Boolean isActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public List<Msg> getMessages() {
        return messages;
    }

    public String getCountOMM() {
        return countOMM == 0 ? "" : countOMM + "";
    }

    public void setCountOfMissingMsgNull() {
        countOMM = 0;
    }

    public void addMessage(Msg msg) {
        messages.add(msg);
        if (isActive) {
            countOMM = 0;
        } else {
            countOMM++;
        }
    }

    public List<String> getLogins() {
        List<String> list = new ArrayList<>();
        for (Msg message : messages) {
            list.add(message.getUser().getLogin());
        }
        return list;
    }

    public List<String> getText() {
        List<String> list = new ArrayList<>();
        for (Msg message : messages) {
            list.add(message.getText());
        }
        return list;
    }

    public void clearMSGs() {
        messages = new ArrayList<>();
    }
}
