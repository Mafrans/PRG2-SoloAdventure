package me.mafrans.soloadventure.editor;

import me.mafrans.soloadventure.models.DBEnemy;
import me.mafrans.soloadventure.models.DBItem;
import me.mafrans.soloadventure.models.DBWeapon;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ItemEditor {
    private JButton saveButton;
    private JTextField nameField;
    private JTextArea descriptionArea;
    private JTabbedPane tabbedPane1;
    private JCheckBox isWeaponCheckBox;
    private JSpinner attackDamageSpinner;
    private JComboBox colorComboBox;
    private JPanel mainPanel;
    private JPanel weaponEditPanel;
    private JSpinner attackVarianceSpinner;
    private JSpinner critPercentSpinner;
    private JTable tagTable;
    private JTable attackMessageTable;

    private ItemSaveRunnable saveListener;
    public DBItem item;

    private void createUIComponents() {
        colorComboBox = new JComboBox(AsciiColor.values());
        tagTable = new JTable(8, 1);
        attackMessageTable = new JTable(8, 1);
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

        weaponEditPanel.setVisible(isWeaponCheckBox.isSelected());
        isWeaponCheckBox.addItemListener(e -> weaponEditPanel.setVisible(isWeaponCheckBox.isSelected()));
    }

    public List<Object> getTableAsList(JTable table) {
        TableModel model = table.getModel();
        List<Object> values = new ArrayList<>();
        for(int i = 0; i < model.getRowCount(); i++) {
            Object value = model.getValueAt(i, 0);
            if (value != null) {
                values.add(value);
            }
        }
        return values;
    }

    public void setListToTable(JTable table, List<Object> values) {
        TableModel model = table.getModel();
        for (int i = 0; i < values.size(); i++) {
            model.setValueAt(values.get(i), i, 0);
        }
    }

    public void update() {
        if(this.item != null) {
            nameField.setText(this.item.name);
            descriptionArea.setText(this.item.description);
            colorComboBox.setSelectedItem(AsciiColor.Companion.fromFG(this.item.color));
            setListToTable(tagTable, Arrays.asList(this.item.tags));
            isWeaponCheckBox.setSelected(this.item.isWeapon);

            setListToTable(attackMessageTable, Arrays.asList(this.item.weapon.attackMessages));
            attackDamageSpinner.setValue(this.item.weapon.damage);
            attackVarianceSpinner.setValue(this.item.weapon.variance);
            critPercentSpinner.setValue(this.item.weapon.critPercent);
        }
    }

    public void onSave(ItemSaveRunnable saveListener) {
        this.saveListener = saveListener;
    }

    public void save() {
        if (saveListener != null) {
            this.item.name = nameField.getText();
            this.item.description = descriptionArea.getText();
            this.item.color = ((AsciiColor) Objects.requireNonNull(colorComboBox.getSelectedItem())).fg();
            this.item.tags = getTableAsList(tagTable).toArray(new String[0]);
            this.item.isWeapon = isWeaponCheckBox.isSelected();

            this.item.weapon = new DBWeapon();
            this.item.weapon.attackMessages = getTableAsList(attackMessageTable).toArray(new String[0]);
            this.item.weapon.damage = (int) attackDamageSpinner.getValue();
            this.item.weapon.variance = (int) attackVarianceSpinner.getValue();
            this.item.weapon.critPercent = (int) critPercentSpinner.getValue();

            System.out.println(this.item);

            this.item.save();
            saveListener.run(this.item);
        }
    }

    interface ItemSaveRunnable {
        void run(DBItem item);
    }
}
