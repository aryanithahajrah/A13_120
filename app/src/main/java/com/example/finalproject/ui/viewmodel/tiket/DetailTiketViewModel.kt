package com.example.finalproject.ui.viewmodel.tiket

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.model.Tiket
import com.example.finalproject.repository.TiketRepository
import com.example.finalproject.ui.view.tiket.DestinasiDetailTiket
import kotlinx.coroutines.launch
import java.io.IOException

sealed class DetailtiketUiState{
    data class Success(val tiket: Tiket) : DetailtiketUiState()
    object Error : DetailtiketUiState()
    object Loading : DetailtiketUiState()
}

class DetailTiketViewModel(
    savedStateHandle: SavedStateHandle,
    private val tiketRepository: TiketRepository
) : ViewModel() {

    private val id_tiket: String = checkNotNull(savedStateHandle[DestinasiDetailTiket.id_tiket])
    var detailtiketUiState: DetailtiketUiState by mutableStateOf(DetailtiketUiState.Loading)
        private set

    init {
        getTiketbyId()
    }

    fun getTiketbyId(){
        viewModelScope.launch {
            detailtiketUiState = DetailtiketUiState.Loading
            detailtiketUiState = try {
                DetailtiketUiState.Success(tiket = tiketRepository.getTiketById(id_tiket))
            } catch (e: IOException) {
                DetailtiketUiState.Error
            }
        }
    }
}