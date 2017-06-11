
package net.codetojoy.waro.strategy 

import java.util.*

class Console : Strategy {

    override fun selectCard(prizeCard: Int, hand: List<Int>, maxCard: Int): Int {
        var bid: Int? = null
        
        println("\nCard in play is $prizeCard")
        println("\nYour hand is $hand")
        println("")
        
        var ok: Boolean = false
        val scanner: Scanner = Scanner(System.`in`)
        
        while (!ok) {
            println("Enter your bid: ")
            bid = scanner.nextInt()
            
            if (hand.contains(bid)) {
                ok = true
            }            
        }
        
        return bid!!
    }
}

