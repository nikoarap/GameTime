package com.nikoarap.gametime.feature_sports.presentation.screenAllEvents.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikoarap.gametime.feature_sports.domain.useCases.allEvents.AllEventsUC
import com.nikoarap.gametime.feature_sports.presentation.screenAllEvents.state.SportListState
import com.nikoarap.gametime.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SportListViewModel @Inject constructor(
    private val getSportsUseCase: AllEventsUC
): ViewModel() {

    private val _state = mutableStateOf(SportListState())
    val state: State<SportListState> = _state

    init {
        getSports()
    }

    private fun getSports() {
        getSportsUseCase().onEach { result ->
            when(result) {
                is DataState.Success -> {
                    _state.value = SportListState(sports = result.data ?: emptyList())
                }
                is DataState.Error -> {
                    _state.value = SportListState(error = result.errorMessage ?: "Error while fetching sports data.")
                }
                is DataState.Loading -> {
                    _state.value = SportListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }



}