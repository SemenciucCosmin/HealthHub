package com.example.healthhub.feature.home

import androidx.compose.runtime.Composable
import com.example.healthhub.feature.home.viewmodel.HomeViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeRoute() {
    val viewModel = koinViewModel<HomeViewModel>()

}
