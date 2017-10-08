package me.george.cgol

fun createGameState (board : BoardDto) : GameState {
    val gameState = GameState (board.nRows, board.nCols, board.rules)
    val bytes = ArrayList<List<Byte>> (board.values.size)
    for (colString in board.values) {
        val cols = ArrayList<Byte>()
        colString.asIterable().forEach(
            {c -> cols.add(if (c != '0') 1 else 0)}
        )
        bytes.add(cols)
    }
    gameState.load(bytes)
    return gameState
}
