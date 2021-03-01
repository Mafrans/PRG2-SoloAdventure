package me.mafrans.soloadventure.editor.jcomponents

import java.awt.Color
import java.awt.Graphics
import javax.swing.JPanel

/**
 * Literally just a single colored panel
 */
class ColoredPanel(val color: Color) : JPanel() {
    override fun paint(g: Graphics?) {
        g?.color = color
        g?.fillRect(0, 0, width, height)
    }
}