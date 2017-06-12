
package net.codetojoy.waro.casino

import net.codetojoy.waro.domain.*
import net.codetojoy.waro.*

class Tourney(val players: List<Player>, val numGames: Int, val numCards: Int) {

    fun playGames() {
        for (i in 1..numGames) {
            playGame(numCards)
        }

        "".logBanner()
        "Tourney summary:".log()

        players.forEach { p ->
            "${p.name} has ${p.playerStats.numGamesWon} wins over ${numGames} games".log()
        }
    }

    // ------- internal

    internal fun playGame(numCards: Int) {
        val game = Game()

        game.playGame(numCards, players)

        players.forEach { it.clear() }
    }
}
