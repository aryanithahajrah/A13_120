package com.example.finalproject.ui.viewmodel.transaksi

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.model.Transaksi
import com.example.finalproject.repository.TransaksiRepository
import com.example.finalproject.ui.view.transaksi.DestinasiDetailTransaksi
import kotlinx.coroutines.launch
import java.io.IOException

sealed class DetailtransaksiUiState{
    data class Success(val transaksi: Transaksi) : DetailtransaksiUiState()
    object Error : DetailtransaksiUiState()
    object Loading : DetailtransaksiUiState()
}

class DetailTransaksiViewModel(
    savedStateHandle: SavedStateHandle,
    private val transaksiRepository: TransaksiRepository
) : ViewModel() {

    private val id_transaksi: String = checkNotNull(savedStateHandle[DestinasiDetailTransaksi.id_transaksi])
    var detailtransaksiUiState: DetailtransaksiUiState by mutableStateOf(DetailtransaksiUiState.Loading)
        private set

    init {
        getTransaksibyId()
    }

    fun getTransaksibyId(){
        viewModelScope.launch {
            detailtransaksiUiState = DetailtransaksiUiState.Loading
            detailtransaksiUiState = try {
                DetailtransaksiUiState.Success(transaksi = transaksiRepository.getTransaksiById(id_transaksi))
            } catch (e: IOException) {
                DetailtransaksiUiState.Error
            }
        }
    }
}