package com.example.finalproject.ui.navigasi

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.finalproject.ui.view.DestinasiMenu
import com.example.finalproject.ui.view.HomeMenuView
import com.example.finalproject.ui.view.event.DestinasiDetailEvent
import com.example.finalproject.ui.view.event.DestinasiEditEvent
import com.example.finalproject.ui.view.event.DestinasiEntryEvent
import com.example.finalproject.ui.view.event.DestinasiEventHome
import com.example.finalproject.ui.view.event.DetailEventView
import com.example.finalproject.ui.view.event.EntryEventScreen
import com.example.finalproject.ui.view.event.HomeEvent
import com.example.finalproject.ui.view.event.UpdateEventView
import com.example.finalproject.ui.view.peserta.DestinasiDetailPeserta
import com.example.finalproject.ui.view.peserta.DestinasiEditPeserta
import com.example.finalproject.ui.view.peserta.DestinasiEntryPeserta
import com.example.finalproject.ui.view.peserta.DestinasiPesertaHome
import com.example.finalproject.ui.view.peserta.DetailPesertaView
import com.example.finalproject.ui.view.peserta.EntryPesertaScreen
import com.example.finalproject.ui.view.peserta.HomeScreen
import com.example.finalproject.ui.view.peserta.UpdatePesertaView
import com.example.finalproject.ui.view.tiket.DestinasiDetailTiket
import com.example.finalproject.ui.view.tiket.DestinasiEditTiket
import com.example.finalproject.ui.view.tiket.DestinasiEntryTiket
import com.example.finalproject.ui.view.tiket.DestinasiTiketHome
import com.example.finalproject.ui.view.tiket.DetailTiketView
import com.example.finalproject.ui.view.tiket.EntryTiketScreen
import com.example.finalproject.ui.view.tiket.HomeTiket
import com.example.finalproject.ui.view.tiket.UpdateTiketView
import com.example.finalproject.ui.view.transaksi.DestinasiDetailTransaksi
import com.example.finalproject.ui.view.transaksi.DestinasiEditTransaksi
import com.example.finalproject.ui.view.transaksi.DestinasiEntryTransaksi
import com.example.finalproject.ui.view.transaksi.DestinasiTransaksiHome
import com.example.finalproject.ui.view.transaksi.DetailTransaksiView
import com.example.finalproject.ui.view.transaksi.EntryTransaksiScreen
import com.example.finalproject.ui.view.transaksi.HomeTransaksi
import com.example.finalproject.ui.view.transaksi.UpdateTransaksiView

