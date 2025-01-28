package com.example.finalproject.ui.CustomWidget

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.finalproject.ui.viewmodel.PenyediaViewModel
import com.example.finalproject.ui.viewmodel.event.HomeEventUiState
import com.example.finalproject.ui.viewmodel.event.HomeEventViewModel

object ListEvent {
    @Composable
    fun DataNamaEvent(
        homeEventViewModel: HomeEventViewModel = viewModel(factory = PenyediaViewModel.Factory)
    ): List<String> {
        val eventUiState = homeEventViewModel.evntUIState

        return when (eventUiState) {
            is HomeEventUiState.Success -> {
                eventUiState.event.map { it.nama_event }
            }
            else -> emptyList()
        }
    }
}
