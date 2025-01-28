package com.example.finalproject.ui.view.event

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
import com.example.finalproject.ui.viewmodel.event.UpdateEventViewModel
import com.example.finalproject.ui.viewmodel.PenyediaViewModel
import kotlinx.coroutines.launch

object DestinasiEditEvent : DestinasiNavigasi {
    override val route = "editevent"
    override val titleRes = "Edit Event"
    const val id_event = "id_event"
    val routeWithArgs = "$route/{$id_event}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateEventView(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: UpdateEventViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            CostumeTopAppBar(
                title = DestinasiEditEvent.titleRes,
                canNavigateBack = true,
                navigateUp = onNavigateUp
            )
        }
    ) { innerPadding ->
        EntryBodyEvent(
            insertEvnUiState = viewModel.uiState,
            onEventValueChange = viewModel::updateInsertEventState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.editEvent()
                    navigateBack()
                }
            },
            modifier = modifier
                .padding(innerPadding)
                .offset(y = (-70).dp)
        )
    }
}