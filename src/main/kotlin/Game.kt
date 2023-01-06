import java.io.File

fun main() {
    println("Let's play hangman!")
    val file = "hangman.txt"
    val game = File(file).readLines()
    val wordToGuess = game.random()
    game(wordToGuess)
}

private fun game(wordToGuess: String) {
    val alreadyGuessed = mutableListOf<String>()
    val correctGuesses =  MutableList(wordToGuess.length){" "}
    var guessesCount = 0
    var guess = ""
    var correct = 0
    println(guesses(guessesCount))
    var gameEnded = false
    while (!gameEnded) {
        println()
        println("Guess a letter.")
        guess = readln()
        when  {
            guess.length != 1 -> println("Enter one letter at time.")
            alreadyGuessed.contains(guess) -> {
                println("You already guessed that letter! Here is what you've guessed:")
                println(alreadyGuessed.joinToString())
                println(guesses(guessesCount))
            }
            !wordToGuess.contains(guess) -> {
                println("Your letter isn't here.")
                alreadyGuessed.add(guess)
                guessesCount++
                println(guesses(guessesCount))
                if (guessesCount > 5) {
                    println("")
                } else {
                    println("Guess again.")
                }
            }
            wordToGuess.contains(guess) -> {
                correct++
                print("Word:")
                correctGuesses.add(wordToGuess.indexOf(guess), guess)
                println(correctGuesses.joinToString(" "))
                println("Great guess! Guess another!")
                println(guesses(guessesCount))
            }

        }

        gameEnded = guessesCount > 5 || correct == wordToGuess.length
    }
    if (guessesCount > 5) {
        println("Game ended you didnt win")
        println("The word was $wordToGuess")
    } else if (correct == wordToGuess.length) {
        println("You win")
        println("The word was $wordToGuess")
    }

}


private fun guesses(guess: Int) = when (guess) {
    0 -> {
        """
            _________
            |	 |
            |
            |
            |
            |
            |________
        """.trimIndent()
    }
    1 ->  {
        """
            _________
            |	 |
            |	 O
            |
            |
            |
            |________
        """.trimIndent()
    }

    2 -> {
        """ _________
            |	 |
            |	 O
            |    | 
            |    |
            |
            |________
        """.trimIndent()
    }

    3 -> {
        """
            _________
            |	 |
            |	 O
            |	\|
            |	 |
            |
            |________
        """.trimIndent()
    }

    4 -> {
        """
            _________
            |	 |
            |	 O
            |	\|/
            |	 |
            |
            |________
        """.trimIndent()
    }
    5 -> {
        """
        _________
        |	 |
        |	 O
        |	\|/
        |	 |
        |	/ 
        |________
    """.trimIndent()
    }
    else -> {
        """
            _________
            |	 |
            |	 O
            |	\|/
            |	 |
            |	/ \ 
            |________
        """.trimIndent()
    }
}
