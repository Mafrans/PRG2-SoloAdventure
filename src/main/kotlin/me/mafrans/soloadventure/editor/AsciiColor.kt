package me.mafrans.soloadventure.editor

import org.fusesource.jansi.Ansi
import java.awt.Color

enum class AsciiColor(val ansiColor: Ansi.Color, val color: Color, val bright: Boolean) {
    WHITE(Ansi.Color.WHITE, Color(255, 255, 255), true),
    GRAY(Ansi.Color.WHITE, Color(128, 128, 128), false),
    RED(Ansi.Color.RED, Color(255, 0, 0), true),
    DARK_RED(Ansi.Color.RED, Color(128, 0, 0), false),
    MAGENTA(Ansi.Color.MAGENTA, Color(255, 0, 255), false),
    DARK_MAGENTA(Ansi.Color.MAGENTA, Color(128, 0, 128), false),
    GREEN(Ansi.Color.GREEN, Color(0, 255, 0), true),
    DARK_GREEN(Ansi.Color.GREEN, Color(0, 128, 0), false),
    CYAN(Ansi.Color.CYAN, Color(0, 255, 255), true),
    DARK_CYAN(Ansi.Color.CYAN, Color(0, 128, 128), false),
    BLUE(Ansi.Color.BLUE, Color(0, 0, 255), true),
    DARK_BLUE(Ansi.Color.BLUE, Color(0, 0, 128), false),
    YELLOW(Ansi.Color.YELLOW, Color(255, 255, 0), true),
    DARK_YELLOW(Ansi.Color.YELLOW, Color(128, 128, 0), false),
    BLACK(Ansi.Color.BLACK, Color(0, 0, 0), true);

    companion object {
        fun from(ansiColor: Ansi.Color): AsciiColor? {
            return values().find { it.ansiColor == ansiColor }
        }

        fun from(color: Color): AsciiColor? {
            return values().find { it.color == color }
        }
    }

    fun fg(): Int {
        return if(bright) ansiColor.fgBright() else ansiColor.fg()
    }

    fun bg(): Int {
        return if(bright) ansiColor.bgBright() else ansiColor.bg()
    }
}