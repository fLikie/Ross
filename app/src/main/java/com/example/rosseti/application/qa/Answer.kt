package com.example.rosseti.application.qa

import android.os.Parcelable

sealed class Answer {
    open class Choice(val text: String, var triggerAnswer: Boolean) : Answer()
    open class MultipleChoice(val answers: List<Triple<Int, String, Boolean>>) : Answer()
    open class EnumAnswer(val answers: List<Pair<String, Long>>) : Answer()
    open class Write(val input: String) : Answer()
}