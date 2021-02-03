package me.mafrans.soloadventure.editor;

import me.mafrans.soloadventure.models.DBEnemy;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EnemyEditor {
    private JTextField nameField;
    private JSpinner hpSpinner;
    private JButton editSpriteButton;
    private JPanel mainPanel;
    private JPanel spritePanelWrapper;

    public ImagePreviewPanel spritePanel;
    public DBEnemy enemy;

    public EnemyEditor(DBEnemy enemy) {
        this.enemy = enemy;
        if (enemy == null) {
            this.enemy = new DBEnemy(nameField.getText(), (int) hpSpinner.getValue());
        }

        editSpriteButton.addActionListener(e -> {
            ImageEditor imageEditor = new ImageEditor(enemy.sprite);
        });
    }

    private void createUIComponents() {
        spritePanel = new ImagePreviewPanel(null);
        spritePanelWrapper.add(spritePanel);
    }
}
