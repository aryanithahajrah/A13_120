package com.example.finalproject.ui.viewmodel.tiket

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.repository.TiketRepository
import com.example.finalproject.ui.view.tiket.DestinasiEditTiket
import kotlinx.coroutines.launch

class UpdateTiketViewModel(
    savedStateHandle: SavedStateHandle,
    private val tiketRepository: TiketRepository
) : ViewModel() {

    var uiState by mutableStateOf(InsertTiketUiState())
        private set

    val id_tiket: String = checkNotNull(savedStateHandle[DestinasiEditTiket.id_tiket])

    init {
        viewModelScope.launch {
            uiState = tiketRepository.getTiketById(id_tiket).toUiStateTiket()
        }
    }

    fun updateInsertTiketState(inserttikettUiTiket: InserttiketUiTiket) {
        uiState = InsertTiketUiState(inserttiketUiTiket = inserttikettUiTiket)
    }

    suspend fun editTiket(){
        viewModelScope.launch {
            try {
                tiketRepository.updateTiket(id_tiket, uiState.inserttiketUiTiket.toTiket())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}