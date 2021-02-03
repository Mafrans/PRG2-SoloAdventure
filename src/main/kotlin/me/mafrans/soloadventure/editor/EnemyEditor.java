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

        JFrame frame = new JFrame("EnemyEditor");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        editSpriteButton.addActionListener(e -> {
            ImageEditor imageEditor = new ImageEditor(this.enemy.sprite);
        });
    }

    private void createUIComponents() {
        spritePanel = new ImagePreviewPanel(null);
        spritePanelWrapper = new JPanel();
        spritePanelWrapper.add(spritePanel);
    }
}
