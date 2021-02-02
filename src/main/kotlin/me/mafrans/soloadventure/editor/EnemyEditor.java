package me.mafrans.soloadventure.editor;

import javax.swing.*;

public class EnemyEditor {
    private JTextField nameField;
    private JSpinner hpSpinner;
    private JButton editSpriteButton;
    private JPanel mainPanel;
    private JPanel spritePanelWrapper;

    public ImagePreviewPanel spritePanel;

    private void createUIComponents() {
        spritePanel = new ImagePreviewPanel(null);
        spritePanelWrapper.add(spritePanel);
    }
}
