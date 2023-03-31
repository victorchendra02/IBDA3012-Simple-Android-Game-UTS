package id.ac.ibda.pads.proyekuts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import id.ac.ibda.pads.proyekuts.databinding.FragmentChooseGameBinding

class ChooseGame : Fragment() {

    private lateinit var binding: FragmentChooseGameBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChooseGameBinding.inflate(inflater, container, false)

        val view = binding.root
        val viewModel = ViewModelProvider(requireActivity())[myViewModel::class.java]

        // parse var to other fragment
        // (Ini cara kerjanya dia parse ke myViewModel, trus dri fragment lain baru ambil dri dia)
        // viewModel bisa dibilang sebagai penampung/wadah
        binding.showPlayerName.text = viewModel.player_name

        // QUIZ BUTTON to navigation to other fragment
        binding.startQuizButton.setOnClickListener {
            val navQuizController = view.findNavController()
            navQuizController.navigate(R.id.action_chooseGame_to_quizFragment)
        }

        // HANGMAN BUTTON to navigation to other fragment
        binding.startHangmanButton.setOnClickListener {
            val navHangmanController = view.findNavController()
            navHangmanController.navigate(R.id.action_chooseGame_to_hangmanFragment)
        }
        return view
    }
}