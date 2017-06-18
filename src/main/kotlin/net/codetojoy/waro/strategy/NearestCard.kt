
package net.codetojoy.waro.strategy

import net.codetojoy.waro.domain.Hand

class NearestCard : Strategy {

override 
fun selectCard(prizeCard: Int, hand: Hand, maxCard: Int) = 
   hand.cards.minBy { Math.abs(prizeCard - it) } !! 
}
