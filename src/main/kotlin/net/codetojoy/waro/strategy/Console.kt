
package net.codetojoy.waro.strategy

import net.codetojoy.waro.domain.Hand

import net.codetojoy.waro.log
import net.codetojoy.waro.logBanner

import java.util.*

class Console : Strategy {

    override fun selectCard(prizeCard: Int, hand: Hand, maxCard: Int): Int {
        var bid: Int? = null

        "".logBanner()
        "Prize card : $prizeCard".log()
        "Your hand  : $hand".log()

        var ok = false
        val scanner = Scanner(System.`in`)

        while (!ok) {
            "Enter your bid: ".log()
            bid = scanner.nextInt()

            if (hand.contains(bid)) {
                ok = true
            }
        }

        return bid!!
    }
}
