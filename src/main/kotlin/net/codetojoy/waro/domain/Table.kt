
package net.codetojoy.waro.domain

data class Table(var players: List<Player>, var kitty: List<Int>) {
    fun assertTotals() {
        val playerTotal = players.sumBy { p -> p.playerStats.total } 
        val roundsTotal = players.sumBy { p -> p.playerStats.numRoundsWon } 
                
        assert(kitty.sum() == playerTotal)
        assert(kitty.size == roundsTotal)        
    }
}
