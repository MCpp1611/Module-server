package CHATGUI.Logic.modules.inner;

import CHATGUI.ChatManager;
import CHATGUI.Logic.db.CurrentUserLogin;
import CHATGUI.Logic.items.PropertyBox;
import CHATGUI.Logic.modules.toserver.NEW_USER;

import javax.swing.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Модуль регистрации/авторизации пользователя
 */

public class UserAuthenticator implements Runnable {
    volatile ChatManager manager;

    // Принимает ссылку на менеджера для дальнейшего обмена команд авторизации
    public UserAuthenticator(ChatManager manager) {
        this.manager = manager;
    }

    // В отдельном потоке запускает диалоговые окна авторизации/регистрации
    @Override
    public void run() {
        authorize("Enter User name");
        while (!CurrentUserLogin.getInstance().isAuthorize()) {
            pause();
            if (!CurrentUserLogin.getInstance().isAuthorize()) {
                authorize("Login is not correct, please reenter it.");
            }
        }
        System.out.println("------- Authorization complete, login is: " +
                CurrentUserLogin.getInstance().getLogin());
    }

    // Время ожидания/формирования ответа от поставщика услуг авторизации
    private void pause() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Запрос на авторизацию
    private String authorize(String msg) {
        String login = getLogin(msg);
        new NEW_USER().toServer(manager, login);
        return login;
    }

    // Получение регистрационной информации от пользователя
    private String getLogin(String msg) {
        System.out.println("------- Enter login dialog start");
        String tmpLogin;
        if ((tmpLogin = JOptionPane.showInputDialog(null,
                msg,
                "Enter", JOptionPane.INFORMATION_MESSAGE)) == null) {
            System.exit(0);
        }
        while (!isCorrectLogin(tmpLogin)) {
            if ((tmpLogin = JOptionPane.showInputDialog(null,
                    "Login is not correct, please reenter it.",
                    "Enter", JOptionPane.INFORMATION_MESSAGE)) == null) {
                System.exit(0);
            }
        }
        return tmpLogin;
    }

    // Проверка корректности введенной информации
    private boolean isCorrectLogin(String tmpLogin) {
        Pattern p = Pattern.compile("^[A-Za-z0-9]" +
                "{" + PropertyBox.MIN_LOGIN_SIZE + "," + PropertyBox.MAX_LOGIN_SIZE + "}$");
        Matcher m = p.matcher(tmpLogin);
        return m.matches();
    }
}
