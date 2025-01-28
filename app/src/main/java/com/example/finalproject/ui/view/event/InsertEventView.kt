package com.example.finalproject.ui.view.event

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.finalproject.ui.CustomWidget.CostumeTopAppBar
import com.example.finalproject.ui.navigasi.DestinasiNavigasi
import com.example.finalproject.ui.viewmodel.event.InsertEventViewModel
import com.example.finalproject.ui.viewmodel.PenyediaViewModel
import com.example.finalproject.ui.viewmodel.event.InsertEventUiState
import com.example.finalproject.ui.viewmodel.event.InsertevntUiEvent
import kotlinx.coroutines.launch

object DestinasiEntryEvent: DestinasiNavigasi {
    override val route="item_entryevent"
    override val titleRes="Entry Event"
}

@OptIn ( ExperimentalMaterial3Api ::class )
@Composable
fun EntryEventScreen (
    navigateBack :  ()  ->  Unit,
    modifier : Modifier =  Modifier,
    viewModel : InsertEventViewModel =  viewModel ( factory =  PenyediaViewModel .Factory)
) {
    val coroutineScope  =  rememberCoroutineScope ()

    val scrollBehavior  =  TopAppBarDefaults . enterAlwaysScrollBehavior ()
    Scaffold (
        modifier =  modifier . nestedScroll ( scrollBehavior .nestedScrollConnection),
        topBar =  {
            CostumeTopAppBar (
                title =  DestinasiEntryEvent .titleRes,
                canNavigateBack =  true ,
                scrollBehavior =  scrollBehavior ,
                navigateUp =  navigateBack
            )
        }
    )  {  innerPadding  ->
        EntryBodyEvent (
            insertEvnUiState =  viewModel .evntuiState,
            onEventValueChange =  viewModel :: updateInsertEvntState,
            onSaveClick =  {
                coroutineScope . launch  {
                    viewModel . insertEvnt ()
                    navigateBack ()
                }
            },
            modifier =  Modifier
                . padding ( innerPadding )
                . verticalScroll ( rememberScrollState ())
                . fillMaxWidth ()
        )

    }
}

@Composable
fun EntryBodyEvent (
    insertEvnUiState : InsertEventUiState,
    onEventValueChange :  (InsertevntUiEvent)  ->  Unit,
    onSaveClick :  ()  ->  Unit,
    modifier : Modifier =  Modifier
) {
    Column (
        verticalArrangement =  Arrangement . spacedBy ( 18 . dp ),
        modifier =  modifier . padding ( 12 . dp )
    )  {
        FormInputEvent (
            insertEvnUiEvent =  insertEvnUiState.insertevntUiEvent,
            onValueChange =  onEventValueChange ,
            modifier =  Modifier . fillMaxWidth ()
        )
        Button (
            onClick =  onSaveClick ,
            shape =  MaterialTheme . shapes .small,
            modifier =  Modifier . fillMaxWidth ()
        )  {
            Text ( text =  "Simpan" )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormInputEvent (
    insertEvnUiEvent : InsertevntUiEvent,
    modifier : Modifier =  Modifier,
    onValueChange :  (InsertevntUiEvent)  ->  Unit  =  {},
    enabled :  Boolean  = true
) {
    Column (
        modifier =  modifier ,
        verticalArrangement =  Arrangement . spacedBy ( 12 . dp )
    )  {
        OutlinedTextField (
            value =  insertEvnUiEvent.id_event,
            onValueChange =  {  onValueChange ( insertEvnUiEvent . copy ( id_event =  it ))  } ,
            label =  {  Text ( "ID Event" )  } ,
            modifier =  Modifier . fillMaxWidth (),
            enabled =  enabled ,
            singleLine =  true
        )
        OutlinedTextField (
            value =  insertEvnUiEvent .nama_event,
            onValueChange =  {  onValueChange ( insertEvnUiEvent . copy ( nama_event =  it ))  } ,
            label =  {  Text ( "Nama Event" )  } ,
            modifier =  Modifier . fillMaxWidth (),
            enabled =  enabled ,
            singleLine =  true
        )
        OutlinedTextField (
            value =  insertEvnUiEvent .deskripsi_event,
            onValueChange =  {  onValueChange ( insertEvnUiEvent . copy ( deskripsi_event =  it ))  } ,
            label =  {  Text ( "Deskripsi Event" )  } ,
            modifier =  Modifier . fillMaxWidth (),
            enabled =  enabled ,
            singleLine =  true
        )
        OutlinedTextField (
            value =  insertEvnUiEvent .tanggal_event,
            onValueChange =  {  onValueChange ( insertEvnUiEvent . copy ( tanggal_event =  it ))  } ,
            label =  {  Text ( "Tanggal Event" )  } ,
            modifier =  Modifier . fillMaxWidth (),
            enabled =  enabled ,
            singleLine =  true
        )
        OutlinedTextField (
            value =  insertEvnUiEvent .lokasi_event,
            onValueChange =  {  onValueChange ( insertEvnUiEvent . copy ( lokasi_event =  it ))  } ,
            label =  {  Text ( "Lokasi Event" )  } ,
            modifier =  Modifier . fillMaxWidth (),
            enabled =  enabled ,
            singleLine =  true
        )
        if  ( enabled ) {
            Text (
                text =  "Isi Semua Data!" ,
                modifier =  Modifier . padding ( 12 . dp )
            )
        }
        Divider (
            thickness =  8 . dp ,
            modifier =  Modifier . padding ( 12 . dp )
        )
    }
}