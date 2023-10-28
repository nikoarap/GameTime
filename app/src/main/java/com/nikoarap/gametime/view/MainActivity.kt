package com.nikoarap.gametime.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import com.nikoarap.gametime.view.composables.LoadTopBarWithContent
import com.nikoarap.gametime.viewmodels.MainViewModel
import io.realm.Realm

class MainActivity : ComponentActivity() {


    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.initViewModel(Realm.getDefaultInstance())
        initObservables()
        setContent {
            TestContent()
        }
    }



    @Preview(showSystemUi = true, showBackground = true)
    @Composable
    fun TestContent() {
        val sportsList by viewModel.sportModelsStateFlow.collectAsState()
        val isRefreshing by viewModel.isRefreshing.collectAsState()
        LoadTopBarWithContent(
            sports = sportsList,
            refreshing = isRefreshing,
            onRefresh = { viewModel.onRefresh() }
        )
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


