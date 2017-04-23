package CHATGUI.Logic.db;

import CHATGUI.Logic.items.RoomItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Заглушка для базы данных выбранных пользователем комнат
 */
public class SelectedRoomDB implements ICRUD<RoomItem> {

    private List<RoomItem> rooms;

    private SelectedRoomDB() {
        rooms = new ArrayList<>();
    }

    public static SelectedRoomDB getInstance() {
        return SingletonHelper.INSTANCE;
    }

    @Override
    public void create(RoomItem item) {
        if (item != null) {
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
            rooms.add(roomItem);
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
            String login = room.getLogin();
            if (!room.isActive()) {
                login = "*" + login;
            }
            logins.add(login);
        }
        return logins;
    }

    public List<String> readCounts() {
        List<String> counts = new ArrayList<>();
        for (RoomItem room : rooms) {
            String count = room.getCountOMM();
            counts.add(count);
        }
        return counts;
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

    public RoomItem getActiveRoom() {
        RoomItem tmp = null;
        for (RoomItem room : rooms) {
            if (room.isActive()) {
                tmp = room;
                break;
            }
        }
        return tmp;
    }

    public void setActiveRoom(String login) {
        for (RoomItem room : rooms) {
            if (room.getLogin().equals(login)) {
                room.setActive(true);
            } else {
                room.setActive(false);
            }
        }
    }

    private static class SingletonHelper {
        private static final SelectedRoomDB INSTANCE = new SelectedRoomDB();
    }
}
