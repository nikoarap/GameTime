package com.nikoarap.gametime.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.nikoarap.gametime.view.composables.MainComponent
import com.nikoarap.gametime.viewmodels.MainViewModel
import io.realm.Realm

class MainActivity : ComponentActivity() {


    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.initViewModel(Realm.getDefaultInstance())
        initObservables()
        setContent {
            val sportsList by viewModel.sportModelsStateFlow.collectAsState()
            val mainComponent = MainComponent(viewModel)
            mainComponent.LoadMainComponent(
                sports = sportsList,
                navBottomItems = viewModel.navBottomItems,
                viewModel.selectedItemIndex,
                viewModel.favouriteSelected.value
            )
        }
    }

    private fun initObservables() {
        viewModel.getSportModels()?.observe(this) {
            run {
                //check if the realm results set we are observing for nullability and validity (realm object validity)
                if (it != null && it.isValid) {
                    viewModel.emitSportModels(it)
                }
            }
        }

    }
}


