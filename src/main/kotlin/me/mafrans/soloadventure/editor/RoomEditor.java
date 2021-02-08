package me.mafrans.soloadventure.editor;

import me.mafrans.soloadventure.models.DBEnemy;
import org.bson.types.ObjectId;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    public ImagePreviewPanel imagePreviewPanel;
    public HashMap<ObjectId, EnemyPreviewPanel> enemyPreviewMap = new HashMap<>();

    private void createUIComponents() {
        roomColorComboBox = new JComboBox<>(AsciiColor.values());

        imagePreviewPanel = new ImagePreviewPanel(null);
        roomImageWrapper = new JPanel();
        roomImageWrapper.add(imagePreviewPanel);

        enemyContainer = new JPanel();
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
    }

    public void addItem() {

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
