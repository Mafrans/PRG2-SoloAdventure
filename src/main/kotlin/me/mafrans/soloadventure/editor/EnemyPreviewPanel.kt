package me.mafrans.soloadventure.editor

import me.mafrans.soloadventure.models.DBEnemy
import java.awt.Dimension
import java.awt.Font
import java.awt.GridLayout
import javax.swing.JButton
import javax.swing.JLabel
import javax.swing.JPanel

class EnemyPreviewPanel(enemy: DBEnemy) : JPanel() {
    var namePanel = JLabel(enemy.name)
    var hpPanel = JLabel(enemy.hp.toString())
    var previewPanel: ImagePreviewPanel? = null
    var editButton = JButton("Edit")

    init {
        if (enemy.sprite != null) {
            previewPanel = ImagePreviewPanel(enemy.sprite)
            previewPanel!!.preferredSize = Dimension(70, 70)
            add(previewPanel)
        }

        namePanel.font = namePanel.font?.deriveFont(Font.BOLD)
        hpPanel.font = namePanel.font?.deriveFont(Font.PLAIN)

        val infoPanel = JPanel()
        infoPanel.layout = GridLayout(0, 1)
        infoPanel.add(namePanel)
        infoPanel.add(hpPanel)
        add(infoPanel)

        add(editButton)
    }

    fun update(enemy: DBEnemy) {
        namePanel.text = enemy.name
        previewPanel!!.image = enemy.sprite
        previewPanel!!.repaint()
    }
}