
package net.codetojoy.waro.strategy

class MinCard : Strategy {
    override fun selectCard(prizeCard: Int, hand: List<Int>, maxCard: Int): Int {
        val result = hand.min() ?: -1
        return result
    }
}
