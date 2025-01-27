package killua.dev

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.help
import com.github.ajalt.clikt.parameters.options.option
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import twitter4j.TwitterException

class Execute : CliktCommand(
    name = "execute",
    help = "Kill!"
) {
    private val accessToken by option(metavar = "KEY").help("OAuth access token")
    private val accessSecret by option(metavar = "KEY").help("OAuth access token secret")
    override fun run() = runBlocking {
        val (token, secret) = if (accessToken == null || accessSecret == null) {
            readCredentialsFromFile()
                ?: throw IllegalArgumentException("No valid credentials found. Run auth first.")
        } else {
            accessToken to accessSecret
        }
        val twitter = initializeTwitterClient(token, secret)!!
        val twitterV1 = twitter.v1()
        val users = twitterV1.users()
        val list = loadSinnersIdsFromFile().toSet() - getMyBlocked(twitterV1) - getMyFollowings(twitterV1) - getMyFollowers(twitterV1)
        println("Sinners: ${list.size} are waiting for death.")
        for (userId in list) {
            println("CurrentSinner: $userId")
            try {
                users.createBlock(userId)
            }catch(e: TwitterException){
                if(e.errorCode == 50){
                    println("User not found")
                }
            }
            delay(6000)
        }

    }
}