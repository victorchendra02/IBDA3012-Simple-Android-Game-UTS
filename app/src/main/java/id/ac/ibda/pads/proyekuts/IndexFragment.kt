package id.ac.ibda.pads.proyekuts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import id.ac.ibda.pads.proyekuts.databinding.FragmentIndexBinding
class IndexFragment : Fragment() {

    private lateinit var binding: FragmentIndexBinding
//    private lateinit var viewModel: myViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIndexBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.continueButton.setOnClickListener {
            val playerName = binding.playerName.text.toString()
            val navController = view.findNavController()

            val viewModel = ViewModelProvider(requireActivity())[myViewModel::class.java]
            viewModel.player_name = playerName  // parsing to other fragment
            navController.navigate(R.id.action_indexFragment_to_chooseGame)  // navigate to other fragment
        }
        return view
    }
}