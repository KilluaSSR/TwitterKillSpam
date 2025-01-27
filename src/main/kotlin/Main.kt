package killua.dev

import com.github.ajalt.clikt.core.NoOpCliktCommand
import com.github.ajalt.clikt.core.subcommands

fun main(vararg args: String) {

    NoOpCliktCommand(name = "<FileName.jar>")
        .apply {
            subcommands(AuthCommand(), Execute())
        }
        .main(args)
}
