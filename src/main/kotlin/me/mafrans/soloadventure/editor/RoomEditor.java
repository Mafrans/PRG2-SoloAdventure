package me.mafrans.soloadventure.editor;

import me.mafrans.soloadventure.Util;
import me.mafrans.soloadventure.models.*;
import org.bson.types.ObjectId;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

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
    private JPanel editorPaneWrapper;

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

        JFrame frame = new JFrame("Room Editor");
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

    interface RoomSaveRunnable {
        void run(DBRoom item);
    }
}
