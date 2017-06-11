
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

    fun buildPlayer(name: String, strategyStr: String, maxCard: Int): Player {
        var thisStrategy: Strategy? 

        val upperStrategyStr: String = strategyStr.toUpperCase()

        if (upperStrategyStr == MAX_CARD) {
            thisStrategy = MaxCard()
        } else if (upperStrategyStr == MIN_CARD) {
            thisStrategy = MinCard()
        } else if (upperStrategyStr == CONSOLE) {
            thisStrategy = net.codetojoy.waro.strategy.Console()
        } else if (upperStrategyStr == POP_CARD) {
            thisStrategy = PopCard()
        } else {
            throw IllegalStateException("unknown strategy: " + strategyStr)
        }

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
            throw e
            // throw IllegalArgumentException("illegal json config")
        }
        
        return config
    }
}
