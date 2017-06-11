
package net.codetojoy.waro.strategy

class PopCard : Strategy {
    override 
    fun selectCard(prizeCard: Int, hand: List<Int>, maxCard: Int) = hand.first()
}
