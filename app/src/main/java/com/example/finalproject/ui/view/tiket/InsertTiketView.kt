package com.example.finalproject.ui.view.tiket

import ListPeserta
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.finalproject.ui.CustomWidget.CostumeTopAppBar
import com.example.finalproject.ui.CustomWidget.DynamicSelectTextField
import com.example.finalproject.ui.CustomWidget.ListEvent
import com.example.finalproject.ui.navigasi.DestinasiNavigasi
import com.example.finalproject.ui.viewmodel.PenyediaViewModel
import com.example.finalproject.ui.viewmodel.event.InsertevntUiEvent
import com.example.finalproject.ui.viewmodel.tiket.InsertTiketUiState
import com.example.finalproject.ui.viewmodel.tiket.InsertTiketViewModel
import com.example.finalproject.ui.viewmodel.tiket.InserttiketUiTiket
import kotlinx.coroutines.launch

object DestinasiEntryTiket: DestinasiNavigasi {
    override val route="item_entrytiket"
    override val titleRes="Entry Tiket"
}

@OptIn ( ExperimentalMaterial3Api ::class )
@Composable
fun EntryTiketScreen (
    navigateBack :  ()  ->  Unit,
    modifier : Modifier =  Modifier,
    viewModel : InsertTiketViewModel =  viewModel ( factory =  PenyediaViewModel .Factory)
) {
    val coroutineScope  =  rememberCoroutineScope ()

    val scrollBehavior  =  TopAppBarDefaults . enterAlwaysScrollBehavior ()
    Scaffold (
        modifier =  modifier . nestedScroll ( scrollBehavior .nestedScrollConnection),
        topBar =  {
            CostumeTopAppBar (
                title =  DestinasiEntryTiket .titleRes,
                canNavigateBack =  true ,
                scrollBehavior =  scrollBehavior ,
                navigateUp =  navigateBack
            )
        }
    )  {  innerPadding  ->
        EntryBodyTiket (
            insertTiketUiState =  viewModel .tiketuiState,
            onTiketValueChange =  viewModel :: updateInsertTiketState,
            onSaveClick =  {
                coroutineScope . launch  {
                    viewModel . insertTiket ()
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
fun EntryBodyTiket (
    insertTiketUiState : InsertTiketUiState,
    onTiketValueChange :  (InserttiketUiTiket)  ->  Unit,
    onSaveClick :  ()  ->  Unit,
    modifier : Modifier =  Modifier
) {
    Column (
        verticalArrangement =  Arrangement . spacedBy ( 18 . dp ),
        modifier =  modifier . padding ( 12 . dp )
    )  {
        FormInputTiket (
            insertTiketUiTiket =  insertTiketUiState.inserttiketUiTiket,
            onValueChange =  onTiketValueChange ,
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
fun FormInputTiket (
    insertTiketUiTiket : InserttiketUiTiket,
    modifier : Modifier =  Modifier,
    onValueChange :  (InserttiketUiTiket)  ->  Unit  =  {},
    enabled :  Boolean  = true
) {
    var chosenDropdownpeserta by remember { mutableStateOf((""))}
    var chosenDropdownevent by remember { mutableStateOf((""))}

    Column (
        modifier =  modifier ,
        verticalArrangement =  Arrangement . spacedBy ( 12 . dp )
    )  {
        OutlinedTextField (
            value =  insertTiketUiTiket.id_tiket,
            onValueChange =  {  onValueChange ( insertTiketUiTiket . copy ( id_tiket =  it ))  } ,
            label =  {  Text ( "ID Tiket" )  } ,
            modifier =  Modifier . fillMaxWidth (),
            enabled =  enabled ,
            singleLine =  true
        )
        OutlinedTextField (
            value =  insertTiketUiTiket .id_event,
            onValueChange =  {  onValueChange ( insertTiketUiTiket . copy ( id_event =  it ))  } ,
            label =  {  Text ( "ID Event" )  } ,
            modifier =  Modifier . fillMaxWidth (),
            enabled =  enabled ,
            singleLine =  true
        )
        DynamicSelectTextField(
            selectedValue = chosenDropdownevent,
            options = ListEvent.DataNamaEvent(),
            label = "Pilih Nama Event",
            onValueChangedPeserta = { selectedEvent -> chosenDropdownevent = selectedEvent
                onValueChange(insertTiketUiTiket.copy(id_event = selectedEvent))
            },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField (
            value =  insertTiketUiTiket .id_peserta,
            onValueChange =  {  onValueChange ( insertTiketUiTiket . copy ( id_peserta =  it ))  } ,
            label =  {  Text ( "ID Peserta" )  } ,
            modifier =  Modifier . fillMaxWidth (),
            enabled =  enabled ,
            singleLine =  true
        )
        DynamicSelectTextField(
            selectedValue = chosenDropdownpeserta,
            options = ListPeserta.DataNamaPeserta(),
            label = "Pilih Nama Peserta",
            onValueChangedPeserta = { selectedPeserta -> chosenDropdownpeserta = selectedPeserta
                onValueChange(insertTiketUiTiket.copy(id_peserta = selectedPeserta))
            },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField (
            value =  insertTiketUiTiket .kapasitas_tiket,
            onValueChange =  {  onValueChange ( insertTiketUiTiket . copy ( kapasitas_tiket =  it ))  } ,
            label =  {  Text ( "Kapasitas Tiket" )  } ,
            modifier =  Modifier . fillMaxWidth (),
            enabled =  enabled ,
            singleLine =  true
        )
        OutlinedTextField (
            value =  insertTiketUiTiket .harga_tiket,
            onValueChange =  {  onValueChange ( insertTiketUiTiket . copy ( harga_tiket =  it ))  } ,
            label =  {  Text ( "Harga Tiket" )  } ,
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