@Composable
fun PengelolaHalaman(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = DestinasiMenu.route,
        modifier = Modifier
    ) {
        composable(
            route = DestinasiMenu.route
        ){
            HomeMenuView(
                onPesertaClick = {
                    navController.navigate(DestinasiPesertaHome.route)
                },
                onEventClick = {
                    navController.navigate(DestinasiEventHome.route)
                },
                onTiketClick = {
                    navController.navigate(DestinasiTiketHome.route)
                },
                onTransaksiClick = {
                    navController.navigate(DestinasiTransaksiHome.route)
                }
            )
        }

        // PESERTA
        composable(
            route = DestinasiPesertaHome.route
        ){
            HomeScreen(
                navigateToItemEntry = { navController.navigate(DestinasiEntryPeserta.route) },
                onDetailClick = { id_peserta ->
                    navController.navigate("${DestinasiDetailPeserta.route}/$id_peserta")
                    println(id_peserta)
                }
            )
        }
        composable(
            route = DestinasiEntryPeserta.route
        ){
            EntryPesertaScreen(
                navigateBack = {
                    navController.navigate(DestinasiPesertaHome.route) {
                        popUpTo(DestinasiPesertaHome.route) { inclusive = true }
                    }
                }
            )
        }
        composable(
            route = DestinasiDetailPeserta.routeWithArgs,
            arguments = listOf(navArgument(DestinasiDetailPeserta.id_peserta){
                type = NavType.StringType
            })
        ){ backStackEntry ->
            val id_peserta = backStackEntry.arguments?.getString(DestinasiDetailPeserta.id_peserta)
            id_peserta?.let {
                DetailPesertaView(
                    navigateBack = {
                        navController.navigateUp()
                    },
                    onEditClick = { id_peserta ->
                        navController.navigate("${DestinasiEditPeserta.route}/$id_peserta")
                        println(id_peserta)
                    }
                )
            }
        }
        composable(
            route = DestinasiEditPeserta.routeWithArgs,
            arguments = listOf(navArgument(DestinasiEditPeserta.id_peserta){
                type = NavType.StringType
            })
        ){
            UpdatePesertaView(
                navigateBack = {
                    navController.popBackStack()
                },
                onNavigateUp = {
                    navController.navigate(
                        DestinasiEditPeserta.route
                    ){
                        popUpTo(DestinasiPesertaHome.route){
                            inclusive = true
                        }
                    }
                }
            )
        }

        // EVENT
        composable(
            route = DestinasiEventHome.route
        ){
            HomeEvent(
                navigateToItemEntry = { navController.navigate(DestinasiEntryEvent.route) },
                onDetailClick = { id_event ->
                    navController.navigate("${DestinasiDetailEvent.route}/$id_event")
                    println(id_event)
                }
            )
        }

        composable(
            route = DestinasiEntryEvent.route
        ){
            EntryEventScreen(
                navigateBack = {
                    navController.navigate(DestinasiEventHome.route) {
                        popUpTo(DestinasiEventHome.route) { inclusive = true }
                    }
                }
            )
        }
        composable(
            route = DestinasiDetailEvent.routeWithArgs,
            arguments = listOf(navArgument(DestinasiDetailEvent.id_event){
                type = NavType.StringType
            })
        ){ backStackEntry ->
            val id_event = backStackEntry.arguments?.getString(DestinasiDetailEvent.id_event)
            id_event?.let {
                DetailEventView(
                    navigateBack = {
                        navController.navigateUp()
                    },
                    onEditClick = { id_event ->
                        navController.navigate("${DestinasiEditEvent.route}/$id_event")
                        println(id_event)
                    }
                )
            }
        }
        composable(
            route = DestinasiEditEvent.routeWithArgs,
            arguments = listOf(navArgument(DestinasiEditEvent.id_event){
                type = NavType.StringType
            })
        ){
            UpdateEventView(
                navigateBack = {
                    navController.popBackStack()
                },
                onNavigateUp = {
                    navController.navigate(
                        DestinasiEntryEvent.route
                    ){
                        popUpTo(DestinasiEventHome.route){
                            inclusive = true
                        }
                    }
                }
            )
        }

        //TIKET
        composable(
            route = DestinasiTiketHome.route
        ){
            HomeTiket(
                navigateToItemEntry = { navController.navigate(DestinasiEntryTiket.route) },
                onDetailClick = { id_tiket ->
                    navController.navigate("${DestinasiDetailTiket.route}/$id_tiket")
                    println(id_tiket)
                }
            )
        }
        composable(
            route = DestinasiEntryTiket.route
        ){
            EntryTiketScreen(
                navigateBack = {
                    navController.navigate(DestinasiTiketHome.route) {
                        popUpTo(DestinasiTiketHome.route) { inclusive = true }
                    }
                }
            )
        }
        composable(
            route = DestinasiDetailTiket.routeWithArgs,
            arguments = listOf(navArgument(DestinasiDetailTiket.id_tiket){
                type = NavType.StringType
            })
        ){ backStackEntry ->
            val id_tiket = backStackEntry.arguments?.getString(DestinasiDetailTiket.id_tiket)
            id_tiket?.let {
                DetailTiketView(
                    navigateBack = {
                        navController.navigateUp()
                    },
                    onEditClick = { id_tiket ->
                        navController.navigate("${DestinasiEditTiket.route}/$id_tiket")
                        println(id_tiket)
                    }
                )
            }
        }
        composable(
            route = DestinasiEditTiket.routeWithArgs,
            arguments = listOf(navArgument(DestinasiEditTiket.id_tiket){
                type = NavType.StringType
            })
        ){
            UpdateTiketView(
                navigateBack = {
                    navController.popBackStack()
                },
                onNavigateUp = {
                    navController.navigate(
                        DestinasiEditTiket.route
                    ){
                        popUpTo(DestinasiTiketHome.route){
                            inclusive = true
                        }
                    }
                }
            )
        }

        // TRANSAKSI
        composable(
            route = DestinasiTransaksiHome.route
        ){
            HomeTransaksi(
                navigateToItemEntry = { navController.navigate(DestinasiEntryTransaksi.route) },
                onDetailClick = { id_transaksi ->
                    navController.navigate("${DestinasiDetailTransaksi.route}/$id_transaksi")
                    println(id_transaksi)
                }
            )
        }
        composable(
            route = DestinasiEntryTransaksi.route
        ){
            EntryTransaksiScreen(
                navigateBack = {
                    navController.navigate(DestinasiTransaksiHome.route) {
                        popUpTo(DestinasiTransaksiHome.route) { inclusive = true }
                    }
                }
            )
        }
        composable(
            route = DestinasiDetailTransaksi.routeWithArgs,
            arguments = listOf(navArgument(DestinasiDetailTransaksi.id_transaksi){
                type = NavType.StringType
            })
        ){ backStackEntry ->
            val id_transaksi = backStackEntry.arguments?.getString(DestinasiDetailTransaksi.id_transaksi)
            id_transaksi?.let {
                DetailTransaksiView(
                    navigateBack = {
                        navController.navigateUp()
                    },
                    onEditClick = { id_transaksi ->
                        navController.navigate("${DestinasiEditTransaksi.route}/$id_transaksi")
                        println(id_transaksi)
                    }
                )
            }
        }
        composable(
            route = DestinasiEditTransaksi.routeWithArgs,
            arguments = listOf(navArgument(DestinasiEditTransaksi.id_transaksi){
                type = NavType.StringType
            })
        ){
            UpdateTransaksiView(
                navigateBack = {
                    navController.popBackStack()
                },
                onNavigateUp = {
                    navController.navigate(
                        DestinasiEditTransaksi.route
                    ){
                        popUpTo(DestinasiTransaksiHome.route){
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}