package me.mafrans.soloadventure.editor

import me.mafrans.soloadventure.models.DBGame

fun main() {
    val game = DBGame.first()
    MapEditor(game)
}