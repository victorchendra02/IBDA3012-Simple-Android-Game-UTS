package id.ac.ibda.pads.proyekuts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import id.ac.ibda.pads.proyekuts.databinding.FragmentHangmanResultBinding

class hangman_result : Fragment() {

    private lateinit var binding: FragmentHangmanResultBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHangmanResultBinding.inflate(inflater,container,false)
        val view = binding.root
        val viewModel = ViewModelProvider(requireActivity())[myViewModel::class.java]

        if (viewModel.hangmanResult == true){
            binding.hangmanResult.text = "YOU WIN"
        }
        if (viewModel.hangmanResult == false) {
            binding.hangmanResult.text = "YOU LOSE"
        }

        binding.continueHangmangFinishButton.setOnClickListener {
            val navController = view.findNavController()

            viewModel.hangmanAnswer = ""
            viewModel.currentAnswer = mutableListOf<String>()
            viewModel.isHasBeenInit = false
            viewModel.usedLetters = arrayOf<String>()
            viewModel.incorrectGuesses = 0
            viewModel.hangmanResult =false

            navController.navigate(R.id.action_hangman_result_to_chooseGame)
        }

        return view
    }

}