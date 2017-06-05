package net.codetojoy.waro.strategy

import kotlin.test.assertEquals
import org.junit.Test

class PopCardTestSource {
    @Test fun testSelectCard() {
        val strategy = PopCard()
        val prizeCard = 10
        val hand = listOf(20, 21, 22, 23) 
        val maxCard = 60

        // test
        val result = strategy.selectCard(prizeCard, hand, maxCard)

        assertEquals(20, result)
    }
}
