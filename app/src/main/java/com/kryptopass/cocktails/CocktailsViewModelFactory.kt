package com.kryptopass.cocktails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kryptopass.cocktails.game.factory.CocktailsGameFactory
import com.kryptopass.cocktails.repository.CocktailsRepository

class CocktailsViewModelFactory(
    private val repository: CocktailsRepository,
    private val gameFactory: CocktailsGameFactory
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CocktailsViewModel(repository, gameFactory) as T
    }
}