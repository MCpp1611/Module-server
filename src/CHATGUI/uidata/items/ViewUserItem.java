package CHATGUI.uidata.items;

import CHATGUI.Logic.items.PropertyBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Графический аналог сущности - Пользователь
 */
public class ViewUserItem extends JPanel {
    private final String login;
    private final ISetTxt listener;
    private JButton btnPerson;
    private JTextArea txtMsg;

    public ViewUserItem(String login, String message, ISetTxt listener) {
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
        btnPerson.setForeground(PropertyBox.DarkOliveGreen);
        if (!login.contains(PropertyBox.BAN)) {
            btnPerson.setBackground(Color.WHITE);
        } else {
            btnPerson.setBackground(PropertyBox.LightSalmon);
        }
        btnPerson.setBorder(PropertyBox.DEFAULT);
        btnPerson.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listener.setText(PropertyBox.USER + PropertyBox.ARG_SEP + login);
            }
        });
    }
}
