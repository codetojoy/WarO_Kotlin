package net.codetojoy.waro.domain

class PlayerStats() {
    var total:Int
    var numGamesWon: Int
    var numRoundsWon:Int

    init {
        total = 0
        numGamesWon = 0
        numRoundsWon = 0
    }

    fun clear() {
        total = 0
        numRoundsWon = 0
    }
}
