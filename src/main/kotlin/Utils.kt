package killua.dev

import twitter4j.Twitter
import twitter4j.v1.RateLimitStatus
import java.io.File
import java.io.FileReader
import java.io.InputStream
import java.util.Properties
import java.util.Scanner

const val RED_TEXT = "\u001B[31m"
const val RESET_TEXT = "\u001B[0m"
const val MAX_RETRY = 5
const val apiKey = "3rJOl1ODzm9yZy63FACdg"
const val apiSecret = "5jPoQ5kQvMJFDYRNE8bQ4rHuds4xJqhvgNJM4awaE8"
lateinit var internalKeywords: Set<String>
var rateLimitStatus: RateLimitStatus = RateLimit.Unlimited

val configFile = File("blocking_rules.json")
val followersIdsFile = File("followers_ids_cache.txt")
val followingsIdsFile = File("followings_ids_cache.txt")
val credentialFile = File("twitter_credentials.properties")
val sinnerFile = File("sinner.txt")

fun readLineWithPrompt(prompt: String): String {
    print(prompt)
    return Scanner(System.`in`).nextLine()
}

fun initialize(keywordsFileName: String) {
    internalKeywords = loadKeywords(keywordsFileName)
}

private fun loadKeywords(fileName: String): Set<String> {
    val inputStream: InputStream = object {}.javaClass.classLoader.getResourceAsStream(fileName)
        ?: throw IllegalArgumentException("Resource not found: $fileName")
    return inputStream.bufferedReader().use {
        it.readLines()
            .map { line -> line.trim() }
            .filter { line -> line.isNotEmpty() }
            .toSet()
    }
}

fun isKeywordPresent(text: String): List<String> {
    if (!::internalKeywords.isInitialized) {
        throw IllegalStateException("Keywords have not been initialized")
    }
    return internalKeywords.filter { text.contains(it, ignoreCase = true) }
}

fun readCredentialsFromFile(): Pair<String, String>? {
    if (!credentialFile.exists()) {
        println("Credentials file not found.")
        return null
    }
    val properties = Properties().apply {
        load(FileReader(credentialFile))
    }

    val token = properties.getProperty("accessToken")
    val secret = properties.getProperty("accessTokenSecret")

    if (token.isNullOrBlank() || secret.isNullOrBlank()) {
        println("Invalid credentials in the file.")
        return null
    }

    return token to secret
}

fun deleteCredentialsFile() {
    if (credentialFile.exists()) {
        if (credentialFile.delete()) {
            println("Credentials file is invalid. Re-Auth please.")
        } else {
            println("Failed to delete credentials file.")
        }
    }
}

fun loadSinnersIdsFromFile(): List<Long> {
    return if (sinnerFile.exists()) {
        sinnerFile.readLines().mapNotNull { it.toLongOrNull() }
    } else {
        emptyList()
    }
}

fun initializeTwitterClient(token: String?, secret: String?): Twitter? {
    return try {
        Twitter.newBuilder()
            .oAuthAccessToken(token, secret)
            .oAuthConsumer(apiKey, apiSecret)
            .build()
    } catch (e: Exception) {
        println("Failed to initialize Twitter client: ${e.message}")
        deleteCredentialsFile()
        null
    }
}