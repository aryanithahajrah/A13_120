package com.example.finalproject.ui.view.transaksi

import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.finalproject.ui.CustomWidget.CostumeTopAppBar
import com.example.finalproject.ui.navigasi.DestinasiNavigasi
import com.example.finalproject.ui.view.transaksi.EntryBodyTransaksi
import com.example.finalproject.ui.viewmodel.PenyediaViewModel
import com.example.finalproject.ui.viewmodel.transaksi.UpdateTransaksiViewModel
import kotlinx.coroutines.launch

object DestinasiEditTransaksi : DestinasiNavigasi {
    override val route = "edittransaksi"
    override val titleRes = "Edit Transaksi"
    const val id_transaksi = "id_transaksi"
    val routeWithArgs = "$route/{$id_transaksi}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateTransaksiView(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: UpdateTransaksiViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val coroutineScope = rememberCoroutineScope()

    Scaffold (
        topBar = {
            CostumeTopAppBar(
                title = DestinasiEditTransaksi.titleRes,
                canNavigateBack = true,
                navigateUp = onNavigateUp
            )
        }
    ){ innerPadding ->
        EntryBodyTransaksi(
            insertTransaksiUiState = viewModel.uiState,
            onTransaksiValueChange = viewModel::updateInsertTransaksiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.editTransaksi()
                    navigateBack()
                }
            },
            modifier = modifier
                .padding(innerPadding)
                .offset(y = (-70).dp)
        )
    }
}