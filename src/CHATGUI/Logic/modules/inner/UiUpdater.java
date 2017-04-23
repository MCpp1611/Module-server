package CHATGUI.Logic.modules.inner;

import CHATGUI.Logic.db.CurrentUserLogin;
import CHATGUI.Logic.db.SelectedRoomDB;
import CHATGUI.Logic.db.UnselectedRoomDB;
import CHATGUI.Logic.db.UserDB;
import CHATGUI.Logic.items.Msg;
import CHATGUI.Logic.items.PropertyBox;
import CHATGUI.Logic.items.RoomItem;
import CHATGUI.uidata.items.ISetTxt;
import CHATGUI.uidata.items.ViewMsgItem;
import CHATGUI.uidata.items.ViewRoomItem;
import CHATGUI.uidata.items.ViewUserItem;
import CHATGUI.uidata.panels.ControlPanel;
import CHATGUI.uidata.panels.ListPanels;
import CHATGUI.uidata.panels.UIFrame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 *  Контроллер графической формы, служит связующим звеном между
 *  логической и графической частями
 *  1) Получает команду на обновление окна пользователя от менеджера
 *  2) Передает команды от графической формы менеджеру
 *  3) Получает информацию от баз данных  и согласно этой информации
 *  обновляет графическую часть
 */
public class UiUpdater {
    // Окно пользователя
    private UIFrame uiFrame;
    // Слушатель событий графической формы
    private ISetTxt listener;
    // Список элементов не выбранных пользователем комнат для общения
    private ListPanels viewUnselectedRooms;
    // Список элементов выбранных пользоавтелем комнат для общения
    private ListPanels viewSelectedRooms;
    // Список отображаемых на данный момент сообщений
    private ListPanels viewMessages;
    // Список пользователей
    private ListPanels viewUsers;

    // Конструктор принимает для инициализации окно
    // пользователя и слушателя событий окна
    public UiUpdater(UIFrame uiFrame, ISetTxt listener) {
        this.uiFrame = uiFrame;
        this.listener = listener;
        initClientForm();
    }

    // Метод инкапсулирует инициализацию формы клиента
    private void initClientForm() {
        System.out.println("------- Client form initialize");
        initItems();
    }

    // Метод инициализирует элементы формы клиента
    private void initItems() {
        viewUnselectedRooms = new ListPanels();
        viewSelectedRooms = new ListPanels();
        viewMessages = new ListPanels();
        viewUsers = new ListPanels();

        JPanel controlPanel;
        String login = CurrentUserLogin.getInstance().getLogin();
        if (!login.equals(PropertyBox.ADMIN)) {
            controlPanel = new ControlPanel(listener);
        } else {
            controlPanel = new JPanel();
        }

        uiFrame = new UIFrame(viewUnselectedRooms, viewSelectedRooms,
                viewMessages, viewUsers, controlPanel);
        uiFrame.add(new JLabel("User: " + login), BorderLayout.NORTH);
    }

    // Возвращаем экземпляр окна пользователя
    public UIFrame getUiFrame() {
        return uiFrame;
    }

    // Обновляет окно пользователя
    public void update() {
        setUnselectedRooms();
        setSelectedRooms();
        setMessages();
        setUsers();
        uiFrame.start();
    }

    // Обновляет список пользователей согласно базы данных
    private void setUsers() {
        UserDB db = UserDB.getInstance();
        List<JPanel> list = new ArrayList<>();
        List<String> logins = db.readLogins();
        for (String login : logins) {
            list.add(new ViewUserItem(login, "", listener));
        }
        viewUsers.removeAll();
        viewUsers.setItemsP(list);
    }

    // Обновляем список видимых сообщений соглавно базы данных
    private void setMessages() {
        SelectedRoomDB db = SelectedRoomDB.getInstance();
        RoomItem room = db.getActiveRoom();
        if (room != null) {
            List<JPanel> list = new ArrayList<>();
            List<Msg> msgs = room.getMessages();
            for (Msg msg : msgs) {
                String login = msg.getUser().getLogin();
                String text = msg.getText();
                list.add(new ViewMsgItem(login, text, listener));
            }
            viewMessages.removeAll();
            viewMessages.setItemsP(list);
        } else {
            viewMessages.removeAll();
            viewMessages.setItemsP(new ArrayList<JPanel>());
        }
    }

    // Обновляем список выбранных пользователем комнат согдасно БД
    private void setSelectedRooms() {
        SelectedRoomDB db = SelectedRoomDB.getInstance();
        List<JPanel> list = new ArrayList<>();
        List<String> logins = db.readLogins();
        List<String> counts = db.readCounts();
        for (int i = 0; i < logins.size(); i++) {
            list.add(new ViewRoomItem(logins.get(i), counts.get(i), listener));
//            System.out.println("!!!!!!!!!!!!!!!!!!" + logins.get(i) + ";" + counts.get(i));
        }
        viewSelectedRooms.removeAll();
        viewSelectedRooms.setItemsP(list);
    }

    // Оюновляем список комнат которых пользователь не выбрал согласно БД
    private void setUnselectedRooms() {
        UnselectedRoomDB db = UnselectedRoomDB.getInstance();
        List<JPanel> list = new ArrayList<>();
        List<String> logins = db.readLogins();
        for (String login : logins) {
            list.add(new ViewRoomItem(login, "", listener));
        }
        viewUnselectedRooms.removeAll();
        viewUnselectedRooms.setItemsP(list);
    }
}
