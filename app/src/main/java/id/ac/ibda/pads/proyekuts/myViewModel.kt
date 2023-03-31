package id.ac.ibda.pads.proyekuts

import androidx.lifecycle.ViewModel

class myViewModel : ViewModel() {
    var player_name = "";
    var quiz_score = 0;

    var hangmanAnswer = ""
    var currentAnswer = mutableListOf<String>()
    var isHasBeenInit = false
    var usedLetters = arrayOf<String>()
    var incorrectGuesses = 0

    var hangmanResult = false

    fun iDontKnowWhattoNamedIt(): Array<Any> {
        return arrayOf(hangmanAnswer, currentAnswer, usedLetters, incorrectGuesses)
    }
}