package CHATGUI.uidata.items;

import CHATGUI.Logic.items.PropertyBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Графический аналог сущности - Комната
 */
public class ViewRoomItem extends JPanel {
    private final String login;
    private final ISetTxt listener;
    private JButton btnPerson;
    private JTextArea txtMsg;

    public ViewRoomItem(String login, String message, ISetTxt listener) {
        setLayout(new BorderLayout());
        this.login = login;
        initButton();
        initTxt(message);
        add(btnPerson, BorderLayout.CENTER);
        add(txtMsg, BorderLayout.WEST);
        this.listener = listener;
        setBackground(Color.WHITE);
    }

    private void initTxt(String message) {
        txtMsg = new JTextArea(message);
        txtMsg.setBorder(BorderFactory.createEmptyBorder());
    }

    private void initButton() {
        this.btnPerson = new JButton(login);
        btnPerson.setForeground(PropertyBox.BlueViolet);
        btnPerson.setBackground(Color.WHITE);
        if (login.contains(PropertyBox.OUT_SEP)) {
            btnPerson.setBackground(PropertyBox.Gray);
            btnPerson.setForeground(PropertyBox.WHITE);
        }
        if (login.contains(PropertyBox.PVT_SEP)) {
            btnPerson.setForeground(PropertyBox.Fuchsia);
        }
        btnPerson.setBorder(PropertyBox.DEFAULT);
        btnPerson.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listener.setText(PropertyBox.ROOM + PropertyBox.ARG_SEP + login);
            }
        });
    }
}
