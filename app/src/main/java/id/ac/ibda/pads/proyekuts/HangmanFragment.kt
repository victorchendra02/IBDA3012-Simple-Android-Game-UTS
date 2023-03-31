package id.ac.ibda.pads.proyekuts

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import id.ac.ibda.pads.proyekuts.databinding.FragmentHangmanBinding

class HangmanFragment : Fragment() {

    private lateinit var binding: FragmentHangmanBinding

    private val arrayOfWord = listOf<String>(
        "Lion",
        "Penguin",
        "Elephant",
        "Dolphin",
        "Kangaroo"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHangmanBinding.inflate(inflater, container, false)
        val view = binding.root
        val viewModel = ViewModelProvider(requireActivity())[myViewModel::class.java]




        var realAnswer = arrayOfWord.random()
        val unk = "_ ".repeat(realAnswer.length)
        viewModel.soalHangman = realAnswer
        binding.questWord.text = unk



        binding.buttonCheck.setOnClickListener {
            check_guesses(realAnswer)
        }



        return view
    }

    fun check_guesses(realAnswer: String) {
        var quessAnswer = binding.tebakkan.text.toString().uppercase()
        Log.d("AAAA", quessAnswer.toString())


//        for (i in 0 until realAnswer.length) {
//
//        }

    }

}