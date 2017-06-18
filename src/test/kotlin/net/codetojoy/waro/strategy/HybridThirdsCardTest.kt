package net.codetojoy.waro.strategy

import net.codetojoy.waro.domain.Hand

import kotlin.test.assertEquals
import org.junit.Test

class HybridThirdsTestSource {
    val strategy = HybridThirds(MaxCard(), PopCard(), MinCard()) 
    val hand = Hand(listOf(11, 1, 33, 44, 22))
    val maxCard = 60

    @Test fun testSelectCard_Low() {
        val prizeCard = 10

        // test
        val result = strategy.selectCard(prizeCard, hand, maxCard)

        assertEquals(1, result)
    }
    @Test fun testSelectCard_Mid() {
        val prizeCard = 35

        // test
        val result = strategy.selectCard(prizeCard, hand, maxCard)

        assertEquals(11, result)
    }
    @Test fun testSelectCard_High() {
        val prizeCard = 50

        // test
        val result = strategy.selectCard(prizeCard, hand, maxCard)

        assertEquals(44, result)
    }
}
