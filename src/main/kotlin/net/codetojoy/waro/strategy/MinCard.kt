
package net.codetojoy.waro.strategy

class MinCard : Strategy {

override 
fun selectCard(prizeCard: Int, hand: List<Int>, maxCard: Int) = hand.min() ?: -1

}
