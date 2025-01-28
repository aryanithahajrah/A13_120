package com.example.finalproject.di

import com.example.finalproject.repository.EventRepository
import com.example.finalproject.repository.NetworkEventRepository
import com.example.finalproject.repository.NetworkPesertaRepository
import com.example.finalproject.repository.NetworkTiketRepository
import com.example.finalproject.repository.NetworkTransaksiRepository
import com.example.finalproject.repository.PesertaRepository
import com.example.finalproject.repository.TiketRepository
import com.example.finalproject.repository.TransaksiRepository
import com.example.finalproject.service_api.EventService
import com.example.finalproject.service_api.PesertaService
import com.example.finalproject.service_api.TiketService
import com.example.finalproject.service_api.TransaksiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val eventRepository: EventRepository
    val pesertaRepository: PesertaRepository
    val tiketRepository: TiketRepository
    val transaksiRepository: TransaksiRepository
}

class PenjualanContainer : AppContainer {
    private val baseUrl = "http://192.168.1.4/finalproject/"
    private val json = Json { ignoreUnknownKeys = true }
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl).build()

    private val eventService: EventService by lazy { retrofit.create(EventService::class.java) }
    private val pesertaService: PesertaService by lazy { retrofit.create(PesertaService::class.java) }
    private val tiketService: TiketService by lazy { retrofit.create(TiketService::class.java) }
    private val transaksiService: TransaksiService by lazy { retrofit.create(TransaksiService::class.java) }

    override val eventRepository: EventRepository by lazy { NetworkEventRepository(eventService) }
    override val pesertaRepository: PesertaRepository by lazy { NetworkPesertaRepository(pesertaService) }
    override val tiketRepository: TiketRepository by lazy { NetworkTiketRepository(tiketService) }
    override val transaksiRepository: TransaksiRepository by lazy { NetworkTransaksiRepository(transaksiService) }
}