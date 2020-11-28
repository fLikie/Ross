package com.example.rosseti.application.qa

sealed class Question(val question: String, var isEnabled: Boolean = true) {

    private companion object QuestionTexts{

        const val SHORT_NAME = "Как бы вы описали ваше предложение в нескольких словах?"
        const val CATEGORY = "Выберите к какой части цифровой трансформации относится ваше предложение?"
        const val ISSUE_DESC = "Опишите проблематику вашего рацпредложения с указанием существующих недостатков"
        const val ISSUE_SOLUTION = "Опишите ваше предалагаемое решение"
        const val ISSUE_EFFECT = "Какой положительный эффект вы ожидаете от данного решения (технический, организационный, управленческий или иной)?"
        const val EXPENDITURES = "Укажите необходимые затраты и их стоимость"
        const val STEPS = "Укажите требуемые шаги для реализации и их длительность"
        const val REWARD = "Укажите сколько процентов от вознаграждения вы бы хотели получить"
        const val ECONOMY = "Несет ли ваше рацпредложение положительный экономический эффект?"
    }
    // OPENED
    open class WriteQuestion(question: String) : Question(question)
    open class DateQuestion(question: String) : Question(question)
    open class TimeQuestion(question: String) : Question(question)
    open class ChooseQuestion(
        question: String,
        val answers: List<Answer.Choice> = listOf(
            Answer.Choice("Да", true),
            Answer.Choice("Нет", false)
        )
    ) : Question(question)
    open class MultipleChoiceQuestion(question: String, val answer: Answer.MultipleChoice): Question(question)
    open class EnumQuestion(question: String) : Question(question)

    // INSTANCES
    object ShortNameQuestion : WriteQuestion(SHORT_NAME)
    class CategoryQuestion(answers: Answer.MultipleChoice) : MultipleChoiceQuestion(CATEGORY, answers)
    object IssueDescriptionQuestion : WriteQuestion(ISSUE_DESC)
    object IssueSolutionQuestion : WriteQuestion(ISSUE_SOLUTION)
    object IssueEffectQuestion : WriteQuestion(ISSUE_EFFECT)
    object ExpendituresQuestion : EnumQuestion(EXPENDITURES)
    object StepsQuestion : EnumQuestion(STEPS)
    object RewardQuestion : WriteQuestion(REWARD)
    object EconomyQuestion : ChooseQuestion(ECONOMY)

    // Navigation
    class StageQuestion(
        question: String,
        val stage: Pair<Stage, Stage>
    ) : Question(question)
}
