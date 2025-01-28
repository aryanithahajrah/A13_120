package com.example.finalproject.ui.viewmodel.event

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.model.Event
import com.example.finalproject.repository.EventRepository
import com.example.finalproject.ui.view.event.DestinasiDetailEvent
import kotlinx.coroutines.launch
import java.io.IOException

sealed class DetailevntUiState{
    data class Success(val event: Event) : DetailevntUiState()
    object Error : DetailevntUiState()
    object Loading : DetailevntUiState()
}

class DetailEventViewModel(
    savedStateHandle: SavedStateHandle,
    private val eventRepository: EventRepository
) : ViewModel() {

    private val id_event: String = checkNotNull(savedStateHandle[DestinasiDetailEvent.id_event])
    var detailevntUiState: DetailevntUiState by mutableStateOf(DetailevntUiState.Loading)
        private set

    init {
        getEventbyId()
    }

    fun getEventbyId(){
        viewModelScope.launch {
            detailevntUiState = DetailevntUiState.Loading
            detailevntUiState = try {
                DetailevntUiState.Success(event = eventRepository.getEventById(id_event))
            } catch (e: IOException) {
                DetailevntUiState.Error
            }
        }
    }
}