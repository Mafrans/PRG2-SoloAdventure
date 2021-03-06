package me.mafrans.soloadventure

import org.fusesource.jansi.Ansi
import java.awt.Color

/**
 * Enum class containing ascii color values, used for matching
 */
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
        /**
         * Finds a corresponding AsciiColor from an {@link Ansi.Color} object
         */
        fun from(ansiColor: Ansi.Color): AsciiColor? {
            return values().find { it.ansiColor == ansiColor }
        }

        /**
         * Finds a corresponding AsciiColor from a {@link java.awt.Color} object
         */
        fun from(color: Color): AsciiColor? {
            return values().find { it.color == color }
        }

        /**
         * Finds a corresponding AsciiColor from a foreground Ansi color int
         */
        fun fromFG(ansiColor: Int): AsciiColor? {
            return values().find { it.fg() == ansiColor }
        }

        /**
         * Finds a corresponding AsciiColor from a background Ansi color int
         */
        fun fromBG(ansiColor: Int): AsciiColor? {
            return values().find { it.bg() == ansiColor }
        }
    }

    /**
     * @return The foreground Ansi color
     */
    fun fg(): Int {
        return if(bright) ansiColor.fgBright() else ansiColor.fg()
    }

    /**
     * @return The background Ansi color
     */
    fun bg(): Int {
        return if(bright) ansiColor.bgBright() else ansiColor.bg()
    }

    override fun toString(): String {
        val name = super.toString()
        return name.split('_').joinToString(" ") {
            it[0].toUpperCase() + it.slice(1 until it.length).toLowerCase()
        }
    }
}