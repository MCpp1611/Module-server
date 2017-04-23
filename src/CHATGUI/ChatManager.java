package CHATGUI;

import CHATGUI.Logic.db.CurrentUserLogin;
import CHATGUI.Logic.items.PropertyBox;
import CHATGUI.Logic.items.PropertyBox.inner;
import CHATGUI.Logic.modules.CmdAdaptor;
import CHATGUI.Logic.modules.CmdTranslator;
import CHATGUI.Logic.modules.ICommand;
import CHATGUI.Logic.modules.inner.UiUpdater;
import CHATGUI.Logic.modules.inner.UserAuthenticator;
import CHATGUI.Logic.modules.toserver.*;
import CHATGUI.uidata.items.ISetTxt;
import CHATGUI.uidata.panels.UIFrame;

import java.util.List;

/**
 * Менеджер команд пользовательского интерфейса клиента
 * 1) Запускает поток авторизации пользователя
 * 2) принимает поступающие команды и перенаправляет их соответствующим
 * исполнительным модулям
 * 3) принимает команды от пользовательского графического
 * интерфейса и перенаправляет их соответствующим исполнительным модулям
 * 4) после обработаки поступающих команд дает команды на обновление
 * графичексой части
 */

public class ChatManager extends CmdAdaptor implements ISetTxt {
    // Окно пользователя
    private UIFrame ui;
    // Контроллер графического интерфейса
    private UiUpdater updater;


    private ChatManager() {
        setMessage("");
        authorize();
        ui = null;
    }

    // Менеджер пользователького интерфейса должен быть один,
    // для этого испальзуется паттерн синглтон
    public static ChatManager getInstance() {
        return SingletonHelper.INSTANCE;
    }

    // Метод получает команду от графической части
    // и перенаправляет её исполняющим модулям
    @Override
    public void setText(String text) {
        System.out.println("------- " + text);
        if (text == null) {
            return;
        }
        List<String> command = CmdTranslator.strToList(text);
        inner innerCommand = CmdTranslator.getInner(command.remove(0));
        switch (innerCommand) {
            case ROOM:
                new CONNECT_ROOM().toServer(this,
                        command.remove(0).replace(PropertyBox.OUT_SEP, ""));
                break;
            case NEW_ROOM:
                new NEW_ROOM().toServer(this, command.remove(0));
                break;
            case QUIT_ROOM:
                new QUIT_ROOM().toServer(this, null);
                break;
            case MESSAGE:
                new MESSAGE().toServer(this, command.remove(0));
                break;
            case USER:
                new CONNECT_USER().toServer(this, command.remove(0));
                break;
        }
        updateUi();
    }

    // Метод запускает модуль авторизации в одельном потоке,
    // чтоб сделать авторизацию независимой от основной графической части
    private void authorize() {
        Thread authorize = new Thread(new UserAuthenticator(this));
        authorize.start();
    }

    // Метод принимает команды от внешнего источника комманд
    // и перенаправляет исполняющим модулям
    @Override
    public void doCommand(String command) {
        System.out.println("------- command from server = " + command);

        if (command == null) {
            System.exit(0);
            return;
        }

        try {
            List<String> commands = CmdTranslator.strToList(command);
            String cmdName = commands.remove(0);
            String args = commands.size() == 0 ? "" :
                    CmdTranslator.cmdToStr(commands);

            ICommand module = (ICommand)
                    Class.forName(PropertyBox.COMMAND_PATH_FROM_SRV + cmdName)
                            .newInstance();
            module.doCommand(args);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (CurrentUserLogin.getInstance().isAuthorize()) {
            updateUi();
        }
    }

    // Метод дает команды на инициализацию,
    // а если инициализированна на обновление графической формы
    private void updateUi() {
        if (ui == null) {
            updater = new UiUpdater(ui, this);
            ui = updater.getUiFrame();
        }
        updater.update();
    }

    // Разнофидность ленивой инициализации синглтона
    // с защитой от разрушения многопоточностью без использования синхронизации
    private static class SingletonHelper {
        private static final ChatManager INSTANCE = new ChatManager();
    }
}
