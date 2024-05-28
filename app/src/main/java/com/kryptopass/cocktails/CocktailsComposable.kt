package com.kryptopass.cocktails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.kryptopass.cocktails.game.factory.CocktailsGameFactory
import com.kryptopass.cocktails.game.model.RequestState
import com.kryptopass.cocktails.game.model.Score
import com.kryptopass.cocktails.repository.CocktailsRepository
import com.kryptopass.cocktails.ui.theme.CocktailsTheme

@Composable
fun CocktailsScreen(
    repository: CocktailsRepository,
    gameFactory: CocktailsGameFactory
) {

    val cocktailsViewModel = viewModel<CocktailsViewModel>(
        factory = CocktailsViewModelFactory(
            repository = repository,
            gameFactory = gameFactory
        )
    )

    val game = cocktailsViewModel.game.collectAsState().value
    val question = cocktailsViewModel.question.collectAsState().value

    val score = when (game) {
        is RequestState.Success -> {
            game.requestObject.score
        }

        else -> {
            Score(0)
        }
    }


    LaunchedEffect(Unit) {
        cocktailsViewModel.initGame()
    }

    val options = question?.getOptions()
    CocktailsComposable(
        score = score,
        cocktailImage = question?.imageUrl ?: "",
        cocktailNameChoiceOne = options?.get(0) ?: "",
        cocktailNameChoiceTwo = options?.get(1) ?: "",
        nextQuestion = { cocktailsViewModel.nextQuestion() },
        answerQuestion = { answer ->
            question?.let {
                cocktailsViewModel.answerQuestion(question, answer)
            }
        }
    )

}

@Composable
fun CocktailsComposable(
    score: Score,
    cocktailImage: String,
    cocktailNameChoiceOne: String,
    cocktailNameChoiceTwo: String,
    nextQuestion: () -> Unit,
    answerQuestion: (answer: String) -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier.fillMaxWidth()
    ) {
        val centeredModifier = modifier.align(CenterHorizontally)
        Column(
            modifier = modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            Text(
                text = "High Score: " + score.highest,
                modifier = centeredModifier
            )
            Text(
                text = "Score: " + score.current,
                modifier = centeredModifier,
                fontSize = 22.sp
            )

            AsyncImage(
                model = cocktailImage,
                contentDescription = null,
                modifier = centeredModifier
            )

            Text(
                text = "What is the name of this cocktail?",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = centeredModifier
            )

            Button(
                onClick = { answerQuestion(cocktailNameChoiceOne) },
                modifier = centeredModifier
            ) {
                Text(
                    text = cocktailNameChoiceOne
                )
            }

            Button(
                onClick = { answerQuestion(cocktailNameChoiceTwo) },
                modifier = centeredModifier
            ) {
                Text(
                    text = cocktailNameChoiceTwo
                )
            }
        }
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = { nextQuestion() },
                modifier = centeredModifier
            ) {
                Text(
                    text = "Next"
                )
            }
        }

    }

}


@Preview
@Composable
fun ContailsComposablePreview() {
    CocktailsTheme {
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            CocktailsComposable(
                Score(2),
                "https://www.thecocktaildb.com/images/media/drink/rtpxqw1468877562.jpg",
                "beer",
                "margarita",
                {},
                {}
            )
        }
    }
}