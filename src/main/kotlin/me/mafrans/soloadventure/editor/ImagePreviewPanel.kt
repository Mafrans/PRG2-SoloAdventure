package me.mafrans.soloadventure.editor

import me.mafrans.soloadventure.models.DBImage
import java.awt.Font
import java.awt.Graphics
import javax.swing.JPanel


class ImagePreviewPanel(var image: DBImage?) : JPanel() {
    override fun paint(g: Graphics?) {
        val image = this.image ?: return;
        g?.clearRect(0, 0, width, height)
        for (x in image.cells.indices) {
            for (y in image.cells[x].indices) {
                val cell = image.cells[x][y]
                val cellWidth = width / image.cells.size
                val cellHeight = height / image.cells[0].size
                val cellX = x * cellWidth
                val cellY = y * cellHeight

                var background = AsciiColor.BLACK
                var foreground = AsciiColor.WHITE
                var text = ""

                if (cell != null) {
                    println(cell.style)
                    background = AsciiColor.fromBG(cell.style.background)!!
                    foreground = AsciiColor.fromFG(cell.style.foreground)!!
                    text = cell.text!!
                }

                g?.color = background.color
                g?.fillRect(cellX, cellY, cellWidth, cellHeight)
                g?.color = foreground.color
                g?.font = g?.font?.deriveFont(Font.PLAIN, width/13f)
                g?.drawString(text, cellX + cellWidth / 2 - 4, cellY + cellHeight / 2 + 4)
            }
        }
    }
}