package net.codetojoy.waro.strategy

import net.codetojoy.waro.domain.Hand

import kotlin.test.assertEquals
import org.junit.Test

class NearestCardTestSource {
    @Test fun testSelectCard_PrizeCardLessThanNearest() {
        val strategy = NearestCard()
        val prizeCard = 10
        val hand = Hand(listOf(1, 60, 11, 40, 19))
        val maxCard = 60

        // test
        val result = strategy.selectCard(prizeCard, hand, maxCard)

        assertEquals(11, result)
    }
    @Test fun testSelectCard_PrizeCardMoreThanNearest() {
        val strategy = NearestCard()
        val prizeCard = 24
        val hand = Hand(listOf(10, 20, 30))
        val maxCard = 60

        // test
        val result = strategy.selectCard(prizeCard, hand, maxCard)

        assertEquals(20, result)
    }
}
