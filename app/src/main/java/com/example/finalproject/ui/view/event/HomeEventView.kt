package com.example.finalproject.ui.view.event

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
import com.example.finalproject.model.Event
import com.example.finalproject.ui.CustomWidget.CostumeTopAppBar
import com.example.finalproject.ui.navigasi.DestinasiNavigasi
import com.example.finalproject.ui.viewmodel.event.HomeEventUiState
import com.example.finalproject.ui.viewmodel.event.HomeEventViewModel
import com.example.finalproject.ui.viewmodel.PenyediaViewModel
import com.example.prak8.R

object DestinasiEventHome  : DestinasiNavigasi {
    override val  route  =  "home event"
    override val  titleRes  =  "Daftar Event"
}

@OptIn ( ExperimentalMaterial3Api ::class )
@Composable
fun HomeEvent (
    navigateToItemEntry :  ()  ->  Unit,
    modifier : Modifier =  Modifier,
    onDetailClick :  ( String )  ->  Unit  =  {},
    viewModel : HomeEventViewModel =  viewModel ( factory =  PenyediaViewModel .Factory)
) {
    val scrollBehavior  =  TopAppBarDefaults . enterAlwaysScrollBehavior ()
    Scaffold (
        modifier =  modifier . nestedScroll ( scrollBehavior .nestedScrollConnection),
        topBar =  {
            CostumeTopAppBar (
                title =  DestinasiEventHome .titleRes,
                canNavigateBack =  false ,
                scrollBehavior =  scrollBehavior ,
                onRefresh =  {
                    viewModel . getEvnt ()
                }
            )
        } ,
        floatingActionButton =  {
            FloatingActionButton (
                onClick =  navigateToItemEntry ,
                shape =  MaterialTheme . shapes .medium,
                modifier =  Modifier . padding ( 18 . dp )
            )  {
                Icon ( imageVector =  Icons .Default. Add ,  contentDescription =  "Tambah Event" )

            }
        } ,
    )  {  innerPadding  ->
        HomeStatus (
            homeevnUiState =  viewModel .evntUIState,
            retryAction =  {  viewModel . getEvnt ()  } ,  modifier =  Modifier . padding ( innerPadding ),
            onDetailClick =  onDetailClick ,  onDeleteClick =  {
                viewModel . deleteEvnt (it .id_event)
                viewModel . getEvnt ()
            }
        )

    }
}

@Composable
fun HomeStatus (
    homeevnUiState : HomeEventUiState,
    retryAction :  ()  ->  Unit,
    modifier : Modifier =  Modifier,
    onDeleteClick :  (Event)  ->  Unit  =  {},
    onDetailClick :  ( String )  ->  Unit
) {

    when  ( homeevnUiState ) {
        is  HomeEventUiState. Loading  ->  OnLoading ( modifier =  modifier . fillMaxSize ())

        is  HomeEventUiState. Success  ->
            if  ( homeevnUiState .event. isEmpty ()){
                return  Box ( modifier =  modifier . fillMaxSize (),  contentAlignment =  Alignment .Center)  {
                    Text ( text =  "Tidak ada data Event"  )

                }
            } else  {
                EventLayout (

                    event =  homeevnUiState .event,  modifier =  modifier . fillMaxWidth (),
                    onDetailClick =  {
                        onDetailClick ( it .id_event)
                    },
                    onDeleteClick =  {
                        onDeleteClick ( it )
                    }
                )
            }
        is  HomeEventUiState. Error  ->  OnError ( retryAction ,  modifier =  modifier . fillMaxSize ())
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
fun EventLayout (
    event :  List <Event>,
    modifier : Modifier =  Modifier,
    onDetailClick :  (Event)  ->  Unit,
    onDeleteClick :  (Event)  ->  Unit  =  {}
) {
    LazyColumn (
        modifier =  modifier ,
        contentPadding =  PaddingValues ( 16 . dp ),
        verticalArrangement =  Arrangement . spacedBy ( 16 . dp )
    )  {
        items ( event )  {  event  ->
            EventCard (
                event =  event ,
                modifier =  Modifier
                    . fillMaxWidth ()
                    . clickable  {  onDetailClick ( event )  } ,
                onDeleteClick =  {
                    onDeleteClick ( event )
                }
            )
        }
    }
}

@Composable
fun EventCard (
    event: Event,
    modifier : Modifier =  Modifier,
    onDeleteClick :  (Event)  ->  Unit  =  {}
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
                    text =  event .id_event,
                    style =  MaterialTheme . typography .titleLarge,

                    )
                Spacer ( Modifier . weight ( 1f ))
                IconButton ( onClick =  {  onDeleteClick ( event )  } )  {
                    Icon (
                        imageVector =  Icons .Default. Delete ,
                        contentDescription =  null ,
                    )
                }
            }
            Text (
                text =  event .nama_event,
                style =  MaterialTheme . typography .titleMedium
            )
            /*Text (
                text =  event .deskripsi_event,
                style =  MaterialTheme . typography .titleMedium
            )*/
            Text (
                text =  event .tanggal_event,
                style =  MaterialTheme . typography .titleMedium
            )
            Text (
                text =  event .lokasi_event,
                style =  MaterialTheme . typography .titleMedium
            )
        }
    }
}