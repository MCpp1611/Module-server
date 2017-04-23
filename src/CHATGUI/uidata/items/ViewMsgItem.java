package CHATGUI.uidata.items;

import CHATGUI.Logic.items.PropertyBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Графический аналог сущности - Сообщение
 */
public class ViewMsgItem extends JPanel {
    private final String login;
    private final ISetTxt listener;
    private JButton btnPerson;
    private JTextArea txtMsg;

    public ViewMsgItem(String login, String message, ISetTxt listener) {
        setLayout(new BorderLayout());
        this.login = login;
        initButton();
        initTxt(message);
        addItems();
        this.listener = listener;
        setBackground(PropertyBox.LightBlue);
    }

    private void addItems() {
        add(btnPerson, BorderLayout.WEST);
        add(txtMsg, BorderLayout.CENTER);
    }

    private void initTxt(String message) {
        StringBuilder stringBuilder = new StringBuilder(message);
        StringBuilder sb = new StringBuilder();
        int limitSize = 150;

        if (stringBuilder.length() > limitSize) {
            int count = 0;
            while (stringBuilder.length() > limitSize) {
                sb.append(stringBuilder.subSequence(0, limitSize)).append("\n");
                stringBuilder.delete(0, limitSize);
                if (count > 0) {
                    sb.append(".... To long string, your length: " + message.length() + "" +
                            " max. length:" + limitSize * 2).append("\n");
                    sb.insert(0, "\n");
                    txtMsg = new JTextArea(sb.toString());
                    txtMsg.setBackground(PropertyBox.LightBlue);
                    txtMsg.setBorder(BorderFactory.createEmptyBorder());
                    txtMsg.setEditable(false);
                    return;
                }
                count++;
            }
        }

        sb.append(stringBuilder);

        sb.insert(0, "\n");

        txtMsg = new JTextArea(sb.toString());
        txtMsg.setBackground(PropertyBox.LightBlue);
        txtMsg.setBorder(BorderFactory.createEmptyBorder());
        txtMsg.setEditable(false);
    }

    private void initButton() {
        btnPerson = new JButton(" " + login + " ");

        btnPerson.setForeground(PropertyBox.D_ORANGE);
        btnPerson.setBackground(PropertyBox.WHITE);
        btnPerson.setBorder(PropertyBox.EMPTY);
        btnPerson.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listener.setText(PropertyBox.USER + PropertyBox.ARG_SEP + login);
            }
        });
    }
}
