import kotlin.random.Random
import java.util.Locale

fun generateSecretNumber(min: Int, max: Int): Int {
    // val secretNumber = Random.nextInt(min, max + 1)
    // return secretNumber
    // أو اختصاراً:
    return Random.nextInt(min, max + 1)
}

fun getUserGuess(): Int? { // بترجع Int? (يعني Int or null)
    print("Enter your guess: ")
    val userInput = readLine()
    return userInput?.toIntOrNull()
}

fun checkGuess(guess: Int, secret: Int): String {
    return when {
        guess > secret -> "Too high! Try guessing lower."
        guess < secret -> "Too low! Try guessing higher."
        else -> "Correct!"
    }
}

fun askPlayAgain(): Boolean {
    print("\nPlay again? (y/n): ")
    val playAgainInput = readLine()
    return playAgainInput?.lowercase(Locale.getDefault()) == "y"
}

fun main() {
    val minRange = 1
    val maxRange = 100
    val maxAttempts = 7

    do {
        val secretNumber = generateSecretNumber(minRange, maxRange)

        println("\n==========================================")
        println("=== New Game Started! ===")
        println("I'm thinking of a number between $minRange and $maxRange.")
        println("You have $maxAttempts attempts to guess it.")
        println("==========================================")

        var attempts = 0
        var guessIsCorrect = false


        while (attempts < maxAttempts && !guessIsCorrect) {
            attempts++
            println("\n--- Attempt #$attempts of $maxAttempts ---")


            val userGuess = getUserGuess()

            if (userGuess == null) {
                println("Invalid input. Please enter a number.")
                continue
            }


            val feedback = checkGuess(userGuess, secretNumber)
            println(feedback)


            if (feedback == "Correct!") {
                println("******************************************")
                println("Congratulations! It took you $attempts attempts.")
                println("******************************************")
                guessIsCorrect = true
            }
        }

        if (!guessIsCorrect) {
            println("\n------------------------------------------")
            println("Sorry, you've run out of attempts!")
            println("The secret number was $secretNumber")
            println("------------------------------------------")
        }

    } while (askPlayAgain())

    println("\nThanks for playing! Goodbye.")

}
