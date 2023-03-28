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
        mutableListOf("Is the capital of Australia Sydney?", false),
        mutableListOf("Is a tomato a fruit or a vegetable?", true),
        mutableListOf("Is the Mona Lisa painting located in Rome?", false),
        mutableListOf("Is the Statue of Liberty located in Los Angeles?", false),
        mutableListOf("Is the currency used in Japan the yen?", true),
        mutableListOf("Is a kilometer longer than a mile?", false),
        mutableListOf("Is the human heart located in the chest?", true),
        mutableListOf("Is the Eiffel Tower located in Germany?", false),
        mutableListOf("Is the planet Neptune the closest planet to the sun?", false),
        mutableListOf("Is the Earth flat?", false),
        mutableListOf("Is the sun a planet?", false),
        mutableListOf("Is the tallest mountain in the world Mount Everest?", true),
        mutableListOf("Is the country of Brazil located in Africa?", false),
        mutableListOf("Is the color of a flamingo's feathers naturally pink?", true),
        mutableListOf("Is the human body made up of more water or more bone?", true),
        mutableListOf("Is the largest ocean in the world the Atlantic Ocean?", false),
        mutableListOf("Is a triangle with three equal sides called an isosceles triangle?", false),
        mutableListOf("Is the planet Saturn the fifth planet from the sun?", false),
        mutableListOf("Is the famous novel 'To Kill a Mockingbird' written by Harper Lee?", true),
        mutableListOf("Is the planet Earth the third planet from the sun?", true),
        mutableListOf("Is the smallest country in the world Monaco?", false),
        mutableListOf("Is the currency used in Mexico the peso?", true),
        mutableListOf("Is the tallest mammal in the world the elephant?", false),
        mutableListOf("Is the chemical symbol for gold \"Au\"?", true),
        mutableListOf("Is 0 an even number?", true),
        mutableListOf("The square root of 16 is 4", true),
        mutableListOf("Is 1 a prime number?", false),
        mutableListOf("Is the sum of two even numbers always an even number?", true),
        mutableListOf("Apes cannot laugh", false),
        mutableListOf("Bananas grow upside down", true),
        mutableListOf("The unicorn is the national animal of Scotland", true),
        mutableListOf("There are five Oceans in the world", true),
        mutableListOf("The Titanic ship cost more to build than The Movie cost to make", false),
        mutableListOf("Mushrooms need sunlight to grow", false),
        mutableListOf("Penguins cant be found on the continent of Africa", false),
        mutableListOf("A snail can sleep for up to 3 months", false),
        mutableListOf("Cinderella was the first Disney princess", false),
        mutableListOf("Human teeth are as strong as the teeth of a mature shark", true),
        mutableListOf("If you add the two numbers on the opposite sides of a dice together, the answer is always 7", true),
        mutableListOf("The Pacific Ocean is bigger than the moon", true),
        mutableListOf("Over 90% of sea turtles in the Ocean are found to have consumed plastic", true),
        mutableListOf("Is the element with the chemical symbol 'Na' sodium?", true)
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