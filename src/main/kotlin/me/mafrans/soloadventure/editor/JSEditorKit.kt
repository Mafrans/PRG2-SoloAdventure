package me.mafrans.soloadventure.editor

import javax.swing.text.StyledEditorKit
import javax.swing.text.ViewFactory

class JSEditorKit : StyledEditorKit() {
    val jsViewFactory: ViewFactory = JSViewFactory();

    override fun getViewFactory(): ViewFactory {
        return jsViewFactory
    }

    override fun getContentType(): String {
        return "text/javascript"
    }
}
