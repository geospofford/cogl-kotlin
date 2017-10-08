package me.george.cgol

import com.beust.jcommander.Parameter

class GameParams {
    @Parameter(names = arrayOf("-init"), required = true)
    var initFile : String = ""

    @Parameter(names = arrayOf("-pauseMs"), required = false)
    var pauseMs : Int = 0

    fun getPause(): () -> Unit {
        val thePause : Long = pauseMs.toLong()
        return if
            (thePause == 0L) { -> } else { -> Thread.sleep(thePause)}
    }
}