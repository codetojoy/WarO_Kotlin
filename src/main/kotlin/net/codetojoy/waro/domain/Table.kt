
package net.codetojoy.waro.domain

data class Table(var players: List<Player>, var kitty: Hand) {
    fun assertTotals() {
        val playerTotal = players.sumBy { p -> p.playerStats.total } 
        val roundsTotal = players.sumBy { p -> p.playerStats.numRoundsWon } 
                
        assert(kitty.cards.sum() == playerTotal)
        assert(kitty.cards.size == roundsTotal)        
    }
}
