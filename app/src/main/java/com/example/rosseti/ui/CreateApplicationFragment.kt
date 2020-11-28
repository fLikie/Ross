package com.example.rosseti.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rosseti.application.CategoryOfDigitalTransf
import com.example.rosseti.application.Form
import com.example.rosseti.application.ImplementationStep
import com.example.rosseti.application.Spending
import com.example.rosseti.application.qa.Answer
import com.example.rosseti.application.qa.Question
import com.example.rosseti.databinding.FragmentCreateAppBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayDeque
import javax.inject.Inject

@AndroidEntryPoint
class CreateApplicationFragment : Fragment(), EnumDialog.Listener {

    @Inject
    lateinit var form: Form

    private var _binding: FragmentCreateAppBinding? = null
    private val binding
        get() = _binding!!

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
    private lateinit var enumChoiceAdapter: EnumChoiceAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateAppBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        multipleChoiceAdapter = MultipleChoiceAdapter { isAnyItemSelected ->
            binding.helpLayoutButtonContinue.isEnabled = isAnyItemSelected
        }
        enumChoiceAdapter = EnumChoiceAdapter()
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
            when (currentQuestion) {
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
                is Question.EnumQuestion -> sendAnswer(Answer.EnumAnswer(enumChoiceAdapter.getData()))
                null -> TODO()
            }
        }
        binding.addEnumAnswer.setOnClickListener {
            EnumDialog().show(childFragmentManager, currentQuestion?.question)
        }
        if (savedInstanceState == null) {
            val question = questionsHARDCODED.pop()
            setupQuestion(question)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onEnumAdded(description: String, sum: Long) {
        val data = enumChoiceAdapter.getData().toMutableList().apply {
            add(description to sum)
        }
        enumChoiceAdapter.setData(data.toList())
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
                is Question.EnumQuestion -> setupEnumQuestion()
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
        binding.recyclerView.visibility = View.GONE
        binding.addEnumAnswer.visibility = View.GONE
        binding.helpLayoutLinearlayoutVariantHolder.visibility = View.VISIBLE
        binding.helpLayoutButtonVariantA.text = question.answers.first().text
        binding.helpLayoutButtonVariantB.text = question.answers.last().text
    }

    private fun setupWriteQuestion() {
        binding.helpLayoutLinearlayoutVariantHolder.visibility = View.GONE
        binding.datepicker.visibility = View.GONE
        binding.timepicker.visibility = View.GONE
        binding.recyclerView.visibility = View.GONE
        binding.addEnumAnswer.visibility = View.GONE
        binding.helpLayoutButtonContinue.visibility = View.VISIBLE
        binding.helpLayoutEdittextAnswer.visibility = View.VISIBLE
    }

    private fun setupDateQuestion() {
        binding.helpLayoutLinearlayoutVariantHolder.visibility = View.GONE
        binding.helpLayoutEdittextAnswer.visibility = View.GONE
        binding.timepicker.visibility = View.GONE
        binding.recyclerView.visibility = View.GONE
        binding.addEnumAnswer.visibility = View.GONE
        binding.datepicker.visibility = View.VISIBLE
    }

    private fun setupTimeQuestion() {
        binding.helpLayoutLinearlayoutVariantHolder.visibility = View.GONE
        binding.helpLayoutEdittextAnswer.visibility = View.GONE
        binding.recyclerView.visibility = View.GONE
        binding.addEnumAnswer.visibility = View.GONE
        binding.timepicker.visibility = View.VISIBLE
        binding.datepicker.visibility = View.GONE
    }

    private fun setupMultipleChoiceQuestion(question: Question.MultipleChoiceQuestion) {
        binding.helpLayoutLinearlayoutVariantHolder.visibility = View.GONE
        binding.helpLayoutEdittextAnswer.visibility = View.GONE
        binding.datepicker.visibility = View.GONE
        binding.timepicker.visibility = View.GONE
        binding.addEnumAnswer.visibility = View.GONE
        binding.recyclerView.visibility = View.VISIBLE
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerView.adapter = multipleChoiceAdapter
        multipleChoiceAdapter.setData(question.answer.answers)
    }

    private fun setupEnumQuestion() {
        binding.helpLayoutLinearlayoutVariantHolder.visibility = View.GONE
        binding.helpLayoutEdittextAnswer.visibility = View.GONE
        binding.datepicker.visibility = View.GONE
        binding.timepicker.visibility = View.GONE
        binding.addEnumAnswer.visibility = View.VISIBLE
        binding.recyclerView.visibility = View.VISIBLE
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = enumChoiceAdapter
        enumChoiceAdapter.setData(emptyList())
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
            Question.IssueDescriptionQuestion -> {
                form.issueDescription = (answer as Answer.Write).input
            }
            Question.IssueSolutionQuestion -> {
                form.issueSolution = (answer as Answer.Write).input
            }
            Question.IssueEffectQuestion -> {
                form.issueEffect = (answer as Answer.Write).input
            }
            Question.ExpendituresQuestion -> {
                val list = (answer as Answer.EnumAnswer).answers
                form.expenditures = list.mapIndexed { index, pair ->  Spending(index + 1, pair.first, pair.second) }
            }
            Question.StepsQuestion -> {
                val list = (answer as Answer.EnumAnswer).answers
                form.steps = list.mapIndexed { index, pair -> ImplementationStep(index + 1, pair.first, pair.second) }
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