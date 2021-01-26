package me.mafrans.soloadventure.editor

import javax.swing.JTable
import javax.swing.table.DefaultTableModel

class AsciiEditorTable : JTable() {
    fun setCellGridSize(width: Int, height: Int) {
        val model = DefaultTableModel()
        for (y in 0 until height) {
            model.addRow(emptyArray())
            for (i in 0 until width) {
                model.addColumn("");
            }
        }
    }
}