package CHATGUI.Logic.db;

import CHATGUI.Logic.items.RoomItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Заглушка для базы данных не выбранных пользователем комнат
 */
public class UnselectedRoomDB implements ICRUD<RoomItem> {
    private List<RoomItem> rooms;

    private UnselectedRoomDB() {
        rooms = new ArrayList<>();
    }

    public static UnselectedRoomDB getInstance() {
        return SingletonHelper.INSTANCE;
    }

    @Override
    public void create(RoomItem item) {
        if (item != null) {
            item.setPvt(false);
            item.setActive(false);
            item.setCountOfMissingMsgNull();
            item.clearMSGs();
            rooms.add(item);
        }
    }

    @Override
    public void create(String item) {
        create(new RoomItem(item));
    }

    @Override
    public void create(List<RoomItem> list) {
        for (RoomItem roomItem : list) {
            create(roomItem);
        }
    }

    public void create(List<String> list, Boolean flag) {
        for (String s : list) {
            create(s);
        }
    }

    @Override
    public void delete(RoomItem item) {
        rooms.remove(item);
    }

    @Override
    public void delete(String login) {
        for (RoomItem room : rooms) {
            if (room.getLogin().equals(login)) {
                rooms.remove(room);
                break;
            }
        }
    }

    @Override
    public List<RoomItem> read() {
        return rooms;
    }

    @Override
    public List<String> readLogins() {
        List<String> logins = new ArrayList<>();
        for (RoomItem room : rooms) {
            logins.add("*" + room.getLogin());
        }
        return logins;
    }

    @Override
    public RoomItem readFromLogin(String login) {
        RoomItem tmp = null;
        for (RoomItem room : rooms) {
            if (room.getLogin().equals(login)) {
                tmp = room;
                break;
            }
        }
        return tmp;
    }

    private static class SingletonHelper {
        private static final UnselectedRoomDB INSTANCE = new UnselectedRoomDB();
    }
}
