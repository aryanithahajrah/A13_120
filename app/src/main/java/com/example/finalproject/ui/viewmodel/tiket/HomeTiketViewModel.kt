package com.example.finalproject.ui.viewmodel.tiket

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.finalproject.model.Tiket
import com.example.finalproject.repository.TiketRepository
import kotlinx.coroutines.launch
import java.io.IOException

sealed class HomeTiketUiState  {
    data class  Success ( val tiket :  List <Tiket> )  :  HomeTiketUiState ()
    object  Error  :  HomeTiketUiState ()
    object  Loading  :  HomeTiketUiState ()
}

class HomeTiketViewModel ( private val  tiket : TiketRepository)  :  ViewModel() {
    var  tiketUIState :  HomeTiketUiState  by  mutableStateOf ( HomeTiketUiState . Loading )
        private set

    init  {
        getTiket ()
    }

    fun getTiket () {
        viewModelScope . launch  {
            tiketUIState  =  HomeTiketUiState . Loading
            tiketUIState  = try  {
                HomeTiketUiState . Success (tiket. getTiket ())
            }  catch  (e : IOException) {
                HomeTiketUiState . Error
            }  catch  (e : HttpException) {
                HomeTiketUiState . Error
            }
        }
    }

    fun deleteTiket (id_tiket : String ) {
        viewModelScope.launch  {
            try  {
                tiket.deleteTiket ( id_tiket )
            }  catch  (e : IOException){
                HomeTiketUiState . Error
            }  catch  (e : HttpException){
                HomeTiketUiState . Error
            }
        }
    }
}