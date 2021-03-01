package me.mafrans.soloadventure.editor.forms;

import me.mafrans.soloadventure.editor.jcomponents.ImagePreviewPanel;
import me.mafrans.soloadventure.models.DBEnemy;

import javax.swing.*;
import java.awt.*;

public class EnemyEditor {
    private JTextField nameField;
    private JSpinner hpSpinner;
    private JButton editSpriteButton;
    private JPanel mainPanel;
    private JPanel spritePanelWrapper;
    private JButton saveButton;

    public ImagePreviewPanel spritePanel;
    public DBEnemy enemy;
    private EnemySaveRunnable saveListener;

    public EnemyEditor(DBEnemy enemy) {
        this.enemy = enemy;
        if (enemy == null) {
            this.enemy = new DBEnemy(nameField.getText(), (int) hpSpinner.getValue());
        }

        JFrame frame = new JFrame("EnemyEditor");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        editSpriteButton.addActionListener(e -> {
            ImageEditor imageEditor = new ImageEditor(this.enemy.sprite);
            imageEditor.onSave(image -> {
                this.enemy.sprite = image;
                spritePanel.setImage(image);
                spritePanel.repaint();
            });
        });

        saveButton.addActionListener(e -> save());
    }

    private void createUIComponents() {
        spritePanel = new ImagePreviewPanel(null);
        spritePanel.setPreferredSize(new Dimension(150, 150));
        spritePanelWrapper = new JPanel();
        spritePanelWrapper.add(spritePanel);
    }

    public void onSave(EnemySaveRunnable saveListener) {
        this.saveListener = saveListener;
    }

    public void save() {
        if (saveListener != null) {
            this.enemy.name = nameField.getText();
            this.enemy.hp = (int) hpSpinner.getValue();
            this.enemy.save();
            saveListener.run(this.enemy);
        }
    }

    public void update() {
        nameField.setText(this.enemy.name);
        hpSpinner.setValue(this.enemy.hp);
        spritePanel.setImage(this.enemy.sprite);
        spritePanel.repaint();
    }

    interface EnemySaveRunnable {
        void run(DBEnemy image);
    }
}
