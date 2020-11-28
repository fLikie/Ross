package com.example.rosseti.application.qa

sealed class Answer {
    open class Choice(val text: String, var triggerAnswer: Boolean) : Answer()
    open class MultipleChoice(val answers: List<Triple<Int, String, Boolean>>) : Answer()
    open class Write(val input: String) : Answer()
}