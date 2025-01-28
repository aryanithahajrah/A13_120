package com.example.finalproject.ui.viewmodel.event

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.model.Event
import com.example.finalproject.repository.EventRepository
import kotlinx.coroutines.launch

class  InsertEventViewModel ( private val  evnt : EventRepository)  :  ViewModel() {
    var  evntuiState  by  mutableStateOf ( InsertEventUiState ())
        private set

    fun  updateInsertEvntState ( insertevntUiEvent :  InsertevntUiEvent ) {
        evntuiState  =  InsertEventUiState ( insertevntUiEvent =  insertevntUiEvent )
    }

    suspend fun  insertEvnt () {
        viewModelScope . launch  {
            try  {
                evnt. insertEvent (evntuiState.insertevntUiEvent. toEvnt ())
            } catch  (e : Exception ){
                e. printStackTrace ()
            }
        }
    }
}

data class  InsertEventUiState (
    val  insertevntUiEvent: InsertevntUiEvent = InsertevntUiEvent()
)

data class InsertevntUiEvent (
    val  id_event :  String  =  "" ,
    val  nama_event :  String  =  "" ,
    val  deskripsi_event :  String  =  "",
    val tanggal_event : String ="",
    val lokasi_event : String = ""
)

fun  InsertevntUiEvent . toEvnt () : Event =  Event (
    id_event =  id_event,
    nama_event =  nama_event,
    deskripsi_event =  deskripsi_event,
    tanggal_event = tanggal_event,
    lokasi_event = lokasi_event,
)

fun Event . toUiStateEvnt () :  InsertEventUiState  =  InsertEventUiState (
    insertevntUiEvent =  toInsertevntUiEvent ()
)

fun Event . toInsertevntUiEvent () :  InsertevntUiEvent  =  InsertevntUiEvent (
    id_event =  id_event,
    nama_event =  nama_event,
    deskripsi_event =  deskripsi_event,
    tanggal_event = tanggal_event,
    lokasi_event = lokasi_event,
)