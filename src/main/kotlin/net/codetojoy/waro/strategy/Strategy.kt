
package net.codetojoy.waro.strategy

import net.codetojoy.waro.domain.Hand

interface Strategy {
    fun selectCard(prizeCard: Int, hand: Hand, maxCard: Int): Int
}
