package me.mafrans.soloadventure.editor

import me.mafrans.soloadventure.models.DBEnemy
import java.awt.Color
import java.awt.Dimension
import java.awt.Font
import java.awt.GridLayout
import javax.swing.*
import javax.swing.border.EmptyBorder

class EnemyPreviewPanel(var enemy: DBEnemy) : JPanel() {
    var namePanel = JLabel(enemy.name)
    var hpPanel = JLabel(enemy.hp.toString())
    var previewPanel: ImagePreviewPanel? = null
    var editButton = JButton("📝")
    var deleteButton = JButton("❌")

    init {
        layout = BoxLayout(this, BoxLayout.LINE_AXIS)
        border = EmptyBorder(5, 5, 0, 0)

        if (enemy.sprite != null) {
            previewPanel = ImagePreviewPanel(enemy.sprite)
            previewPanel!!.preferredSize = Dimension(70, 70)
            previewPanel!!.maximumSize = Dimension(70, 70)
            add(previewPanel)
        }

        add(Box.createRigidArea(Dimension(10, 0)));

        namePanel.font = namePanel.font?.deriveFont(Font.BOLD)
        hpPanel.font = namePanel.font?.deriveFont(Font.PLAIN)

        val infoPanel = JPanel()
        infoPanel.layout = GridLayout(0, 1)
        infoPanel.add(namePanel)
        infoPanel.add(hpPanel)
        add(infoPanel)
        add(Box.createHorizontalGlue());
        add(editButton)
        add(deleteButton)

        editButton.addActionListener { e -> edit() }
        deleteButton.addActionListener {
            val response = JOptionPane.showConfirmDialog(this, "Do you really wish to delete " + this.enemy.name + "?", "Really delete?", JOptionPane.YES_NO_OPTION);
            if(response == JOptionPane.YES_OPTION) {
                delete();
            }
        }
    }

    fun update(enemy: DBEnemy) {
        namePanel.text = enemy.name
        previewPanel!!.image = enemy.sprite
        previewPanel!!.repaint()
    }

    fun edit() {
        val editor = EnemyEditor(this.enemy)
        editor.update()
        editor.onSave { enemy ->
            run {
                this.enemy = enemy
                this.update(enemy)
            }
        }
    }

    fun delete() {
        this.enemy.delete()
        this.parent.remove(this)
    }
}