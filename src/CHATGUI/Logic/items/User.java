package CHATGUI.Logic.items;

/**
 * Сущнсть - пользователь
 */
public class User {
    private final String login;
    private Boolean isBanned = false;

    public User(String login) {
        this.login = login;
    }

    @Override
    public String toString() {
        return login;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return login.equals(user.login);
    }

    @Override
    public int hashCode() {
        return login.hashCode();
    }

    public Boolean isBanned() {
        return isBanned;
    }

    public void setBanned(Boolean isBanned) {
        this.isBanned = isBanned;
    }

    public String getLogin() {
        return login;
    }
}
