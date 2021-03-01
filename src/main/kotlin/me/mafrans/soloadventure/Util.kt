package me.mafrans.soloadventure

import java.util.*
import javax.swing.JTable

/**
 * Helper class including utility functions
 */
class Util {
    companion object {
        /**
         * Treats a {@link java.swing.JTable} as a list, setting its values from a {@link java.util.List}
         * @param table The {@link java.swing.JTable} to edit
         * @param values A list of values to set in the {@link java.swing.JTable}
         */
        fun setListToTable(table: JTable, values: List<Any?>) {
            val model = table.model
            for (i in values.indices) {
                model.setValueAt(values[i], i, 0)
            }
        }

        /**
         * Treats a {@link java.swing.JTable} as a list, getting its values as a {@link java.util.List}
         * @param table The {@link java.swing.JTable} to parse
         * @return A list of values
         */
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
