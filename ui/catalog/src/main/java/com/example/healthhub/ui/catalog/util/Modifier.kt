package com.example.healthhub.ui.catalog.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Modifier.thenIf(predicate: Boolean, action: @Composable Modifier.() -> Modifier) =
    if (predicate) then(action()) else this
