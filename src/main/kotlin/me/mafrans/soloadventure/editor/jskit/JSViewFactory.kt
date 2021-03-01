package me.mafrans.soloadventure.editor.jskit

import javax.swing.text.Element
import javax.swing.text.View
import javax.swing.text.ViewFactory

class JSViewFactory : ViewFactory {
    override fun create(elem: Element?): View {
        return JSView(elem);
    }
}
