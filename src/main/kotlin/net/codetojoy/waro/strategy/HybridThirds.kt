
package net.codetojoy.waro.strategy

import net.codetojoy.waro.domain.Hand

class HybridThirds(val high:Strategy, val mid:Strategy, val low:Strategy) : Strategy {
    enum class Range {
        highRange,
        midRange,
        lowRange
    }

    override fun selectCard(prizeCard: Int, hand: Hand, maxCard: Int) = 
        when(getRange(prizeCard, maxCard)) {
            Range.highRange -> high.selectCard(prizeCard, hand, maxCard)
            Range.midRange -> mid.selectCard(prizeCard, hand, maxCard)
            else -> low.selectCard(prizeCard, hand, maxCard)
        }

    /*
    override 
    fun selectCard(prizeCard: Int, hand: List<Int>, maxCard: Int): Int {
        var result = -1
                
        if (prizeCard > (maxCard * (2/3.0)) ) {
            println("TRACER high ${prizeCard} ${maxCard}");
            result = high.selectCard(prizeCard, hand, maxCard)
        } else if (prizeCard > (maxCard/3.0) ) {
            println("TRACER mid");
            result = mid.selectCard(prizeCard, hand, maxCard)
        } else {
            println("TRACER low");
            result = low.selectCard(prizeCard, hand, maxCard)            
        }
        
        return result        
    } 
    */

    internal fun getRange(prize: Int, max: Int): Range {
        var result = Range.lowRange

        if (prize > (max * (2/3.0)) ) {
            result = Range.highRange
        } else if (prize > (max/3.0) ) {
            result = Range.midRange
        }

        return result
    }
}
