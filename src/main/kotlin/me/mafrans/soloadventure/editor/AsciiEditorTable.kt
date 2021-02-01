package me.mafrans.soloadventure.editor

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
}