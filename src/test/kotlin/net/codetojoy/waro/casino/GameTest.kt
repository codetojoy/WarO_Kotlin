
package net.codetojoy.waro.casino

import net.codetojoy.waro.domain.*
import net.codetojoy.waro.strategy.*

import kotlin.test.assertEquals
import org.junit.*

class GameTestSource {
    val game = Game(true)
    val strategy = PopCard()
    val numCards = 12
    val maxCard = numCards

    val p1 = Player("Phil H", strategy, maxCard)
    val p2 = Player("Daniel N", strategy, maxCard)
    val p3 = Player("Doyle B", strategy, maxCard)

    val players = mutableListOf(p1, p2, p3)

    @Test fun testPlayGame() {
        val kitty = mutableListOf(4,6,8)

        p1.hand = mutableListOf(1,11,3)
        p2.hand = mutableListOf(5,7,12)
        p3.hand = mutableListOf(10,2,9)

        val table = Table(players, kitty)

        // test
        val winner = game.playGame(table)

        assertEquals("Daniel N", winner.name)
        assertEquals(1, winner.playerStats.numGamesWon)
        assertEquals(1, winner.playerStats.numRoundsWon)
        assertEquals(8, winner.playerStats.total)
    }
}
