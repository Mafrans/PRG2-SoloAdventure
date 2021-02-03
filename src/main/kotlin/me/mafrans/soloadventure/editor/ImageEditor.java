package me.mafrans.soloadventure.editor;

import me.mafrans.soloadventure.models.DBImage;
import me.mafrans.soloadventure.models.DBImageCell;

import javax.swing.*;

public class ImageEditor {
    private AsciiEditorTable asciiEditorTable;
    private JPanel mainPanel;
    private JPanel asciiEditorPainterWrapper;
    private JPanel colorPickerWrapper;
    private JRadioButton foregroundRadioButton;
    private JRadioButton backgroundRadioButton;
    private JButton saveButton;
    private JButton cancelButton;

    public AsciiEditorPainter asciiEditorPainter;
    public DBImage image;
    private Runnable saveListener;

    private void createUIComponents() {
        asciiEditorTable = new AsciiEditorTable(10, 10);
        asciiEditorTable.load(image);

        asciiEditorPainter = new AsciiEditorPainter(asciiEditorTable);
        asciiEditorPainterWrapper = new JPanel();
        asciiEditorPainterWrapper.add(asciiEditorPainter);
        asciiEditorPainter.load(image);

        colorPickerWrapper = new JPanel();
        colorPickerWrapper.add(new ColorPickerPanel(asciiEditorPainter, AsciiColor.values()));
    }

    public ImageEditor(DBImage image) {
        this.image = image;
        if(image == null) {
            this.image = new DBImage(new DBImageCell[24][16]);
        }

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

        saveButton.addActionListener(e -> save());
    }

    public void onSave(Runnable saveListener) {
        this.saveListener = saveListener;
    }

    public void save() {
        image.save();

        if (saveListener != null) {
            saveListener.run();
        }
    }
}
