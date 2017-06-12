
package net.codetojoy.waro.strategy

import net.codetojoy.waro.log

import java.util.*

class Console : Strategy {

    override fun selectCard(prizeCard: Int, hand: List<Int>, maxCard: Int): Int {
        var bid: Int? = null

        "\nCard in play is $prizeCard".log()
        "\nYour hand is $hand\n".log()

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
