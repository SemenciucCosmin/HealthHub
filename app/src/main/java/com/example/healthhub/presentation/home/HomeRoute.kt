package com.example.healthhub.presentation.home

import androidx.compose.runtime.Composable
import com.example.healthhub.presentation.home.viewmodel.HomeViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeRoute() {
    val viewModel = koinViewModel<HomeViewModel>()

}
