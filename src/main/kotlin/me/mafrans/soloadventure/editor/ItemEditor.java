package me.mafrans.soloadventure.editor;

import me.mafrans.soloadventure.models.DBEnemy;
import me.mafrans.soloadventure.models.DBItem;

import javax.swing.*;

public class ItemEditor {
    private JButton saveButton;
    private JTextField nameField;
    private JTextArea descriptionArea;
    private JTabbedPane tabbedPane1;
    private JCheckBox isWeaponCheckBox;
    private JList messageList;
    private JSpinner attackDamageSpinner;
    private JComboBox colorComboBox;
    private JButton newAttackMessageButton;
    private JList tagList;
    private JButton newTagButton;
    private JPanel mainPanel;

    public DBItem item;

    private void createUIComponents() {
        colorComboBox = new JComboBox(AsciiColor.values());
    }

    public ItemEditor(DBItem item) {
        this.item = item;
        if (item == null) {
            this.item = new DBItem();
        }

        JFrame frame = new JFrame("ItemEditor");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        saveButton.addActionListener(e -> save());
    }

    public void save() {
    }
}
