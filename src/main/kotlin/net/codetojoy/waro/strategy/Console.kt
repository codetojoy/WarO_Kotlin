
package net.codetojoy.waro.strategy

import net.codetojoy.waro.Log

import java.util.*

class Console : Strategy {

    override fun selectCard(prizeCard: Int, hand: List<Int>, maxCard: Int): Int {
        var bid: Int? = null

        Log.log("\nCard in play is $prizeCard")
        Log.log("\nYour hand is $hand")
        Log.log("")

        var ok: Boolean = false
        val scanner: Scanner = Scanner(System.`in`)

        while (!ok) {
            Log.log("Enter your bid: ")
            bid = scanner.nextInt()

            if (hand.contains(bid)) {
                ok = true
            }
        }

        return bid!!
    }
}
