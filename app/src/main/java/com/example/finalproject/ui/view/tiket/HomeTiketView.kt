package com.example.finalproject.ui.view.tiket

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.finalproject.model.Tiket
import com.example.finalproject.ui.CustomWidget.CostumeTopAppBar
import com.example.finalproject.ui.navigasi.DestinasiNavigasi
import com.example.finalproject.ui.viewmodel.PenyediaViewModel
import com.example.finalproject.ui.viewmodel.tiket.HomeTiketUiState
import com.example.finalproject.ui.viewmodel.tiket.HomeTiketViewModel
import com.example.prak8.R

object DestinasiTiketHome  : DestinasiNavigasi {
    override val  route  =  "home tiket"
    override val  titleRes  =  "Daftar Tiket"
}

@OptIn ( ExperimentalMaterial3Api ::class )
@Composable
fun HomeTiket (
    navigateToItemEntry :  ()  ->  Unit,
    modifier : Modifier =  Modifier,
    onDetailClick :  ( String )  ->  Unit  =  {},
    viewModel : HomeTiketViewModel =  viewModel ( factory =  PenyediaViewModel .Factory)
) {
    val scrollBehavior  =  TopAppBarDefaults . enterAlwaysScrollBehavior ()
    Scaffold (
        modifier =  modifier . nestedScroll ( scrollBehavior .nestedScrollConnection),
        topBar =  {
            CostumeTopAppBar (
                title =  DestinasiTiketHome .titleRes,
                canNavigateBack =  false ,
                scrollBehavior =  scrollBehavior ,
                onRefresh =  {
                    viewModel . getTiket ()
                }
            )
        } ,
        floatingActionButton =  {
            FloatingActionButton (
                onClick =  navigateToItemEntry ,
                shape =  MaterialTheme . shapes .medium,
                modifier =  Modifier . padding ( 18 . dp )
            )  {
                Icon ( imageVector =  Icons .Default. Add ,  contentDescription =  "Add Tiket" )

            }
        } ,
    )  {  innerPadding  ->
        HomeStatus (
            hometiketUiState =  viewModel .tiketUIState,
            retryAction =  {  viewModel . getTiket ()  } ,  modifier =  Modifier . padding ( innerPadding ),
            onDetailClick =  onDetailClick ,  onDeleteClick =  {
                viewModel . deleteTiket (it .id_tiket)
                viewModel . getTiket ()
            }
        )

    }
}

@Composable
fun HomeStatus (
    hometiketUiState : HomeTiketUiState,
    retryAction :  ()  ->  Unit,
    modifier : Modifier =  Modifier,
    onDeleteClick :  (Tiket)  ->  Unit  =  {},
    onDetailClick :  ( String )  ->  Unit
) {

    when  ( hometiketUiState ) {
        is  HomeTiketUiState. Loading  ->  OnLoading ( modifier =  modifier . fillMaxSize ())

        is  HomeTiketUiState. Success  ->
            if  ( hometiketUiState .tiket. isEmpty ()){
                return  Box ( modifier =  modifier . fillMaxSize (),  contentAlignment =  Alignment .Center)  {
                    Text ( text =  "Tidak ada data Tiket"  )

                }
            } else  {
                TiketLayout (

                    tiket =  hometiketUiState .tiket,  modifier =  modifier . fillMaxWidth (),
                    onDetailClick =  {
                        onDetailClick ( it .id_tiket)
                    },
                    onDeleteClick =  {
                        onDeleteClick ( it )
                    }
                )
            }
        is  HomeTiketUiState. Error  ->  OnError ( retryAction ,  modifier =  modifier . fillMaxSize ())
    }

}

/**
 * The home screen displaying the loading message.
 */
@Composable
fun OnLoading ( modifier : Modifier =  Modifier ) {
    Image (
        modifier =  modifier.size ( 200 . dp ),
        painter =  painterResource ( R.drawable.loading_img),
        contentDescription =  stringResource ( R.string.loading )
    )
}

/**
 * The home screen displaying error message with re - attempt button.
 */

@Composable
fun OnError ( retryAction :  ()  ->  Unit ,  modifier : Modifier =  Modifier ) {
    Column (
        modifier =  modifier ,
        verticalArrangement =  Arrangement .Center,
        horizontalAlignment =  Alignment .CenterHorizontally
    )  {
        Image (
            painter =  painterResource ( id =  R . drawable . ic_connection_error ),  contentDescription =  ""
        )
        Text ( text =  stringResource ( R . string . loading_failed ),  modifier =  Modifier . padding ( 16 . dp ))
        Button ( onClick =  retryAction )  {
            Text ( stringResource ( R . string . retry ))
        }
    }
}

@Composable
fun TiketLayout (
    tiket :  List <Tiket>,
    modifier : Modifier =  Modifier,
    onDetailClick :  (Tiket)  ->  Unit,
    onDeleteClick :  (Tiket)  ->  Unit  =  {}
) {
    LazyColumn (
        modifier =  modifier ,
        contentPadding =  PaddingValues ( 16 . dp ),
        verticalArrangement =  Arrangement . spacedBy ( 16 . dp )
    )  {
        items ( tiket )  {  tiket  ->
            TiketCard (
                tiket =  tiket ,
                modifier =  Modifier
                    . fillMaxWidth ()
                    . clickable  {  onDetailClick ( tiket )  } ,
                onDeleteClick =  {
                    onDeleteClick ( tiket )
                }
            )
        }
    }

}

@Composable
fun TiketCard (
    tiket: Tiket,
    modifier : Modifier =  Modifier,
    onDeleteClick :  (Tiket)  ->  Unit  =  {}
) {
    Card (
        modifier =  modifier ,
        shape =  MaterialTheme . shapes .medium,
        elevation =  CardDefaults . cardElevation ( defaultElevation =  8 . dp )
    )  {
        Column (
            modifier =  Modifier . padding ( 16 . dp ),
            verticalArrangement =  Arrangement . spacedBy ( 8 . dp )
        )  {
            Row (
                modifier =  Modifier . fillMaxWidth (),
                verticalAlignment =  Alignment .CenterVertically
            )  {
                Text (
                    text =  tiket .id_tiket,
                    style =  MaterialTheme . typography .titleLarge,

                    )
                Spacer ( Modifier . weight ( 1f ))
                IconButton ( onClick =  {  onDeleteClick ( tiket )  } )  {
                    Icon (
                        imageVector =  Icons .Default. Delete ,
                        contentDescription =  null ,
                    )
                }
                Text (
                    text =  tiket.id_event,
                    style =  MaterialTheme . typography .titleMedium
                )
            }
            Text (
                text =  tiket.id_peserta,
                style =  MaterialTheme . typography .titleMedium
            )
            Text (
                text =  tiket.kapasitas_tiket,
                style =  MaterialTheme . typography .titleMedium
            )
            Text (
                text =  tiket.harga_tiket,
                style =  MaterialTheme . typography .titleMedium
            )
        }
    }
}