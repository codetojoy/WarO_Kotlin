
package net.codetojoy.waro.strategy

class MaxCard : Strategy {

override 
fun selectCard(prizeCard: Int, hand: List<Int>, maxCard: Int) = hand.max() ?: -1 
         
}
