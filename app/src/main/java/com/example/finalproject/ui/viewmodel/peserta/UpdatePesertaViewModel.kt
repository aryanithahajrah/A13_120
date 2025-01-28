package com.example.finalproject.ui.viewmodel.peserta

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.repository.PesertaRepository
import com.example.finalproject.ui.view.peserta.DestinasiEditPeserta
import kotlinx.coroutines.launch

class UpdatePesertaViewModel(
    savedStateHandle: SavedStateHandle,
    private val pesertaRepository: PesertaRepository
) : ViewModel() {

    var uiState by mutableStateOf(InsertUiState())
        private set

    val id_peserta: String = checkNotNull(savedStateHandle[DestinasiEditPeserta.id_peserta])

    init {
        viewModelScope.launch {
            uiState = pesertaRepository.getPesertaById(id_peserta).toUiStatePeserta()
        }
    }

    fun updateInsertPesertaState(insertUiEvent: InsertUiEvent) {
        uiState = InsertUiState(insertUiEvent = insertUiEvent)
    }

    suspend fun editPeserta(){
        viewModelScope.launch {
            try {
                pesertaRepository.updatePeserta(id_peserta, uiState.insertUiEvent.toPeserta())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}