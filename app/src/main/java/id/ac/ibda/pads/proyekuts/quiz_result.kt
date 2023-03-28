package id.ac.ibda.pads.proyekuts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import id.ac.ibda.pads.proyekuts.databinding.FragmentQuizResultBinding

class quiz_result : Fragment() {

    private lateinit var binding: FragmentQuizResultBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQuizResultBinding.inflate(inflater, container, false)
        val view = binding.root
        val viewModel = ViewModelProvider(requireActivity())[myViewModel::class.java]

        binding.quizScore.text = "${viewModel.quiz_score} / 10"

        binding.quizFinished.setOnClickListener {
            val navController = view.findNavController()
            navController.navigate(R.id.action_quiz_result_to_chooseGame)
        }
        return view
    }
}