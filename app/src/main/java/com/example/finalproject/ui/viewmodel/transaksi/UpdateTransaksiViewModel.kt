package com.example.finalproject.ui.viewmodel.transaksi

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.repository.TransaksiRepository
import com.example.finalproject.ui.view.transaksi.DestinasiEditTransaksi
import kotlinx.coroutines.launch

class UpdateTransaksiViewModel(
    savedStateHandle: SavedStateHandle,
    private val transaksiRepository: TransaksiRepository
) : ViewModel() {

    var uiState by mutableStateOf(InsertTransaksiUiState())
        private set

    val id_transaksi: String = checkNotNull(savedStateHandle[DestinasiEditTransaksi.id_transaksi])

    init {
        viewModelScope.launch {
            uiState = transaksiRepository.getTransaksiById(id_transaksi).toUiStateTransaksi()
        }
    }

    fun updateInsertTransaksiState(inserttransaksitUiTransaksi: InserttransaksiUiTransaksi) {
        uiState = InsertTransaksiUiState(inserttransaksiUiTransaksi = inserttransaksitUiTransaksi)
    }

    suspend fun editTransaksi(){
        viewModelScope.launch {
            try {
                transaksiRepository.updateTransaksi(id_transaksi, uiState.inserttransaksiUiTransaksi.toTransaksi())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}