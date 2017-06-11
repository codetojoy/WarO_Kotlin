
package net.codetojoy.waro

import net.codetojoy.waro.casino.*
import net.codetojoy.waro.domain.*
import net.codetojoy.waro.strategy.*

fun main(args: Array<String>) {
    val configJson = args[0]
    val config = Config().buildFromFile(configJson)

    val numCards = config.numCards
    val numGames = config.numGames
    val players = config.players
    val isVerbose = true

    /*
    val strategy = PopCard()
    val numCards = 12 
    val maxCard = numCards

    val p1 = Player("Phil H", strategy, maxCard)
    val p2 = Player("Daniel N", strategy, maxCard)
    val p3 = Player("Doyle B", strategy, maxCard)

    val players = listOf(p1, p2, p3)

    val numGames = 1
    */

    val tourney = Tourney(players, numGames, numCards, isVerbose)

    tourney.playGames()
}
