
package net.codetojoy.waro.strategy

class NearestCard : Strategy {
    override fun selectCard(prizeCard: Int, hand: List<Int>, maxCard: Int): Int {
        val result = hand.minBy { Math.abs(prizeCard - it) } ?: -1
         
        return result
    }
}
