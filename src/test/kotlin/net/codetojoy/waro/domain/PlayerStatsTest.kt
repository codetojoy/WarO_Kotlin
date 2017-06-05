
package net.codetojoy.waro.domain

import kotlin.test.assertEquals
import org.junit.Test

class PlayerStatsTestSource {
    @Test fun testConstructor() {
        // test
        val playerStats = PlayerStats()
        assertEquals(0, playerStats.numGamesWon)
    }
    @Test fun testSetterGetter() {
        val playerStats = PlayerStats()
        // test
        playerStats.numGamesWon = 5150
        assertEquals(5150, playerStats.numGamesWon)
    }
    @Test fun testClear() {
        val playerStats = PlayerStats()
        playerStats.total = 5150
        // test
        playerStats.clear()
        assertEquals(0, playerStats.total)
    }
}
