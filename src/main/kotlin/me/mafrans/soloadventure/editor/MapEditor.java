package me.mafrans.soloadventure.editor;

import me.mafrans.soloadventure.models.DBRoom;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MapEditor {
    private JPanel mainPanel;
    private JButton saveButton;
    private JPanel roomGridPanel;

    private final int MAP_WIDTH = 9;
    private final int MAP_HEIGHT = 9;

    public JButton[][] roomButtons = new JButton[MAP_WIDTH][MAP_HEIGHT];
    public DBRoom[][] rooms = new DBRoom[MAP_WIDTH][MAP_HEIGHT];

    private void createUIComponents() {
        roomGridPanel = new JPanel();
        roomGridPanel.setLayout(new GridLayout(MAP_WIDTH, MAP_HEIGHT));
        for (int x = 0; x < MAP_WIDTH; x++) {
            for (int y = 0; y < MAP_HEIGHT; y++) {
                JButton button = new JButton(" ");
                button.setVisible(false);

                int _x = x;
                int _y = y;
                button.addActionListener(e -> {
                    DBRoom room = rooms[_x][_y];
                    if (room == null) {
                        room = new DBRoom();
                    }

                    RoomEditor editor = new RoomEditor(room);
                    editor.onSave(r -> {
                        if (button.getText().equals(" ")) {
                            button.setText("🚪");
                        }
                    });
                });

                roomGridPanel.add(button);
                roomButtons[x][y] = button;
            }
        }

        JButton startButton = roomButtons[MAP_WIDTH/2][MAP_HEIGHT/2];
        startButton.setText("🏴");
        startButton.setVisible(true);
    }

    public MapEditor() {
        JFrame frame = new JFrame("Map Editor");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
