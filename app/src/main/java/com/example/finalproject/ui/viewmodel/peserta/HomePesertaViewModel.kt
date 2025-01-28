package com.example.finalproject.ui.viewmodel.peserta

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.model.Peserta
import com.example.finalproject.repository.PesertaRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed class HomePesertaUiState {
    data class Success(val peserta: List<Peserta>) : HomePesertaUiState()
    object Error : HomePesertaUiState()
    object Loading : HomePesertaUiState()
}

class HomePesertaViewModel(
    private val pesertaRepository: PesertaRepository
) : ViewModel() {

    var pesertaUiState: HomePesertaUiState by mutableStateOf(HomePesertaUiState.Loading)
        private set

    var selectedPeserta: String by mutableStateOf("")
        private set

    init {
        getPeserta()
    }

    fun getPeserta() {
        viewModelScope.launch {
            pesertaUiState = HomePesertaUiState.Loading
            pesertaUiState = try {
                HomePesertaUiState.Success(pesertaRepository.getPeserta())
            } catch (e: IOException) {
                HomePesertaUiState.Error
            } catch (e: HttpException) {
                HomePesertaUiState.Error
            }
        }
    }

    fun deletePeserta(id_peserta: String) {
        viewModelScope.launch {
            try {
                pesertaRepository.deletePeserta(id_peserta)
                getPeserta() // Refresh daftar peserta setelah penghapusan
            } catch (e: IOException) {
                pesertaUiState = HomePesertaUiState.Error
            } catch (e: HttpException) {
                pesertaUiState = HomePesertaUiState.Error
            }
        }
    }
}
