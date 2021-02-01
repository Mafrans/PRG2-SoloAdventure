package me.mafrans.soloadventure.editor

import java.awt.Color
import java.awt.Dimension
import java.awt.GridBagLayout
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.BorderFactory
import javax.swing.JPanel

class ColorPickerPanel(painter: AsciiEditorPainter, colors: Array<Color>) : JPanel() {
    init {
        layout = GridBagLayout()

        for (color in colors) {
            val panel = ColoredPanel(color)
            panel.preferredSize = Dimension(20, 20)
            panel.border = BorderFactory.createEmptyBorder(10, 10, 10, 10)
            panel.addMouseListener(object : MouseAdapter() {
                override fun mouseClicked(e: MouseEvent?) {
                    painter.currentColor = color
                }
            })
            add(panel)
        }
    }
}