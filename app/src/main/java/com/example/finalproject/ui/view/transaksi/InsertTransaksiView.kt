package com.example.finalproject.ui.view.transaksi

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
import com.example.finalproject.ui.viewmodel.PenyediaViewModel
import com.example.finalproject.ui.viewmodel.transaksi.InsertTransaksiUiState
import com.example.finalproject.ui.viewmodel.transaksi.InsertTransaksiViewModel
import com.example.finalproject.ui.viewmodel.transaksi.InserttransaksiUiTransaksi
import kotlinx.coroutines.launch

object DestinasiEntryTransaksi: DestinasiNavigasi {
    override val route="item_entrytransaksi"
    override val titleRes="Entry Transaksi"
}

@OptIn ( ExperimentalMaterial3Api ::class )
@Composable
fun EntryTransaksiScreen (
    navigateBack :  ()  ->  Unit,
    modifier : Modifier =  Modifier,
    viewModel : InsertTransaksiViewModel =  viewModel ( factory =  PenyediaViewModel .Factory)
) {
    val coroutineScope  =  rememberCoroutineScope ()

    val scrollBehavior  =  TopAppBarDefaults . enterAlwaysScrollBehavior ()
    Scaffold (
        modifier =  modifier . nestedScroll ( scrollBehavior .nestedScrollConnection),
        topBar =  {
            CostumeTopAppBar (
                title =  DestinasiEntryTransaksi .titleRes,
                canNavigateBack =  true ,
                scrollBehavior =  scrollBehavior ,
                navigateUp =  navigateBack
            )
        }
    )  {  innerPadding  ->
        EntryBodyTransaksi (
            insertTransaksiUiState =  viewModel .transaksiuiState,
            onTransaksiValueChange =  viewModel :: updateInsertTransaksiState,
            onSaveClick =  {
                coroutineScope . launch  {
                    viewModel . insertTransaksi ()
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
fun EntryBodyTransaksi (
    insertTransaksiUiState : InsertTransaksiUiState,
    onTransaksiValueChange :  (InserttransaksiUiTransaksi)  ->  Unit,
    onSaveClick :  ()  ->  Unit,
    modifier : Modifier =  Modifier
) {
    Column (
        verticalArrangement =  Arrangement . spacedBy ( 18 . dp ),
        modifier =  modifier . padding ( 12 . dp )
    )  {
        FormInputTransaksi (
            insertTransaksiUiTransaksi =  insertTransaksiUiState.inserttransaksiUiTransaksi,
            onValueChange =  onTransaksiValueChange ,
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
fun FormInputTransaksi (
    insertTransaksiUiTransaksi : InserttransaksiUiTransaksi,
    modifier : Modifier =  Modifier,
    onValueChange :  (InserttransaksiUiTransaksi)  ->  Unit  =  {},
    enabled :  Boolean  = true
) {
    Column (
        modifier =  modifier ,
        verticalArrangement =  Arrangement . spacedBy ( 12 . dp )
    )  {
        OutlinedTextField (
            value =  insertTransaksiUiTransaksi.id_transaksi,
            onValueChange =  {  onValueChange ( insertTransaksiUiTransaksi . copy ( id_transaksi =  it ))  } ,
            label =  {  Text ( "ID Transaksi" )  } ,
            modifier =  Modifier . fillMaxWidth (),
            enabled =  enabled ,
            singleLine =  true
        )
        OutlinedTextField (
            value =  insertTransaksiUiTransaksi .id_tiket,
            onValueChange =  {  onValueChange ( insertTransaksiUiTransaksi . copy ( id_tiket =  it ))  } ,
            label =  {  Text ( "ID Tiket" )  } ,
            modifier =  Modifier . fillMaxWidth (),
            enabled =  enabled ,
            singleLine =  true
        )
        OutlinedTextField (
            value =  insertTransaksiUiTransaksi .jumlah_tiket,
            onValueChange =  {  onValueChange ( insertTransaksiUiTransaksi . copy ( jumlah_tiket =  it ))  } ,
            label =  {  Text ( "Jumlah Tiket" )  } ,
            modifier =  Modifier . fillMaxWidth (),
            enabled =  enabled ,
            singleLine =  true
        )
        OutlinedTextField (
            value =  insertTransaksiUiTransaksi .jumlah_pembayaran,
            onValueChange =  {  onValueChange ( insertTransaksiUiTransaksi . copy ( jumlah_pembayaran =  it ))  } ,
            label =  {  Text ( "Jumlah Pembayaran" )  } ,
            modifier =  Modifier . fillMaxWidth (),
            enabled =  enabled ,
            singleLine =  true
        )
        OutlinedTextField (
            value =  insertTransaksiUiTransaksi .tanggal_transaksi,
            onValueChange =  {  onValueChange ( insertTransaksiUiTransaksi . copy ( tanggal_transaksi =  it ))  } ,
            label =  {  Text ( "Tanggal Transaksi" )  } ,
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