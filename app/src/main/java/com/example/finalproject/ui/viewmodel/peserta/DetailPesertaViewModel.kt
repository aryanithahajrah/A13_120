package com.example.finalproject.ui.viewmodel.peserta

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.model.Peserta
import com.example.finalproject.repository.PesertaRepository
import com.example.finalproject.ui.view.peserta.DestinasiDetailPeserta
import kotlinx.coroutines.launch
import java.io.IOException

sealed class DetailPesertaUiState{
    data class Success(val peserta: Peserta) : DetailPesertaUiState()
    object Error : DetailPesertaUiState()
    object Loading : DetailPesertaUiState()
}

class DetailPesertaViewModel(
    savedStateHandle: SavedStateHandle,
    private val pesertaRepository: PesertaRepository
) : ViewModel() {

    private val id_peserta: String = checkNotNull(savedStateHandle[DestinasiDetailPeserta.id_peserta])
    var detailPesertaUiState: DetailPesertaUiState by mutableStateOf(DetailPesertaUiState.Loading)
        private set

    init {
        getPesertabyId()
    }

    fun getPesertabyId(){
        viewModelScope.launch {
            detailPesertaUiState = DetailPesertaUiState.Loading
            detailPesertaUiState = try {
                DetailPesertaUiState.Success(peserta = pesertaRepository.getPesertaById(id_peserta))
            } catch (e: IOException) {
                DetailPesertaUiState.Error
            }
        }
    }
}