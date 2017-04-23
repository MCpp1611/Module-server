package CHATGUI.Logic.db;

/**
 * Логин пользователя выведен в отдельный класс
 * для возможности более гибкой работы с ним
 */
public class CurrentUserLogin {
    private String login;
    private Boolean isAuthorize;

    // приватный конструктор для синглтона
    private CurrentUserLogin() {
        login = "";
        isAuthorize = false;
    }

    // возвращает екземпляр синглтона
    public static CurrentUserLogin getInstance() {
        return SingletonHelper.INSTANCE;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Boolean isAuthorize() {
        return isAuthorize;
    }

    public void setAuthorize(Boolean authorize) {
        isAuthorize = authorize;
    }

    // разновидность ленивой инициализации с защитой от многопоточности
    private static class SingletonHelper {
        private static final CurrentUserLogin INSTANCE = new CurrentUserLogin();
    }
}
