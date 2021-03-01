package me.mafrans.soloadventure.editor.jskit

import java.awt.Color
import java.awt.Graphics2D
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern
import javax.swing.text.Element
import javax.swing.text.PlainDocument
import javax.swing.text.PlainView
import javax.swing.text.Utilities


class JSView(elem: Element?) : PlainView(elem) {
    private val patternColors = hashMapOf<Pattern, Color>(
            Pattern.compile("(if|while|for|function)") to Color(128, 128, 255),
            Pattern.compile("(var|const|let)") to Color(128, 128, 255),
            Pattern.compile("(\".+\")") to Color(60, 140, 60),
            Pattern.compile("([0-9]+)") to Color(128, 128, 255),
    )

    init {
        document.putProperty(PlainDocument.tabSizeAttribute, 4)
    }

    override fun drawUnselectedText(g: Graphics2D?, x0: Float, y: Float, p0: Int, p1: Int): Float {
        var x = x0;

        val text = document.getText(p0, p1 - p0)

        val startMap: SortedMap<Int, Int> = TreeMap<Int, Int>()
        val colorMap: SortedMap<Int, Color> = TreeMap<Int, Color>()

        // Match all regexes on this snippet, store positions
        for ((key, value) in patternColors.entries) {
            val matcher: Matcher = key.matcher(text)
            while (matcher.find()) {
                startMap[matcher.start(1)] = matcher.end()
                colorMap[matcher.start(1)] = value
            }
        }

        // TODO: check the map for overlapping parts
        var i = 0

        // Colour the parts
        for ((start, end) in startMap) {
            if (i < start) {
                g?.color = Color.black
                document.getText(p0 + i, start - i, lineBuffer)
                x = Utilities.drawTabbedText(lineBuffer, x, y, g, this, i)
            }
            g?.color = colorMap[start]
            i = end
            document.getText(p0 + start, i - start, lineBuffer)
            x = Utilities.drawTabbedText(lineBuffer, x, y, g, this, start)
        }

        // Paint possible remaining text black
        if (i < text.length) {
            g?.color = Color.black
            document.getText(p0 + i, text.length - i, lineBuffer)
            x = Utilities.drawTabbedText(lineBuffer, x, y, g, this, i)
        }

        return x
    }
}
