
package net.codetojoy.waro

import net.codetojoy.waro.domain.*
import net.codetojoy.waro.strategy.*

import com.beust.klaxon.*
import java.io.*

class Config {
    var numGames: Int
    var numCards: Int
    var players: MutableList<Player>

    val CONSOLE: String = "CONSOLE";
    val MAX_CARD: String = "MAXCARD";
    val MIN_CARD: String = "MINCARD";
    val POP_CARD: String = "POPCARD";
   
    init {
        numGames = 0
        numCards = 0
        players = mutableListOf() 
    }

    companion object {
        var logDelay = false
    }

    fun buildStrategy(strategyStr: String) = when(strategyStr.toUpperCase()) {
        CONSOLE -> net.codetojoy.waro.strategy.Console()
        MAX_CARD -> MaxCard()
        MIN_CARD -> MinCard()
        POP_CARD -> PopCard()
        else -> throw IllegalStateException("unknown strategy: " + strategyStr)
    }

    fun buildPlayer(name: String, strategyStr: String, maxCard: Int): Player {
        val thisStrategy = buildStrategy(strategyStr) 

        return Player(name, thisStrategy, maxCard)
    }

    fun buildPlayers(playerArray: JsonArray<JsonObject>, maxCard: Int): MutableList<Player> {
        val players: MutableList<Player> = mutableListOf()

        for (playerObject in playerArray) {
            val name = playerObject.string("name")!!
            val strategy = playerObject.string("strategy")!!
            val player = buildPlayer(name, strategy, maxCard)
            players.add(player)
        }

        return players
    }

    fun buildFromFile(jsonFile: String): Config {
        val jsonStr = File(jsonFile).readText()
        return buildFromString(jsonStr)
    }

    fun buildFromString(jsonStr: String): Config {
        val config = Config()

        try {
            val parser: Parser = Parser()
            val s: StringBuilder = StringBuilder(jsonStr)
            val json: JsonObject = parser.parse(s) as JsonObject
            var playerArray: JsonArray<JsonObject> = json.array("players")!!

            config.numGames = json.int("numGames")!!
            config.numCards = json.int("numCards")!!
            config.players = buildPlayers(playerArray, config.numCards)
        } catch (e: Exception) {
            throw IllegalArgumentException("illegal json config")
        }
        
        return config
    }
}
