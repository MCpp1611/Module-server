package CHATGUI.Logic.db;

import CHATGUI.Logic.items.PropertyBox;
import CHATGUI.Logic.items.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Заглушка для базы данных списка пользователей
 */
public class UserDB implements ICRUD<User> {
    private List<User> users;

    private UserDB() {
        users = new ArrayList<>();
    }

    public static UserDB getInstance() {
        return SingletonHelper.INSTANCE;
    }

    @Override
    public void create(User item) {
        users.add(item);
    }

    @Override
    public void create(String item) {
        create(new User(item));
    }

    @Override
    public void create(List<User> list) {
        for (User user : list) {
            users.add(user);
        }
    }

    public void create(List<String> list, Boolean flag) {
        for (String s : list) {
            create(s);
        }
    }

    @Override
    public void delete(User item) {
        users.remove(item);
    }

    @Override
    public void delete(String login) {
        for (User user : users) {
            if (user.getLogin().equals(login)) {
                users.remove(user);
                break;
            }
        }
    }

    @Override
    public List<User> read() {
        return users;
    }

    @Override
    public List<String> readLogins() {
        List<String> logins = new ArrayList<>();
        for (User user : users) {
            String login = user.getLogin();
            if (user.isBanned()) {
                login = PropertyBox.BAN + login;
            }
            logins.add(login);
        }
        return logins;
    }

    @Override
    public User readFromLogin(String login) {
        User tmp = null;
        for (User user : users) {
            if (user.getLogin().equals(login)) {
                tmp = user;
                break;
            }
        }
        return tmp;
    }

    private static class SingletonHelper {
        private static final UserDB INSTANCE = new UserDB();
    }
}
