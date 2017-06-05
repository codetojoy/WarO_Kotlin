
package net.codetojoy.waro.strategy

interface Strategy {
    fun selectCard(prizeCard: Int, hand: List<Int>, maxCard:Int): Int
}
