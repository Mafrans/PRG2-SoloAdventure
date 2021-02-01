package me.mafrans.soloadventure.editor;

import javax.swing.*;
import java.awt.*;

public class ImageEditor {
    private AsciiEditorTable asciiEditorTable;
    private JPanel mainPanel;
    private JPanel asciiEditorPainterWrapper;
    private JPanel colorPickerWrapper;

    private void createUIComponents() {
        asciiEditorTable = new AsciiEditorTable(10, 10);

        AsciiEditorPainter painter = new AsciiEditorPainter(asciiEditorTable);
        asciiEditorPainterWrapper = new JPanel();
        asciiEditorPainterWrapper.add(painter);

        colorPickerWrapper = new JPanel();
        colorPickerWrapper.add(new ColorPickerPanel(painter, new Color[] { Color.RED, Color.GREEN, Color.BLUE }));
    }

    public ImageEditor() {
        JFrame frame = new JFrame("ImageEditor");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
