package me.mafrans.soloadventure.editor.forms;

import me.mafrans.soloadventure.AsciiColor;
import me.mafrans.soloadventure.editor.jcomponents.AsciiEditorPainter;
import me.mafrans.soloadventure.editor.jcomponents.AsciiEditorTable;
import me.mafrans.soloadventure.editor.jcomponents.ColorPickerPanel;
import me.mafrans.soloadventure.editor.jcomponents.DrawType;
import me.mafrans.soloadventure.models.DBImage;

import javax.swing.*;

public class ImageEditor {
    private AsciiEditorTable asciiEditorTable;
    private JPanel mainPanel;
    private JPanel asciiEditorPainterWrapper;
    private JPanel colorPickerWrapper;
    private JRadioButton foregroundRadioButton;
    private JRadioButton backgroundRadioButton;
    private JButton saveButton;

    public AsciiEditorPainter asciiEditorPainter;
    public DBImage image;
    private ImageSaveRunnable saveListener;

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
            this.image = new DBImage();
        }

        JFrame frame = new JFrame("ImageEditor");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

    public void onSave(ImageSaveRunnable saveListener) {
        this.saveListener = saveListener;
    }

    public void save() {
        this.image = asciiEditorPainter.toDBImage();
        if (saveListener != null) {
            saveListener.run(this.image);
        }
    }

    interface ImageSaveRunnable {
        void run(DBImage image);
    }
}
