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

    public ImagePreviewPanel imagePreviewPanel;

    private void createUIComponents() {
        roomColorComboBox = new JComboBox<>(AsciiColor.values());

        imagePreviewPanel = new ImagePreviewPanel(null);
        roomImageWrapper = new JPanel();
        roomImageWrapper.add(imagePreviewPanel);
    }

    public RoomEditor() {
        JFrame frame = new JFrame("Room Editor");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
