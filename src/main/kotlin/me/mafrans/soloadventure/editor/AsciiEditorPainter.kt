package me.mafrans.soloadventure.editor

import me.mafrans.soloadventure.models.DBImage
import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.JPanel

class CellColor(var background: AsciiColor?, var foreground: AsciiColor?)

enum class DrawType {
    FOREGROUND,
    BACKGROUND
}

class AsciiEditorPainter(val table: AsciiEditorTable) : JPanel() {
    val colors: Array<Array<CellColor>> = Array(table.columns) { Array(table.rows) { CellColor(null, null) } }
    var currentColor = AsciiColor.WHITE
    var drawType: DrawType = DrawType.FOREGROUND;
    init {
        preferredSize = Dimension(table.preferredSize.height, table.preferredSize.height)
        addMouseListener(object : MouseAdapter() {
            override fun mousePressed(e: MouseEvent?) {
                if (e == null) return
                val cellX = e.x / (width / colors.size)
                val cellY = e.y / (height / colors[0].size)

                when(drawType) {
                    DrawType.FOREGROUND -> colors[cellX][cellY].foreground = currentColor
                    DrawType.BACKGROUND -> colors[cellX][cellY].background = currentColor
                }

                repaint()
            }
        })

        table.model.addTableModelListener { repaint() }
    }

    fun load(image: DBImage?) {
        if (image == null)
            return

        for (x in image.cells.indices) {
            for (y in image.cells[x].indices) {
                val cell = image.cells[x][y]
                val background = AsciiColor.fromAnsi(cell.style.background)
                val foreground = AsciiColor.fromAnsi(cell.style.foreground)
                colors[x][y] = CellColor(background, foreground)
            }
        }
        repaint()
    }

    override fun paint(g: Graphics?) {
        for (x in colors.indices) {
            for (y in colors[x].indices) {
                val cellWidth = width / colors.size
                val cellHeight = height / colors[x].size
                val cellX = x * cellWidth
                val cellY = y * cellHeight

                val background = colors[x][y].background
                if (background == null)
                    g?.color = Color.BLACK
                else
                    g?.color = background.color;

                g?.fillRect(cellX, cellY, cellWidth, cellHeight)

                val foreground = colors[x][y].foreground
                if (foreground == null)
                    g?.color = Color.WHITE
                else
                    g?.color = foreground.color;

                val text: String? = table.model.getValueAt(y, x) as String?
                if (text != null) {
                    g?.drawString(text, cellX + cellWidth/2 - 4, cellY + cellHeight/2 + 4)
                }

                g?.color = Color.WHITE
                g?.drawRect(cellX, cellY, cellWidth, cellHeight)
            }
        }
    }
}