package net.codetojoy.waro.strategy

import net.codetojoy.waro.domain.Hand

import kotlin.test.assertEquals
import org.junit.Test

class MaxCardTestSource {
    @Test fun testSelectCard() {
        val strategy = MaxCard()
        val prizeCard = 10
        val hand = Hand(listOf(20, 21, 22, 23))
        val maxCard = 60

        // test
        val result = strategy.selectCard(prizeCard, hand, maxCard)

        assertEquals(23, result)
    }
}
