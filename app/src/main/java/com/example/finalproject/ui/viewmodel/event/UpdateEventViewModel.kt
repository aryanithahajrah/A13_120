package com.example.finalproject.ui.viewmodel.event

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.repository.EventRepository
import com.example.finalproject.ui.view.event.DestinasiEditEvent
import kotlinx.coroutines.launch

class UpdateEventViewModel(
    savedStateHandle: SavedStateHandle,
    private val eventRepository: EventRepository
) : ViewModel() {

    var uiState by mutableStateOf(InsertEventUiState())
        private set

    val id_event: String = checkNotNull(savedStateHandle[DestinasiEditEvent.id_event])

    init {
        viewModelScope.launch {
            uiState = eventRepository.getEventById(id_event).toUiStateEvnt()
        }
    }

    fun updateInsertEventState(insertevntUiEvent: InsertevntUiEvent) {
        uiState = InsertEventUiState(insertevntUiEvent = insertevntUiEvent)
    }

    suspend fun editEvent(){
        viewModelScope.launch {
            try {
                eventRepository.updateEvent(id_event, uiState.insertevntUiEvent.toEvnt())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}