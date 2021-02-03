package me.mafrans.soloadventure.editor

import me.mafrans.soloadventure.models.DBImage
import javax.swing.JTable
import javax.swing.table.DefaultTableModel

class AsciiEditorTable(val columns: Int, val rows: Int) : JTable() {
    init {
        val model = DefaultTableModel()
        for (y in 0 until columns) {
            model.addRow(arrayOfNulls<String>(0))
        }
        for (x in 0 until rows) {
            model.addColumn("")
        }
        this.model = model;
    }

    fun load(image: DBImage?) {
        if (image == null)
            return

        val model = this.model;
        for (x in image.cells.indices) {
            for (y in image.cells[x].indices) {
                val cell = image.cells[x][y]
                model.setValueAt(cell.text, x, y);
            }
        }
        this.model = model;
    }
}