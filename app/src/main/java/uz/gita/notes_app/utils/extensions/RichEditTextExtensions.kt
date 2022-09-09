package uz.gita.notes_app.utils.extensions

import android.graphics.Color
import jp.wasabeef.richeditor.RichEditor

// Created by Jamshid Isoqov an 9/6/2022

fun RichEditor.changeType(type: Int) {
    this.removeFormat()
    when (type) {
        1 -> this.undo()
        2 -> this.redo()
        3 -> this.setBold()
        4 -> this.setUnderline()
        5 -> this.setSubscript()
        6 -> this.setSuperscript()
        7 -> this.setStrikeThrough()
        8 -> this.setUnderline()
        in 9..14 -> this.setHeading(type + 1 - 9)
        15 -> this.setEditorFontColor(Color.GREEN)
        17 -> this.setIndent()
        18 -> this.setOutdent()
        19 -> this.setAlignLeft()
        20 -> this.setAlignCenter()
        21 -> this.setAlignRight()
        22 -> this.setBullets()
        23 -> this.setNumbers()
        24 -> this.setBlockquote()
        //25->this.insertLink()

    }
}