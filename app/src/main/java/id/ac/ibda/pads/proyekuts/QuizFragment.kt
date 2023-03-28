package id.ac.ibda.pads.proyekuts

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import id.ac.ibda.pads.proyekuts.databinding.FragmentQuizBinding

class QuizFragment : Fragment() {

    private lateinit var binding: FragmentQuizBinding
    private var rightAnswer: Boolean? = null
    private var rightAnswerCount = 0
    private var quizCount = 1
    private var maxQuestion = 10

    private val questionsAndAnswers = mutableListOf(
        mutableListOf("Is the Great Wall of China visible from space?", false),
        mutableListOf("Is the capital of Australia Sydney?", true),
        mutableListOf("Is a tomato a fruit or a vegetable?", true),
        mutableListOf("Is the Mona Lisa painting located in Rome?", false),
        mutableListOf("Is the Statue of Liberty located in Los Angeles?", false),
        mutableListOf("Is the currency used in Japan the yen?", true),
        mutableListOf("Is a kilometer longer than a mile?", false),
        mutableListOf("Is the human heart located in the chest?", true),
        mutableListOf("Is the Eiffel Tower located in Germany?", false),
        mutableListOf("Is the planet Neptune the closest planet to the sun?", false),
        mutableListOf("Is the Earth flat?", false),
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQuizBinding.inflate(inflater, container, false)
        val view = binding.root

        questionsAndAnswers.shuffle()
        showNextQuestion()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val trueButton = binding.ansTrue
        val falseButton = binding.ansFalse

        trueButton.setOnClickListener { view ->
            checkAnswer(view)
        }
        falseButton.setOnClickListener { view ->
            checkAnswer(view)
        }
    }

    fun showNextQuestion() {
        binding.qNumber.text = getString(R.string.qNumber, quizCount)

        val quiz = questionsAndAnswers[0]

        binding.questionLabel.text = quiz[0].toString()
        rightAnswer = quiz[1].toString().toBoolean()

        questionsAndAnswers.removeAt(0)

    }

    fun checkAnswer(view: View) {
        val answerBtn = view as Button
        val btnText = answerBtn.text.toString().toBoolean()

        val alertTitle: String
        if (btnText == rightAnswer) {
            alertTitle = "Correct"
            rightAnswerCount++
        } else {
            alertTitle = "Wrong"
        }
        AlertDialog.Builder(requireContext())
            .setTitle(alertTitle)
            .setMessage("Answer: $rightAnswer")
            .setPositiveButton("OK"){ dialogInterface, i ->
                checkQuizCount()
            }
            .setCancelable(false)
            .show()
    }

    fun checkQuizCount() {
        if (quizCount == maxQuestion) {
            val viewModel = ViewModelProvider(requireActivity())[myViewModel::class.java]
            viewModel.quiz_score = rightAnswerCount

            val navController = view?.findNavController()
            navController?.navigate(R.id.action_quizFragment_to_quiz_result)
        } else {
            quizCount++
            showNextQuestion()
        }
    }
}