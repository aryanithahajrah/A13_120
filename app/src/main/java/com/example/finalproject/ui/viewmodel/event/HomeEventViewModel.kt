package com.example.finalproject.ui.viewmodel.event

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.finalproject.model.Event
import com.example.finalproject.repository.EventRepository
import kotlinx.coroutines.launch
import java.io.IOException

sealed class  HomeEventUiState  {
    data class  Success ( val  event :  List <Event> )  :  HomeEventUiState ()
    object  Error  :  HomeEventUiState ()
    object  Loading  :  HomeEventUiState ()
}

class HomeEventViewModel ( private val  evnt : EventRepository)  :  ViewModel() {
    var  evntUIState :  HomeEventUiState  by  mutableStateOf ( HomeEventUiState . Loading )
        private set

    init  {
        getEvnt ()
    }

    fun  getEvnt () {
        viewModelScope . launch  {
            evntUIState  =  HomeEventUiState . Loading
            evntUIState  = try  {
                HomeEventUiState . Success (evnt. getEvent ())
            }  catch  (e : IOException) {
                HomeEventUiState . Error
            }  catch  (e : HttpException) {
                HomeEventUiState . Error
            }
        }
    }


    fun  deleteEvnt ( id_event :  String ) {
        viewModelScope . launch  {
            try  {
                evnt. deleteEvent ( id_event )
            }  catch  (e : IOException){
                HomeEventUiState . Error
            }  catch  (e : HttpException){
                HomeEventUiState . Error
            }
        }
    }
}