package me.mafrans.soloadventure

import java.util.*
import javax.swing.JTable

class Util {
    companion object {
        fun setListToTable(table: JTable, values: List<Any?>) {
            val model = table.model
            for (i in values.indices) {
                model.setValueAt(values[i], i, 0)
            }
        }

        fun getTableAsList(table: JTable): List<Any?> {
            val model = table.model
            val values: MutableList<Any> = ArrayList()
            for (i in 0 until model.rowCount) {
                val value = model.getValueAt(i, 0)
                if (value != null) {
                    values.add(value)
                }
            }
            return values
        }
    }
}
