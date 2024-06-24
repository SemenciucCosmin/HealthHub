package com.example.healthhub.presentation.info

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.healthhub.presentation.info.viewmodel.InfoViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import org.koin.androidx.compose.koinViewModel

private const val ROMANIA_LATITUDE = 45.9432
private const val ROMANIA_LONGITUDE = 24.9668
private const val DEFAULT_ZOOM_FACTOR = 6f

@Composable
fun InfoRoute() {
    val viewModel = koinViewModel<InfoViewModel>()
    val cameraCoordinates = LatLng(ROMANIA_LATITUDE, ROMANIA_LONGITUDE)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(cameraCoordinates, DEFAULT_ZOOM_FACTOR)
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        viewModel.uiState.locations.forEach { location ->
            Marker(
                state = MarkerState(position = LatLng(location.latitude, location.longitude)),
                title = location.name,
                snippet = location.address
            )
        }
    }
}
