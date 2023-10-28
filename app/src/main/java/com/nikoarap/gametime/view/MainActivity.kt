package com.nikoarap.gametime.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.nikoarap.gametime.view.composables.LoadSportList
import com.nikoarap.gametime.viewmodels.MainViewModel
import io.realm.Realm

class MainActivity : ComponentActivity() {

    private var realm = Realm.getDefaultInstance()
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.initViewModel(realm)
        initObservables()
        setContent {
            val sportsList by viewModel.sportModelsStateFlow.collectAsState()
            val isRefreshing by viewModel.isRefreshing.collectAsState()
            LoadSportList(
                sports = sportsList,
                refreshing = isRefreshing,
                onRefresh = { viewModel.onRefresh() }
            )
        }
    }

//    override fun onCreateView(
//        parent: View?,
//        name: String,
//        context: Context,
//        attrs: AttributeSet
//    ): View? {
//        initObservers()
//        return super.onCreateView(parent, name, context, attrs)
//    }
//
//    /**
//     * onViewCreated lifecycle method.
//     */
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        navController = NavHostFragment.findNavController(this)
//        analyticsViewListLogEvent(taskTable)
//        initObservers()
//    }



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
