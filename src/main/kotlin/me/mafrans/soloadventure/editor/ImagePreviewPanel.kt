package me.mafrans.soloadventure.editor

import me.mafrans.soloadventure.models.DBImage
import java.awt.Graphics
import javax.swing.JPanel

class ImagePreviewPanel(val image: DBImage) : JPanel() {
    override fun paint(g: Graphics?) {
        for (x in image.cells.indices) {
            for (y in image.cells[x].indices) {
                val cell = image.cells[x][y]
                val cellWidth = width / image.cells.size
                val cellHeight = height / image.cells[0].size
                val cellX = x * cellWidth
                val cellY = y * cellHeight
            }
        }
    }
}