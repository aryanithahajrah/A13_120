package com.example.finalproject.ui.view.tiket

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
import com.example.finalproject.ui.viewmodel.PenyediaViewModel
import com.example.finalproject.ui.viewmodel.tiket.UpdateTiketViewModel
import kotlinx.coroutines.launch

object DestinasiEditTiket : DestinasiNavigasi {
    override val route = "edittiket"
    override val titleRes = "Edit Tiket"
    const val id_tiket = "id_tiket"
    val routeWithArgs = "$route/{$id_tiket}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateTiketView(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: UpdateTiketViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val coroutineScope = rememberCoroutineScope()

    Scaffold (
        topBar = {
            CostumeTopAppBar(
                title = DestinasiEditTiket.titleRes,
                canNavigateBack = true,
                navigateUp = onNavigateUp
            )
        }
    ){ innerPadding ->
        EntryBodyTiket(
            insertTiketUiState = viewModel.uiState,
            onTiketValueChange = viewModel::updateInsertTiketState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.editTiket()
                    navigateBack()
                }
            },
            modifier = modifier
                .padding(innerPadding)
                .offset(y = (-70).dp)
        )
    }
}