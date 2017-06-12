
package net.codetojoy.waro

import net.codetojoy.waro.domain.*
import net.codetojoy.waro.strategy.*

import kotlin.test.*
import org.junit.Test

class ConfigTestSource {
    val config = Config

    @Test fun testBuildStrategy_MaxCard() {
        // test
        val result = config.buildStrategy("maxCard")
        
        assertTrue(result is MaxCard)
    }

    @Test fun testBuildStrategy_Console() {
        // test
        val result = config.buildStrategy("console")
        
        assertTrue(result is net.codetojoy.waro.strategy.Console)
    }

    @Test fun testBuild_NoPlayers() {
        val jsonInput = "{ \"numGames\": 2, \"numCards\": 60, \"players\": [] }"
        
        // test
        config.buildFromString(jsonInput)
        
        assertEquals(2, config.numGames)
        assertEquals(60, config.numCards)
    }    

    @Test fun testBuild_OnePlayer() {
        val jsonInput = "{ \"numGames\": 1, \"numCards\": 10, \"players\": [{ \"name\": \"Brahms\", \"strategy\": \"maxCard\"}] }"
        
        // test
        config.buildFromString(jsonInput)
        
        assertEquals(1, config.numGames)
        assertEquals(10, config.numCards)
        assertEquals(1, config.players.size)
        assertEquals("Brahms", config.players[0].name)
        assertTrue(config.players[0].strategy is MaxCard)
    }    
}
