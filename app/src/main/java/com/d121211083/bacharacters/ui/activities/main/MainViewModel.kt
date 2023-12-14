package com.d121211083.bacharacters.ui.activities.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.d121211083.bacharacters.MyApplication
import com.d121211083.bacharacters.data.models.Char
import com.d121211083.bacharacters.data.repository.CharRepository
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface MainUiState {
    data class Success(val char: List<Char>) : MainUiState
    object Error : MainUiState
    object Loading : MainUiState
}

class MainViewModel(private val charsRepository: CharRepository): ViewModel() {

    var mainUiState: MainUiState by mutableStateOf(MainUiState.Loading)
        private set

    fun getChars() = viewModelScope.launch {
        mainUiState = MainUiState.Loading
        try {
            val result = charsRepository.getChar()
            mainUiState = MainUiState.Success(result.data.orEmpty())
        } catch (e: IOException) {
            mainUiState = MainUiState.Error
        }
    }

    init {
        getChars()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MyApplication)
                val repository = application.container.charRepository
                MainViewModel(repository)
            }
        }
    }
}