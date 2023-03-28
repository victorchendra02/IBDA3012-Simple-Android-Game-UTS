package id.ac.ibda.pads.proyekuts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import id.ac.ibda.pads.proyekuts.databinding.FragmentChooseGameBinding

class ChooseGame : Fragment() {

    private lateinit var binding: FragmentChooseGameBinding
//    private lateinit var viewModel: myViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChooseGameBinding.inflate(inflater, container, false)
        val view = binding.root

        val viewModel = ViewModelProvider(requireActivity())[myViewModel::class.java]
        binding.showPlayerName.text = viewModel.player_name

        return view
    }

}