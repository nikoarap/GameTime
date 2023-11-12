package com.nikoarap.gametime.feature_sports.presentation.screenAllEvents.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikoarap.gametime.feature_sports.domain.useCases.allEvents.AllEventsUC
import com.nikoarap.gametime.feature_sports.presentation.screenAllEvents.state.SportListState
import com.nikoarap.gametime.utils.DownloadResult
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
                is DownloadResult.Success -> {
                    _state.value = SportListState(sports = result.data ?: emptyList())
                }
                is DownloadResult.Error -> {
                    _state.value = SportListState(error = result.errorMessage ?: "Error while fetching sports data.")
                }
                is DownloadResult.Loading -> {
                    _state.value = SportListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }



}