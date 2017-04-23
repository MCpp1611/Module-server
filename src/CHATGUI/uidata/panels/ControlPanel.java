package CHATGUI.uidata.panels;

import CHATGUI.Logic.items.PropertyBox;
import CHATGUI.uidata.items.ISetTxt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Нижняя панель с кнопочками
 */
public class ControlPanel extends JPanel {
    private JButton exitRoom;
    private JButton newRoom;
    private JButton send;
    private JTextArea message;

    public ControlPanel(ISetTxt listener) {
        setLayout(new BorderLayout());
        initElements(listener);
        addElements();
    }

    private void addElements() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(newRoom, BorderLayout.WEST);
        panel.add(exitRoom, BorderLayout.CENTER);

        add(panel, BorderLayout.WEST);
        add(message, BorderLayout.CENTER);
        add(send, BorderLayout.EAST);
    }

    private void initElements(final ISetTxt listener) {
        exitRoom = new JButton("EXIT ROOM");
        exitRoom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listener.setText(PropertyBox.inner.QUIT_ROOM.toString());
            }
        });

        newRoom = new JButton("NEW ROOM");
        newRoom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listener.setText(PropertyBox.inner.NEW_ROOM + PropertyBox.ARG_SEP +
                        JOptionPane.showInputDialog(null,
                                "Enter room name",
                                "Enter", JOptionPane.INFORMATION_MESSAGE));
            }
        });

        message = new JTextArea();
        send = new JButton("SEND");
        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listener.setText(PropertyBox.MESSAGE + PropertyBox.ARG_SEP + message.getText());
                message.setText("");
            }
        });
    }
}
