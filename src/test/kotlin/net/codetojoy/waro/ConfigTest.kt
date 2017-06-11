
package net.codetojoy.waro

import net.codetojoy.waro.domain.*
import net.codetojoy.waro.strategy.*

import kotlin.test.*
import org.junit.Test

class ConfigTestSource {
    val config: Config = Config() 

    @Test fun testBuild_NoPlayers() {
        val jsonInput = "{ \"numGames\": 2, \"numCards\": 60, \"players\": [] }"
        
        // test
        val result = config.buildFromString(jsonInput)
        
        assertEquals(2, result.numGames)
        assertEquals(60, result.numCards)
    }    

    @Test fun testBuild_OnePlayer() {
        val jsonInput = "{ \"numGames\": 1, \"numCards\": 10, \"players\": [{ \"name\": \"Brahms\", \"strategy\": \"maxCard\"}] }"
        
        // test
        val result = config.buildFromString(jsonInput)
        
        assertEquals(1, result.numGames)
        assertEquals(10, result.numCards)
        assertEquals(1, result.players.size)
        assertEquals("Brahms", result.players[0].name)
        assertTrue(result.players[0].strategy is MaxCard)
    }    
}
