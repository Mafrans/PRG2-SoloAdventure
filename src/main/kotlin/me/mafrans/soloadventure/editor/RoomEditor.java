package me.mafrans.soloadventure.editor;

import org.bson.types.ObjectId;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.HashMap;

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
    private JTextPane onEnterPane;
    private JTabbedPane eventsTabs;
    private JTable inspectionTable;
    private JPanel editorPaneWrapper;

    public ImagePreviewPanel imagePreviewPanel;
    public HashMap<ObjectId, EnemyPreviewPanel> enemyPreviewMap = new HashMap<>();
    public HashMap<ObjectId, ItemPreviewPanel> itemPreviewMap = new HashMap<>();

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

    public RoomEditor() {
        JFrame frame = new JFrame("Room Editor");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
            if (enemyPreviewMap.containsKey(item.id)) {
                ItemPreviewPanel itemPreviewPanel = itemPreviewMap.get(item.id);
                itemPreviewPanel.update(item);
            }
            else {
                ItemPreviewPanel itemPreviewPanel = new ItemPreviewPanel(item);
                itemContainer.add(itemPreviewPanel);
                itemPreviewMap.put(item.id, itemPreviewPanel);
            }
        });
    }

    public void addEnemy() {
        EnemyEditor enemyEditor = new EnemyEditor(null);
        enemyEditor.onSave(enemy -> {
            if (enemyPreviewMap.containsKey(enemy.id)) {
                EnemyPreviewPanel enemyPreviewPanel = enemyPreviewMap.get(enemy.id);
                enemyPreviewPanel.update(enemy);
            }
            else {
                EnemyPreviewPanel enemyPreviewPanel = new EnemyPreviewPanel(enemy);
                enemyContainer.add(enemyPreviewPanel);
                enemyPreviewMap.put(enemy.id, enemyPreviewPanel);
            }
        });
    }

    public void addInvestigation() {

    }
}
