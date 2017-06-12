
package net.codetojoy.waro.strategy

class NearestCard : Strategy {

override 
fun selectCard(prizeCard: Int, hand: List<Int>, maxCard: Int) = 
   hand.minBy { Math.abs(prizeCard - it) } !! 
}
