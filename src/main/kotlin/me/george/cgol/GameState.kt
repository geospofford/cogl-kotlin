package me.george.cgol

class GameState (
        val nSizeRows: Int,
        val nSizeCols: Int,
        val rules: ByteArray
) {
    val nAllocRows = nSizeRows + 2
    val nAllocCols = nSizeCols + 2

    fun boardArr (nRows: Int, nCols: Int) : Array<ByteArray> {
        return Array<ByteArray>(nRows, { _ ->  ByteArray(nCols)})
    }

    var gen0 = boardArr(nAllocRows, nAllocCols)
    var gen1 = boardArr(nAllocRows, nAllocCols)

    fun swapBoards () {
        val tmpG = gen0
        gen0 = gen1
        gen1 = tmpG
    }

    fun makeGen () {
        for (idxRow in 1 .. nSizeRows ) {
            val genRow = gen1[idxRow]
            for (idxCol in 1 .. nSizeCols) {
                genRow[idxCol] = rules[countNeighbors(gen0, idxRow, idxCol)]
            }
        }
    }

    fun load (rows: Iterable<Iterable<Byte>>) {
        var idxToRow = 1
        for (row in rows) {
            var idxToCol = 1
            for (colVal in row) {
                gen0[idxToRow][idxToCol] = colVal
                ++idxToCol
            }
            ++idxToRow
        }
    }

    fun getCell (idxRow: Int, idxCol: Int) : Byte {
        return gen0[idxRow][idxCol]
    }

    fun cycle () {
        makeGen()
        swapBoards()
    }
}

// returns the neighbor count
fun countNeighbors (board: Array<ByteArray>, idxRow: Int, idxCol: Int): Int {
    return (
            board[idxRow-1][idxCol-1] +
                    board[idxRow-1][idxCol  ] +
                    board[idxRow-1][idxCol+1] +
                    board[idxRow  ][idxCol-1] +
                    board[idxRow  ][idxCol+1] +
                    board[idxRow+1][idxCol-1] +
                    board[idxRow+1][idxCol  ] +
                    board[idxRow+1][idxCol+1]
            )
}
