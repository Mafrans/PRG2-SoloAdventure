package me.mafrans.soloadventure.editor.forms;

import me.mafrans.soloadventure.AsciiColor;
import me.mafrans.soloadventure.editor.jcomponents.EnemyPreviewPanel;
import me.mafrans.soloadventure.editor.jcomponents.ImagePreviewPanel;
import me.mafrans.soloadventure.editor.jcomponents.ItemPreviewPanel;
import me.mafrans.soloadventure.editor.jskit.JSEditorPane;
import me.mafrans.soloadventure.models.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.HashMap;

public class RoomEditor {
    private JTabbedPane tabbedPane1;
    private JPanel mainPanel;
    private JComboBox<AsciiColor> roomColorComboBox;
    private JTextArea roomDescriptionArea;
    private JButton editImageButton;
    private JButton addEnemyButton;
    private JButton addItemButton;
    private JButton saveButton;
    private JButton addInspectionButton;
    private JPanel itemContainer;
    private JPanel enemyContainer;
    private JPanel inspectionContainer;
    private JPanel roomImageWrapper;
    private JTextPane onEnterPane;
    private JTabbedPane eventsTabs;
    private JTable inspectionTable;
    private JButton deleteButton;
    private JPanel editorPaneWrapper;
    private JFrame frame;

    private DBImage image;
    private DBRoom room;

    public ImagePreviewPanel imagePreviewPanel;
    public HashMap<DBEnemy, EnemyPreviewPanel> enemyPreviewMap = new HashMap<>();
    public HashMap<DBItem, ItemPreviewPanel> itemPreviewMap = new HashMap<>();
    private RoomSaveRunnable saveListener;

    private void createUIComponents() {
        roomColorComboBox = new JComboBox<>(AsciiColor.values());

        imagePreviewPanel = new ImagePreviewPanel(null);
        roomImageWrapper = new JPanel();
        roomImageWrapper.add(imagePreviewPanel);

        GridLayout layout = new GridLayout(0, 1);
        enemyContainer = new JPanel();
        enemyContainer.setLayout(layout);

        itemContainer = new JPanel();
        itemContainer.setLayout(layout);

        inspectionTable = new JTable();
        inspectionTable.setModel(new DefaultTableModel(new String[] { "Key", "Inspection" }, 0));

        onEnterPane = new JSEditorPane();
        onEnterPane.setText(
                "function onEnter (room, oldRoom, player) {\n" +
                "    \n" +
                "}\n"
        );
    }

    public RoomEditor(DBRoom room) {
        this.room = room;

        if (this.room == null) {
            this.room = new DBRoom();
        }
        System.out.println(this.room);
        update(this.room);

        frame = new JFrame("Room Editor");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        addEnemyButton.addActionListener(e -> {
            addEnemy();
        });

        addItemButton.addActionListener(e -> {
            addItem();
        });

        addInspectionButton.addActionListener(e -> {
            DefaultTableModel model = ((DefaultTableModel) inspectionTable.getModel());
            model.addRow(new Object[] { "key", "text" });
            inspectionTable.setModel(model);
        });

        saveButton.addActionListener(e -> {
            this.save();
        });

        deleteButton.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(mainPanel, "Do you really wish to delete this room?", "Really delete?", JOptionPane.YES_NO_OPTION);
            if(response == JOptionPane.YES_OPTION) {
                this.delete();
            }
        });

        inspectionTable.getModel().addTableModelListener(e -> {
            DefaultTableModel model = ((DefaultTableModel) inspectionTable.getModel());

            int columnCount = inspectionTable.getModel().getColumnCount();
            int rowCount = inspectionTable.getModel().getRowCount();
            for (int r = 0; r < rowCount; r++) {
                boolean hasContent = false;
                for (int c = 0; c < columnCount; c++) {
                    String value = (String) inspectionTable.getModel().getValueAt(r, c);

                    if (value != null && !value.isBlank()) {
                        hasContent = true;
                        break;
                    }
                }

                if (!hasContent) {
                    model.removeRow(r);
                }
            }

            inspectionTable.setModel(model);
        });
    }

    public void addItem() {
        ItemEditor itemEditor = new ItemEditor(null);
        itemEditor.onSave(item -> {
            if (itemPreviewMap.containsKey(item)) {
                ItemPreviewPanel itemPreviewPanel = itemPreviewMap.get(item);
                itemPreviewPanel.update(item);
            }
            else {
                ItemPreviewPanel itemPreviewPanel = new ItemPreviewPanel(item);
                itemContainer.add(itemPreviewPanel);
                itemPreviewMap.put(item, itemPreviewPanel);
            }
        });
    }

    public void addEnemy() {
        EnemyEditor enemyEditor = new EnemyEditor(null);
        enemyEditor.onSave(enemy -> {
            if (enemyPreviewMap.containsKey(enemy)) {
                EnemyPreviewPanel enemyPreviewPanel = enemyPreviewMap.get(enemy);
                enemyPreviewPanel.update(enemy);
            }
            else {
                EnemyPreviewPanel enemyPreviewPanel = new EnemyPreviewPanel(enemy);
                enemyContainer.add(enemyPreviewPanel);
                enemyPreviewMap.put(enemy, enemyPreviewPanel);
            }
        });
    }

    public void onSave(RoomSaveRunnable saveListener) {
        this.saveListener = saveListener;
    }

    public void save() {
        this.room.description = roomDescriptionArea.getText();
        this.room.enemies = enemyPreviewMap.keySet();
        this.room.items = itemPreviewMap.keySet();
        this.room.image = image;
        this.room.color = ((AsciiColor) roomColorComboBox.getSelectedItem()).getColor().getRGB();

        DBRoomEvents events = new DBRoomEvents();
        events.onEnter = onEnterPane.getText();
        this.room.events = events;

        HashMap<String, String> inspections = new HashMap<>();
        for (int r = 0; r < inspectionTable.getRowCount(); r++) {
            inspections.put(
                    (String) inspectionTable.getValueAt(0, 0),
                    (String) inspectionTable.getValueAt(0, 1)
                );
        }
        this.room.inspections = inspections;

        if (saveListener != null) {
            saveListener.run(this.room);
        }

        this.room.save();
    }

    public void update(DBRoom room) {
        roomDescriptionArea.setText(room.description);
        for (DBEnemy enemy : room.enemies) {
            EnemyPreviewPanel enemyPreviewPanel = new EnemyPreviewPanel(enemy);
            enemyContainer.add(enemyPreviewPanel);
            enemyPreviewMap.put(enemy, enemyPreviewPanel);
        }
        for (DBItem item : room.items) {
            ItemPreviewPanel itemPreviewPanel = new ItemPreviewPanel(item);
            itemContainer.add(itemPreviewPanel);
            itemPreviewMap.put(item, itemPreviewPanel);
        }
        image = room.image;
        imagePreviewPanel.setImage(image);
        roomColorComboBox.setSelectedItem(AsciiColor.Companion.from(new Color(room.color)));

        onEnterPane.setText(room.events.onEnter);

        int i = 0;
        inspectionTable.setModel(new DefaultTableModel(new String[] { "Key", "Inspection" }, room.inspections.size()));
        for(String key : room.inspections.keySet()) {
            inspectionTable.setValueAt(key, i, 0);
            inspectionTable.setValueAt(room.inspections.get(key), i, 1);
            i++;
        }
    }

    public void delete() {
        if (saveListener != null) {
            saveListener.run(null);
        }
        this.room.delete();
        frame.dispose();
    }

    interface RoomSaveRunnable {
        void run(DBRoom item);
    }
}
