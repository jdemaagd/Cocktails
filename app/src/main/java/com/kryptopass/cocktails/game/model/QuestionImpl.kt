package com.kryptopass.cocktails.game.model

class QuestionImpl(
    correctOption: String,
    incorrectOption: String,
    imageUrl: String? = null
) : Question(correctOption, incorrectOption, imageUrl) {

    override fun answer(option: String): Boolean {
        if (option != correctOption && option != incorrectOption)
            throw IllegalArgumentException("Not a valid option")

        answeredOption = option

        return isAnsweredCorrectly
    }
}