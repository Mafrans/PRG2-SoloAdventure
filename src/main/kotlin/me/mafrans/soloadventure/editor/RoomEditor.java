package me.mafrans.soloadventure.editor;

import me.mafrans.soloadventure.models.DBEnemy;

import javax.swing.*;

public class RoomEditor {
    private JTabbedPane tabbedPane1;
    private JPanel mainPanel;
    private JComboBox<AsciiColor> roomColorComboBox;
    private JTextArea roomTextArea;
    private JButton editImageButton;
    private JButton addEnemyButton;
    private JButton addItemButton;
    private JButton saveButton;
    private JButton addInspectionButton;
    private JPanel itemContainer;
    private JPanel enemyContainer;
    private JPanel inspectionContainer;
    private JPanel roomImageWrapper;

    private void createUIComponents() {
        roomColorComboBox = new JComboBox<>(AsciiColor.values());
    }

    public RoomEditor(DBEnemy enemy) {
        JFrame frame = new JFrame("Room Editor");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
