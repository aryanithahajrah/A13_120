import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.finalproject.ui.viewmodel.PenyediaViewModel
import com.example.finalproject.ui.viewmodel.peserta.HomePesertaUiState
import com.example.finalproject.ui.viewmodel.peserta.HomePesertaViewModel

object ListPeserta {
    @Composable
    fun DataNamaPeserta(
        homePesertaViewModel: HomePesertaViewModel = viewModel(factory = PenyediaViewModel.Factory)
    ): List<String> {
        val pesertaUiState = homePesertaViewModel.pesertaUiState

        return when (pesertaUiState) {
            is HomePesertaUiState.Success -> {
                pesertaUiState.peserta.map { it.nama_peserta }
            }
            else -> emptyList()
        }
    }
}