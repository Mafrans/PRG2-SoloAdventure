package me.mafrans.soloadventure.editor;

import me.mafrans.soloadventure.models.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.HashMap;

import static me.mafrans.soloadventure.models.DBGame.MAP_HEIGHT;
import static me.mafrans.soloadventure.models.DBGame.MAP_WIDTH;

public class MapEditor {
    private JPanel mainPanel;
    private JButton saveButton;
    private JPanel roomGridPanel;

    public JButton[][] roomButtons = new JButton[MAP_WIDTH][MAP_HEIGHT];
    public DBRoom[][] rooms = new DBRoom[MAP_WIDTH][MAP_HEIGHT];

    public Point[] directions = new Point[] {
            new Point(1, 0),
            new Point(0, 1),
            new Point(-1, 0),
            new Point(0, -1),
    };

    private DBGame game;

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
                        rooms[_x][_y] = r;

                        if (r == null) button.setBackground(null);
                        else button.setBackground(new Color(r.color));

                        for (Point direction : directions) {
                            if (rooms[_x + direction.x][_y + direction.y] == null) {
                                JButton btn = roomButtons[_x + direction.x][_y + direction.y];
                                btn.setVisible(r != null);
                            }
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

    public void save() {
        this.game.rooms = rooms;
        this.game.save();
    }

    public void update(DBGame game) {
        rooms = game.rooms;
    }

    public MapEditor(DBGame game) {
        this.game = game;
        if(this.game == null) {
            this.game = new DBGame();
        }
        update(this.game);

        saveButton.addActionListener(e -> {
            this.save();
        });

        JFrame frame = new JFrame("Map Editor");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
