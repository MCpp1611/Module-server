package CHATGUI.uidata.panels;

import CHATGUI.Logic.items.PropertyBox;

import javax.swing.*;
import java.awt.*;

/**
 * Контейнер с прокруткой для панелей с элементами списков
 */
class ScrollPanel extends JPanel {

    public ScrollPanel(JPanel listPanels, String name) {
        setLayout(new BorderLayout());
        JLabel name1 = new JLabel(name);
        JScrollPane scrollPane = new JScrollPane(listPanels);
        add(name1, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        setBorder(PropertyBox.EMPTY);
        scrollPane.setBackground(Color.decode("#AFEEEE"));
        listPanels.setBackground(Color.decode("#AFEEEE"));
        setBackground(Color.decode("#AFEEEE"));
    }
}
