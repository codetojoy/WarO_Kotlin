
package net.codetojoy.waro

import net.codetojoy.waro.casino.*
import net.codetojoy.waro.domain.*
import net.codetojoy.waro.strategy.*

fun main(args: Array<String>) {
    val configJson = args[0]

    val config = Config
    config.buildFromFile(configJson)
    config.logDelay = true
    config.isVerbose = true

    val numCards = config.numCards
    val numGames = config.numGames
    val players = config.players

    val tourney = Tourney(players, numGames, numCards)

    tourney.playGames()
}
