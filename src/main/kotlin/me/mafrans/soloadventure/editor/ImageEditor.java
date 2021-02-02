package me.mafrans.soloadventure.editor;

import javax.swing.*;

public class ImageEditor {
    private AsciiEditorTable asciiEditorTable;
    private JPanel mainPanel;
    private JPanel asciiEditorPainterWrapper;
    private JPanel colorPickerWrapper;
    private JRadioButton foregroundRadioButton;
    private JRadioButton backgroundRadioButton;

    public AsciiEditorPainter asciiEditorPainter;

    private void createUIComponents() {
        asciiEditorTable = new AsciiEditorTable(10, 10);

        asciiEditorPainter = new AsciiEditorPainter(asciiEditorTable);
        asciiEditorPainterWrapper = new JPanel();
        asciiEditorPainterWrapper.add(asciiEditorPainter);

        colorPickerWrapper = new JPanel();
        colorPickerWrapper.add(new ColorPickerPanel(asciiEditorPainter, AsciiColor.values()));
    }

    public ImageEditor() {
        JFrame frame = new JFrame("ImageEditor");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        foregroundRadioButton.addActionListener(e -> {
            asciiEditorPainter.setDrawType(DrawType.FOREGROUND);
        });

        backgroundRadioButton.addActionListener(e -> {
            asciiEditorPainter.setDrawType(DrawType.BACKGROUND);
        });
    }
}
