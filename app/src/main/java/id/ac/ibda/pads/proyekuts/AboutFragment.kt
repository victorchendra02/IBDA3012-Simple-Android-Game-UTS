package id.ac.ibda.pads.proyekuts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import id.ac.ibda.pads.proyekuts.databinding.FragmentAboutBinding
import id.ac.ibda.pads.proyekuts.databinding.FragmentHangmanBinding

class AboutFragment : Fragment() {

    private lateinit var binding: FragmentAboutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAboutBinding.inflate(inflater, container, false)
        val view = binding.root
        val viewModel = ViewModelProvider(requireActivity())[myViewModel::class.java]

        return view
    }
}