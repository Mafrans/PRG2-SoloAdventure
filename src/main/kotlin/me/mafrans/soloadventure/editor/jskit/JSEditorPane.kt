package me.mafrans.soloadventure.editor.jskit

import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import javax.swing.JTextPane
import javax.swing.text.BadLocationException


class JSEditorPane : JTextPane() {
    init {
        setEditorKitForContentType("text/javascript", JSEditorKit())
        contentType = "text/javascript"

        addKeyListener(object : KeyListener {
            private var enterFlag = false
            private val NEW_LINE = '\n'
            override fun keyPressed(event: KeyEvent) {
                enterFlag = false
                if (event.keyCode == KeyEvent.VK_ENTER && event.modifiersEx == 0) {
                    if (selectionStart == selectionEnd) {
                        enterFlag = true
                        event.consume()
                    }
                }

                val pairs = hashMapOf('{' to '}', '[' to ']', '(' to ')', '"' to '"', '\'' to '\'')
                if (pairs.keys.contains(event.keyChar)) {
                    val oldCaretPosition = caretPosition
                    Thread {
                        // Wait 100ms, then add a new character, this causes issues if you write
                        // faster than 10 characters per second, but no real person does that
                        Thread.sleep(100)
                        text = text.slice(0 until caretPosition) + pairs[event.keyChar] + text.slice(caretPosition until text.length)
                        caretPosition = oldCaretPosition + 1
                    }.start()
                }
            }

            override fun keyTyped(e: KeyEvent) {
                val oldCaretPosition = caretPosition;
                when(e.keyChar) {
                    '\b' -> {
                        var i = caretPosition-1
                        var c = 1;
                        while (i >= 0 && text[i] == ' ') {
                            c++
                            i--
                        }

                        if (c > 0 && c % 4 == 0) {
                            text = text.slice(0 until caretPosition - 3) + text.slice(caretPosition until text.length)
                        }
                    }
                    '	' -> {
                        text = text.slice(0 until caretPosition - 1) + "    " + text.slice(caretPosition until text.length)
                        caretPosition = oldCaretPosition + 3
                    }
                }
            }

            override fun keyReleased(event: KeyEvent) {
                if (event.keyCode == KeyEvent.VK_ENTER && event.modifiersEx == 0) {
                    if (enterFlag) {
                        event.consume()
                        val start: Int
                        var end: Int
                        val text: String = text
                        var caretPosition: Int = caretPosition
                        try {
                            if (text[caretPosition] == NEW_LINE) {
                                caretPosition--
                            }
                        } catch (e: IndexOutOfBoundsException) {
                        }
                        start = text.lastIndexOf(NEW_LINE, caretPosition) + 1
                        end = start
                        try {
                            if (text[start] != NEW_LINE) {
                                while (end < text.length && Character.isWhitespace(text[end]) && text[end] != NEW_LINE) {
                                    end++
                                }
                                if (end > start) {
                                    document.insertString(
                                            getCaretPosition(),
                                            NEW_LINE.toString() + text.substring(start, end),
                                            null
                                    )
                                } else {
                                    document.insertString(getCaretPosition(), NEW_LINE.toString(), null)
                                }
                            } else {
                                document.insertString(getCaretPosition(), NEW_LINE.toString(), null)
                            }
                        } catch (e: IndexOutOfBoundsException) {
                            try {
                                document.insertString(getCaretPosition(), NEW_LINE.toString(), null)
                            } catch (e1: BadLocationException) {
                                e1.printStackTrace()
                            }
                        } catch (e: BadLocationException) {
                            e.printStackTrace()
                        }
                    }
                }
            }
        })
    }
}