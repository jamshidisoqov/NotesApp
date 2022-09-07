package uz.gita.notes_app.utils.extensions

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
        in 9..14 -> this.setHeading(type+1-9)
    }
}