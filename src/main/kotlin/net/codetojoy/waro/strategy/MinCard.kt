
package net.codetojoy.waro.strategy

import net.codetojoy.waro.domain.Hand

class MinCard : Strategy {

override 
fun selectCard(prizeCard: Int, hand: Hand, maxCard: Int) = hand.cards.min()!!

}
