package practice;

import javax.swing.*;

public class Scratch {
    public static void main(String[] args) {
        JFrame f = new JFrame("Test");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(400, 300);

        JMenuBar mb = new JMenuBar();
        JMenu m = new JMenu("Menu");
        JMenuItem it = new JMenuItem("Item");
        m.add(it);
        mb.add(m);

        f.setJMenuBar(mb);
        f.setVisible(true);
    }
}

