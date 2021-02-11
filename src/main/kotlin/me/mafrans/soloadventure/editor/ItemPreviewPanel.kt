package me.mafrans.soloadventure.editor

import me.mafrans.soloadventure.Database
import me.mafrans.soloadventure.models.DBEnemy
import me.mafrans.soloadventure.models.DBItem
import java.awt.Color
import java.awt.Dimension
import java.awt.Font
import java.awt.GridLayout
import javax.swing.*
import javax.swing.border.EmptyBorder

class ItemPreviewPanel(var item: DBItem) : JPanel() {
    var namePanel = JLabel()
    var descriptionPanel = JLabel()
    var editButton = JButton("📝")
    var deleteButton = JButton("❌")

    init {
        layout = BoxLayout(this, BoxLayout.LINE_AXIS)
        border = EmptyBorder(5, 5, 0, 0)

        namePanel.font = namePanel.font?.deriveFont(Font.BOLD)
        descriptionPanel.font = namePanel.font?.deriveFont(Font.PLAIN)

        add(namePanel)
        add(Box.createRigidArea(Dimension(10, 0)))
        add(descriptionPanel)
        add(Box.createHorizontalGlue());
        add(editButton)
        add(deleteButton)

        update(item);

        editButton.addActionListener { edit() }
        deleteButton.addActionListener {
            val response = JOptionPane.showConfirmDialog(this, "Do you really wish to delete " + this.item.name + "?", "Really delete?", JOptionPane.YES_NO_OPTION);
            if(response == JOptionPane.YES_OPTION) {
                delete();
            }
        }
    }

    fun update(item: DBItem) {
        namePanel.text = (if(item.isWeapon) "⚔ " else "") + item.name
        descriptionPanel.text = if(item.description.length > 32) item.description.substring(0..32) + "..." else item.description
    }

    fun edit() {
        val editor = ItemEditor(this.item);
        editor.update();
        editor.onSave { item ->
            run {
                this.item = item;
                this.update(item);
            }
        }
    }

    fun delete() {
        this.item.delete()
        this.parent.remove(this)
    }
}