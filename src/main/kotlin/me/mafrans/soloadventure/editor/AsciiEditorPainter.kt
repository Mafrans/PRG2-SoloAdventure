package me.mafrans.soloadventure.editor

import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import javax.swing.JPanel
import javax.swing.event.TableModelEvent
import javax.swing.event.TableModelListener

class AsciiEditorPainter(val table: AsciiEditorTable) : JPanel() {
    val colors: Array<Array<Color?>> = Array(table.columns) { Array(table.rows) { null } }
    var currentColor = Color.RED
    init {
        preferredSize = Dimension(table.preferredSize.height, table.preferredSize.height)
        addMouseListener(object : MouseAdapter() {
            override fun mousePressed(e: MouseEvent?) {
                if (e == null) return
                val cellX = e.x / (width / colors.size)
                val cellY = e.y / (height / colors[0].size)

                colors[cellX][cellY] = currentColor
                repaint()
            }
        })

        table.model.addTableModelListener { repaint() }
    }

    override fun paint(g: Graphics?) {
        for (x in colors.indices) {
            for (y in colors[0].indices) {
                val cellWidth = width / colors.size
                val cellHeight = height / colors[0].size
                val cellX = x * cellWidth
                val cellY = y * cellHeight

                if (colors[x][y] == null)
                    g?.color = Color.WHITE
                else
                    g?.color = colors[x][y];

                g?.fillRect(cellX, cellY, cellWidth, cellHeight)

                val saturation = (g?.color!!.red + g.color!!.green + g.color!!.blue) / (3.0 * 256)
                println(saturation)
                g.color = Color.BLACK
                if (saturation < 0.5)
                    g.color = Color.WHITE

                val text: String? = table.model.getValueAt(y, x) as String?
                if (text != null) {
                    g.drawString(text, cellX + cellWidth/2 - 4, cellY + cellHeight/2 + 4)
                }

                g.color = Color.BLACK
                g.drawRect(cellX, cellY, cellWidth, cellHeight)
            }
        }
    }
}