package CHATGUI.uidata.panels;

import javax.swing.*;
import java.awt.*;

/**
 * Панель с элементами скписков
 */
public class ListPanels extends JPanel {
    public ListPanels() {
        init();
    }

    public void setItemsP(java.util.List<JPanel> list) {
        for (JPanel panel : list) {
            add(panel);
        }
        if (list.size() < 12) {
            for (int i = 0; i < 12 - list.size(); i++) {
                JPanel fillPanel = new JPanel();
                fillPanel.setBackground(Color.decode("#AFEEEE"));
                add(fillPanel);
            }
        }
    }

    private void init() {
        setBackground(Color.decode("#AFEEEE"));
        setLayout(new GridLayout(0, 1));
    }
}
