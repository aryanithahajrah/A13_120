package com.example.finalproject.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.finalproject.PenjualanApplications
import com.example.finalproject.ui.viewmodel.event.DetailEventViewModel
import com.example.finalproject.ui.viewmodel.event.HomeEventViewModel
import com.example.finalproject.ui.viewmodel.event.InsertEventViewModel
import com.example.finalproject.ui.viewmodel.event.UpdateEventViewModel
import com.example.finalproject.ui.viewmodel.peserta.DetailPesertaViewModel
import com.example.finalproject.ui.viewmodel.peserta.HomePesertaViewModel
import com.example.finalproject.ui.viewmodel.peserta.InsertPesertaViewModel
import com.example.finalproject.ui.viewmodel.peserta.UpdatePesertaViewModel
import com.example.finalproject.ui.viewmodel.tiket.DetailTiketViewModel
import com.example.finalproject.ui.viewmodel.tiket.HomeTiketViewModel
import com.example.finalproject.ui.viewmodel.tiket.InsertTiketViewModel
import com.example.finalproject.ui.viewmodel.tiket.UpdateTiketViewModel
import com.example.finalproject.ui.viewmodel.transaksi.DetailTransaksiViewModel
import com.example.finalproject.ui.viewmodel.transaksi.HomeTransaksiViewModel
import com.example.finalproject.ui.viewmodel.transaksi.InsertTransaksiViewModel
import com.example.finalproject.ui.viewmodel.transaksi.UpdateTransaksiViewModel

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer { HomePesertaViewModel(aplikasiPenjualan().container.pesertaRepository) }
        initializer { InsertPesertaViewModel(aplikasiPenjualan().container.pesertaRepository) }
        initializer { DetailPesertaViewModel(createSavedStateHandle(), pesertaRepository = aplikasiPenjualan().container.pesertaRepository) }
        initializer { UpdatePesertaViewModel(createSavedStateHandle(), pesertaRepository = aplikasiPenjualan().container.pesertaRepository) }

        initializer { HomeEventViewModel(aplikasiPenjualan().container.eventRepository) }
        initializer { InsertEventViewModel(aplikasiPenjualan().container.eventRepository) }
        initializer { DetailEventViewModel(createSavedStateHandle(), eventRepository = aplikasiPenjualan().container.eventRepository) }
        initializer { UpdateEventViewModel(createSavedStateHandle(), eventRepository = aplikasiPenjualan().container.eventRepository) }

        initializer { HomeTiketViewModel(aplikasiPenjualan().container.tiketRepository) }
        initializer { InsertTiketViewModel(aplikasiPenjualan().container.tiketRepository) }
        initializer { DetailTiketViewModel(createSavedStateHandle(), tiketRepository = aplikasiPenjualan().container.tiketRepository) }
        initializer { UpdateTiketViewModel(createSavedStateHandle(), tiketRepository = aplikasiPenjualan().container.tiketRepository) }

        initializer { HomeTransaksiViewModel(aplikasiPenjualan().container.transaksiRepository) }
        initializer { InsertTransaksiViewModel(aplikasiPenjualan().container.transaksiRepository) }
        initializer { DetailTransaksiViewModel(createSavedStateHandle(), transaksiRepository = aplikasiPenjualan().container.transaksiRepository) }
        initializer { UpdateTransaksiViewModel(createSavedStateHandle(), transaksiRepository = aplikasiPenjualan().container.transaksiRepository) }
    }
}

fun CreationExtras. aplikasiPenjualan (): PenjualanApplications =
    ( this [ ViewModelProvider . AndroidViewModelFactory .APPLICATION_KEY]  as PenjualanApplications)