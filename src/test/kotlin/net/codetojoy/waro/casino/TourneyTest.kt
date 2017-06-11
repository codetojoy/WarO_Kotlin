
package net.codetojoy.waro.casino

import net.codetojoy.waro.domain.*
import net.codetojoy.waro.strategy.*
import net.codetojoy.waro.Log

import kotlin.test.assertEquals
import org.junit.*

class TourneyTestSource {
    val strategy = PopCard()
    val numCards = 12 
    val maxCard = numCards

    val p1 = Player("Phil H", strategy, maxCard)
    val p2 = Player("Daniel N", strategy, maxCard)
    val p3 = Player("Doyle B", strategy, maxCard)

    val players = mutableListOf(p1, p2, p3)

    init {
        Log.setTestMode()
    }

    @Test fun testPlayGame() {
        val numGames = 1
        val isVerbose = true
        val tourney = Tourney(players, numGames, numCards, isVerbose)

        // test
        tourney.playGames()
    }
}
