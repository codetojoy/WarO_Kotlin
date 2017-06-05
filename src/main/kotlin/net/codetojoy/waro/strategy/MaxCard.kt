
package net.codetojoy.waro.strategy

class MaxCard : Strategy {
    override fun selectCard(prizeCard: Int, hand: List<Int>, maxCard:Int): Int {
        val result = hand.max() ?: -1 
         
        return result
    }
}
