package com.example.finalproject.ui.viewmodel.transaksi

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.model.Transaksi
import com.example.finalproject.repository.TransaksiRepository
import kotlinx.coroutines.launch

class  InsertTransaksiViewModel ( private val  transaksi : TransaksiRepository)  :  ViewModel() {
    var  transaksiuiState  by  mutableStateOf ( InsertTransaksiUiState ())
        private set

    fun  updateInsertTransaksiState ( inserttransaksiUiTransaksi :  InserttransaksiUiTransaksi ) {
        transaksiuiState  =  InsertTransaksiUiState ( inserttransaksiUiTransaksi =  inserttransaksiUiTransaksi )
    }

    suspend fun insertTransaksi () {
        viewModelScope . launch  {
            try  {
                transaksi. insertTransaksi (transaksiuiState.inserttransaksiUiTransaksi. toTransaksi ())
            } catch  (e : Exception ){
                e. printStackTrace ()
            }
        }
    }
}

data class  InsertTransaksiUiState (
    val  inserttransaksiUiTransaksi: InserttransaksiUiTransaksi = InserttransaksiUiTransaksi()
)

data class InserttransaksiUiTransaksi (
    val  id_transaksi :  String  =  "" ,
    val  id_tiket :  String  =  "" ,
    val  jumlah_tiket :  String  =  "" ,
    val  jumlah_pembayaran :  String  =  "",
    val  tanggal_transaksi :  String  =  "",
)

fun  InserttransaksiUiTransaksi . toTransaksi () : Transaksi =  Transaksi (
    id_transaksi =  id_transaksi,
    id_tiket =  id_tiket,
    jumlah_tiket = jumlah_tiket,
    jumlah_pembayaran = jumlah_pembayaran,
    tanggal_transaksi = tanggal_transaksi
)

fun Transaksi.toUiStateTransaksi () :  InsertTransaksiUiState  =  InsertTransaksiUiState (
    inserttransaksiUiTransaksi =  toInserttransaksiUiTransaksi ()
)

fun Transaksi. toInserttransaksiUiTransaksi () :  InserttransaksiUiTransaksi  =  InserttransaksiUiTransaksi (
    id_transaksi =  id_transaksi,
    id_tiket =  id_tiket,
    jumlah_tiket =  jumlah_tiket,
    jumlah_pembayaran =  jumlah_pembayaran,
    tanggal_transaksi = tanggal_transaksi
)