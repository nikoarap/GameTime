package com.nikoarap.gametime.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.nikoarap.gametime.domain.uc.get_sports.GetSportsUC
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SportListViewModel @Inject constructor(
    private val getSportsUseCase: GetSportsUC
): ViewModel() {

}