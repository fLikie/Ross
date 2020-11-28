package com.example.rosseti.ui

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rosseti.R
import com.example.rosseti.application.CategoryOfDigitalTransf
import com.example.rosseti.application.Form
import com.example.rosseti.application.qa.Answer
import com.example.rosseti.application.qa.Question
import com.example.rosseti.databinding.FragmentCreateAppBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayDeque
import javax.inject.Inject

class CreateApplicationFragment : Fragment() {

    @Inject
    lateinit var form: Form

    private lateinit var binding: FragmentCreateAppBinding

    val questionsHARDCODED = ArrayDeque(
        listOf(
            Question.ShortNameQuestion,
            Question.CategoryQuestion(Answer.MultipleChoice(CategoryOfDigitalTransf.values().map { Triple(it.id, it.description, false) })),
            Question.IssueDescriptionQuestion,
            Question.IssueSolutionQuestion,
            Question.IssueEffectQuestion,
            Question.ExpendituresQuestion,
            Question.StepsQuestion
        )
    )

    var currentQuestion: Question? = null
    private lateinit var multipleChoiceAdapter: MultipleChoiceAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_create_app, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCreateAppBinding.inflate(layoutInflater)
        multipleChoiceAdapter = MultipleChoiceAdapter { isAnyItemSelected ->
            binding.helpLayoutButtonContinue.isEnabled = isAnyItemSelected
        }
        binding.multipleChoice.adapter = multipleChoiceAdapter
        binding.multipleChoice.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.helpLayoutButtonVariantA.setOnClickListener {
            val mCurrentQuestion = currentQuestion
            if (mCurrentQuestion is Question.ChooseQuestion) {
                mCurrentQuestion.answers.first().also(::sendAnswer)
            }
        }
        binding.helpLayoutButtonVariantB.setOnClickListener {
            val mCurrentQuestion = currentQuestion
            if (mCurrentQuestion is Question.ChooseQuestion) {
                mCurrentQuestion.answers.last().also(::sendAnswer)
            }
        }
        binding.helpLayoutButtonContinue.setOnClickListener {
            val mCurrentQuestion = currentQuestion
            when (mCurrentQuestion) {
                is Question.WriteQuestion -> {
                    val text = binding.helpLayoutEdittextAnswer.text?.toString() ?: ""
                    binding.helpLayoutEdittextAnswer.setText("")
                    val answer = Answer.Write(text)
                    sendAnswer(answer)
                }
                is Question.DateQuestion -> {
                    val text = "${binding.datepicker.dayOfMonth}.${binding.datepicker.month}.${binding.datepicker.year}"
                    val answer = Answer.Write(text)
                    sendAnswer(answer)
                }
                is Question.TimeQuestion -> {
                    val text = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                        "${binding.timepicker.hour}:${binding.timepicker.minute}"
                    } else {
                        TODO("VERSION.SDK_INT < M")
                    }
                    val answer = Answer.Write(text)
                    sendAnswer(answer)
                }
                is Question.MultipleChoiceQuestion -> sendAnswer(Answer.MultipleChoice(multipleChoiceAdapter.getData()))
                is Question.EnumQuestion -> TODO()
                null -> TODO()
            }
        }
        if (savedInstanceState == null) {
            val question = questionsHARDCODED.pop()
            setupQuestion(question)
        }
    }

    private fun setupQuestion(question: Question) {
        if (question.isEnabled) {
            currentQuestion = question
            binding.helpLayoutTextviewQuestion.text = question.question
            when (question) {
                is Question.ChooseQuestion -> setupChooseQuestion(question)
                is Question.WriteQuestion -> setupWriteQuestion()
                is Question.DateQuestion -> setupDateQuestion()
                is Question.TimeQuestion -> setupTimeQuestion()
                is Question.MultipleChoiceQuestion -> setupMultipleChoiceQuestion(question)
            }
        } else {
            if (questionsHARDCODED.isNotEmpty()) {
                setupQuestion(questionsHARDCODED.pop())
            } else {
                navigateToShareScreen()
            }
        }
    }

    private fun setupChooseQuestion(question: Question.ChooseQuestion) {
        binding.helpLayoutEdittextAnswer.visibility = View.GONE
        binding.helpLayoutButtonContinue.visibility = View.GONE
        binding.datepicker.visibility = View.GONE
        binding.timepicker.visibility = View.GONE
        binding.multipleChoice.visibility = View.GONE
        binding.helpLayoutLinearlayoutVariantHolder.visibility = View.VISIBLE
        binding.helpLayoutButtonVariantA.text = question.answers.first().text
        binding.helpLayoutButtonVariantB.text = question.answers.last().text
    }

    private fun setupWriteQuestion() {
        binding.helpLayoutLinearlayoutVariantHolder.visibility = View.GONE
        binding.datepicker.visibility = View.GONE
        binding.timepicker.visibility = View.GONE
        binding.multipleChoice.visibility = View.GONE
        binding.helpLayoutButtonContinue.visibility = View.VISIBLE
        binding.helpLayoutEdittextAnswer.visibility = View.VISIBLE
    }

    private fun setupDateQuestion() {
        binding.helpLayoutLinearlayoutVariantHolder.visibility = View.GONE
        binding.helpLayoutEdittextAnswer.visibility = View.GONE
        binding.timepicker.visibility = View.GONE
        binding.multipleChoice.visibility = View.GONE
        binding.datepicker.visibility = View.VISIBLE
    }

    private fun setupTimeQuestion() {
        binding.helpLayoutLinearlayoutVariantHolder.visibility = View.GONE
        binding.helpLayoutEdittextAnswer.visibility = View.GONE
        binding.multipleChoice.visibility = View.GONE
        binding.timepicker.visibility = View.VISIBLE
        binding.datepicker.visibility = View.GONE
    }

    private fun setupMultipleChoiceQuestion(question: Question.MultipleChoiceQuestion) {
        binding.helpLayoutLinearlayoutVariantHolder.visibility = View.GONE
        binding.datepicker.visibility = View.GONE
        binding.timepicker.visibility = View.GONE
        binding.multipleChoice.visibility = View.VISIBLE
        multipleChoiceAdapter.setData(question.answer.answers)
    }

    private fun sendAnswer(answer: Answer) {
        when (currentQuestion) {
            Question.ShortNameQuestion -> {
                form.shortname = (answer as Answer.Write).input
            }
            is Question.CategoryQuestion -> {
                val choosedAnswersId =
                    (answer as Answer.MultipleChoice).answers.filter { (_, _, choosed) -> choosed }
                        .map { (id, _, _) -> id }
                val result = mutableListOf<CategoryOfDigitalTransf>().apply {
                    choosedAnswersId.forEach { id ->
                        val category = CategoryOfDigitalTransf.getById(id)
                        category?.also { add(category) }
                    }
                }
                form.categories = result
            }
        }
        if (questionsHARDCODED.isNotEmpty()) {
            setupQuestion(questionsHARDCODED.pop())
        } else {
            navigateToShareScreen()
        }
    }

    private fun navigateToShareScreen() {
        // TODO замутить навигацию на экран создания заявки
    }
}