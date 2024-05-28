package com.kryptopass.cocktails.game.model

abstract class Question(
    val correctOption: String,
    val incorrectOption: String,
    val imageUrl: String? = null
) {
    var answeredOption: String? = null
        protected set

    val isAnsweredCorrectly: Boolean
        get() = correctOption == answeredOption

    abstract fun answer(option: String): Boolean

    fun getOptions(sort: (List<String>) -> List<String> = { it.shuffled() }) =
        sort(listOf(correctOption, incorrectOption))
}