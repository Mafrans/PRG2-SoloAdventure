package me.mafrans.soloadventure.editor;

import javax.swing.*;

public class ImageEditor {
    private JTable table1;
    private JPanel mainPanel;

    private void createUIComponents() {
        table1 = new AsciiEditorTable();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("ImageEditor");
        frame.setContentPane(new ImageEditor().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
