package me.mafrans.soloadventure.editor

import me.mafrans.soloadventure.models.DBEnemy
import java.awt.Color
import java.awt.Dimension
import java.awt.Font
import java.awt.GridLayout
import javax.swing.*
import javax.swing.border.EmptyBorder

class EnemyPreviewPanel(enemy: DBEnemy) : JPanel() {
    var namePanel = JLabel(enemy.name)
    var hpPanel = JLabel(enemy.hp.toString())
    var previewPanel: ImagePreviewPanel? = null
    var editButton = JButton("Edit")

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
    }

    fun update(enemy: DBEnemy) {
        namePanel.text = enemy.name
        previewPanel!!.image = enemy.sprite
        previewPanel!!.repaint()
    }
}