package me.george.cgol

import com.beust.jcommander.*
import com.fasterxml.jackson.databind.ObjectMapper
import java.io.File

/**
 * Hit Ctrl-C to kill
 */
fun main (args: Array<String>) {
    val opts = GameParams()
    val ctx = JCommander(opts)
    ctx.parse(*args)

    val board = ObjectMapper().readValue<BoardDto>(File(opts.initFile), BoardDto::class.java)
    val gameState = createGameState(board)
    val pause = opts.getPause()
    display(gameState)
    while (true) {
        pause()
        gameState.cycle()
        display(gameState)
    }
}

fun display(gameState: GameState) {
    for (idxRow in 0 until gameState.nSizeRows) {
        for (idxCol in 0 until gameState.nSizeCols) {
            print( if (gameState.getCell(idxRow, idxCol) != 0.toByte()) '+' else ' ')
        }
        println()
    }
    println()
}