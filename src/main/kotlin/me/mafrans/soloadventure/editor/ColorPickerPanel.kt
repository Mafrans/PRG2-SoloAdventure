package me.mafrans.soloadventure.editor

import java.awt.Color
import java.awt.Dimension
import java.awt.GridLayout
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.BorderFactory
import javax.swing.JPanel

class ColorPickerPanel(painter: AsciiEditorPainter, colors: Array<AsciiColor>) : JPanel() {
    init {
        layout = GridLayout(4, 0, 5, 5)

        for (color in colors) {
            val panel = ColoredPanel(color.color)
            panel.preferredSize = Dimension(20, 20)
            panel.addMouseListener(object : MouseAdapter() {
                override fun mouseClicked(e: MouseEvent?) {
                    painter.currentColor = color
                }
            })
            add(panel)
        }
    }
}