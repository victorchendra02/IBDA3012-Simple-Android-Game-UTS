package id.ac.ibda.pads.proyekuts

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import id.ac.ibda.pads.proyekuts.databinding.FragmentHangmanBinding

class HangmanFragment : Fragment() {

    private lateinit var binding: FragmentHangmanBinding

    private val arrayOfWord = arrayOf(
        "Lion", "Kangaroo", "Flamingo"
    )

    private val realAnswer = arrayOfWord.random().uppercase()
    private val hidden = "_".repeat(realAnswer.length)
    private var usedLetters = arrayOf<String>()
    private var incorrectGuesses = 0
    private val maxIncorrectGuesses = 7

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHangmanBinding.inflate(inflater, container, false)
        val view = binding.root
        val viewModel = ViewModelProvider(requireActivity())[myViewModel::class.java]


        // 1
        if (viewModel.isHasBeenInit == true) { surviveConfiguration(viewModel) }

        if (viewModel.isHasBeenInit == false) { initializeGame(viewModel) }


        binding.buttonCheck.setOnClickListener {
            check_guesses(hidden, realAnswer, viewModel)
            binding.tebakkan.setText("")
        }
        return view
    }

    private fun initializeGame(viewModel: myViewModel) {
        viewModel.hangmanAnswer = realAnswer
        var i = 0;
        while (i < realAnswer.length) {
            viewModel.currentAnswer += "_"
            i++
        }

        binding.hiddenWord.text = hidden
        binding.numberGuessed.text = "Number guessed: 0/$maxIncorrectGuesses"
        viewModel.isHasBeenInit = true
    }

    private fun surviveConfiguration(viewModel: myViewModel) {
        val temp = viewModel.iDontKnowWhattoNamedIt()
        usedLetters = temp[2] as Array<String>
        binding.letterUsed.text = usedLetters.joinToString(" ")

        var currentAnswer = temp[1] as MutableList<String>
        var newCA = ""
        for (i in currentAnswer){ newCA += i.toString() }
        binding.hiddenWord.text = newCA

        incorrectGuesses = temp[3] as Int
        binding.numberGuessed.text = "Number guessed: $incorrectGuesses/$maxIncorrectGuesses"

    }

    fun check_guesses(hidden: String, realAnswer: String, viewModel: myViewModel) {

        // Masih ada jatah tebak
        if (incorrectGuesses != maxIncorrectGuesses) {

            // Masih ada yang belum ditebak
            if ("_" in viewModel.currentAnswer) {
                val tebakkannya = binding.tebakkan.text.toString().uppercase()

                // Salah tebak
                if (!realAnswer.contains(tebakkannya)) {

                    // Karakter belum pernah ditebak
                    if (tebakkannya !in usedLetters) {
                        usedLetters += tebakkannya
                        binding.letterUsed.text = usedLetters.joinToString(separator = " ")

                        viewModel.usedLetters = usedLetters

                        incorrectGuesses++
                        viewModel.incorrectGuesses = incorrectGuesses
                    }

                    // Karakter sudah pernah ditebak
                    else {
                        println("DO NOTHING!")
                    }
                }

                // Benar tebak
                else {
                    var i = 0
                    var temp = viewModel.hangmanAnswer.toString()

                    while (i < temp.length) {
                        if (temp[i].toString() == tebakkannya) {
                            viewModel.currentAnswer[i] = tebakkannya
                        }
                        i++
                    }
                    binding.hiddenWord.text = viewModel.currentAnswer.joinToString(separator = "")
                }
                binding.numberGuessed.text = "Number guessed: $incorrectGuesses/$maxIncorrectGuesses"
            }
            else {
                println("GAME IS DONE")
                println("YOU WIN")
            }
        }

        // Jatah tebak habis
        else {
            println("GAME OVER")
            println("YOU LOSE")
        }

        if (incorrectGuesses == maxIncorrectGuesses) {
            binding.buttonCheck.text = "RESULT"
            binding.tebakkan.visibility = View.GONE

            val navController = view?.findNavController()
            navController?.navigate(R.id.action_hangmanFragment_to_hangman_result)
        }

        if ("_" !in viewModel.currentAnswer) {
            viewModel.hangmanResult = true

            binding.buttonCheck.text = "RESULT"
            binding.tebakkan.visibility = View.GONE

            val navController = view?.findNavController()
            navController?.navigate(R.id.action_hangmanFragment_to_hangman_result)
        }
    }

}