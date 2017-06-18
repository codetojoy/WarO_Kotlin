
package net.codetojoy.waro.domain

import net.codetojoy.waro.strategy.*

import kotlin.test.assertEquals
import org.junit.Test

class PlayerTestSource {
    @Test fun testGetBid() {
        val strategy = PopCard()
        val player = Player("Peterborough Pete", strategy, 60)

        player.hand = Hand(listOf(10,20,30))
        
        // test
        val bid = player.getBid(9)
        
        assertEquals(10, bid.offer)
        assertEquals(player, bid.player)
        assertEquals(2, player.numCards())
    }    
}
