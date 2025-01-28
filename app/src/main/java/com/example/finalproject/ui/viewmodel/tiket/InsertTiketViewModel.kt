package com.example.finalproject.ui.viewmodel.tiket

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.model.Tiket
import com.example.finalproject.repository.TiketRepository
import kotlinx.coroutines.launch

class  InsertTiketViewModel ( private val  tiket : TiketRepository)  :  ViewModel() {
    var  tiketuiState  by  mutableStateOf ( InsertTiketUiState ())
        private set

    fun  updateInsertTiketState ( inserttiketUiTiket :  InserttiketUiTiket ) {
        tiketuiState  =  InsertTiketUiState ( inserttiketUiTiket =  inserttiketUiTiket )
    }

    suspend fun insertTiket () {
        viewModelScope . launch  {
            try  {
                tiket. insertTiket (tiketuiState.inserttiketUiTiket. toTiket ())
            } catch  (e : Exception ){
                e. printStackTrace ()
            }
        }
    }
}

data class  InsertTiketUiState (
    val  inserttiketUiTiket: InserttiketUiTiket = InserttiketUiTiket()
)

data class InserttiketUiTiket (
    val  id_tiket :  String  =  "" ,
    val  id_event :  String  =  "" ,
    val  id_peserta :  String  =  "" ,
    val  kapasitas_tiket :  String  =  "",
    val  harga_tiket :  String  =  "",
)

fun  InserttiketUiTiket . toTiket () : Tiket =  Tiket (
    id_tiket =  id_tiket,
    id_event =  id_event,
    id_peserta = id_peserta,
    kapasitas_tiket = kapasitas_tiket,
    harga_tiket = harga_tiket
)

fun Tiket. toUiStateTiket () :  InsertTiketUiState  =  InsertTiketUiState (
    inserttiketUiTiket =  toInserttiketUiTiket ()
)

fun Tiket. toInserttiketUiTiket () :  InserttiketUiTiket  =  InserttiketUiTiket (
    id_tiket =  id_tiket,
    id_event =  id_event,
    id_peserta =  id_peserta,
    kapasitas_tiket =  kapasitas_tiket,
    harga_tiket = harga_tiket
)