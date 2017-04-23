package CHATGUI.uidata.panels;

import CHATGUI.ChatManager;
import CHATGUI.Logic.items.PropertyBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Пользовательское окно
 */
public class UIFrame extends JFrame {
    // Ссылка на контейнер списка не выбранных комнат
    private ScrollPanel unSelRooms;
    // Ссылка на контейнер списка выбранных комнат
    private ScrollPanel selRooms;
    // Ссылка на контейнер списка сообщений активной комнаты
    private ScrollPanel messages;
    // Ссылка на контейнер списка пользователей
    private ScrollPanel users;

    // Ссылка на список не выбранных комнат
    private ListPanels unselectedRooms;
    // Ссылка на список выбранных комнат
    private ListPanels selectedRooms;
    // Ссылка на список сообщений
    private ListPanels messageList;
    // Ссылка на список пользователей
    private ListPanels userList;
    // Ссылка на нижнюю панель с кнопочками
    private JPanel controls;

    public UIFrame(ListPanels unselectedRooms, ListPanels selectedRooms,
                   ListPanels messageList, ListPanels userList,
                   JPanel panel) throws HeadlessException {
        initLists(unselectedRooms, selectedRooms, messageList, userList, panel);
        initScrolls();
        initFrame();
    }

    // Инициализация эдементов списков
    private void initLists(ListPanels unselectedRooms,
                           ListPanels selectedRooms, ListPanels messageList,
                           ListPanels userList, JPanel panel) {
        this.unselectedRooms = unselectedRooms;
        this.selectedRooms = selectedRooms;
        this.messageList = messageList;
        this.userList = userList;
        controls = panel;
    }

    // Инициализация прокручивающихся контейнеров списков
    private void initScrolls() {
        unSelRooms = new ScrollPanel(unselectedRooms, " Unselected rooms:  ");
        selRooms = new ScrollPanel(selectedRooms, " Selected rooms:  ");
        messages = new ScrollPanel(messageList, " MESSAGES:               ");
        users = new ScrollPanel(userList, " USERS:                ");
    }

    // Инициализация окна
    private void initFrame() {
        setTitle("CHAT");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(0, 0, 800, 600);
        setLocationRelativeTo(null);


        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(unSelRooms, BorderLayout.WEST);
        panel.add(selRooms, BorderLayout.CENTER);
        add(panel, BorderLayout.WEST);
        add(messages, BorderLayout.CENTER);
        add(users, BorderLayout.EAST);
        add(controls, BorderLayout.SOUTH);
        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                ChatManager.getInstance().setMessage(PropertyBox.toSrv.EXIT.toString());
                System.out.println(PropertyBox.toSrv.EXIT);
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
    }

    public void start() {
        setVisible(true);
        repaint();
    }

    public void stop() {
        setVisible(false);
        dispose();
    }
}

