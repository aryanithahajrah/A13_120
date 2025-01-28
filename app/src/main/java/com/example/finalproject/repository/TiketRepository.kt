package com.example.finalproject.repository

import com.example.finalproject.model.Tiket
import com.example.finalproject.service_api.TiketService
import java.io.IOException

interface TiketRepository {
    suspend fun getTiket(): List<Tiket>
    suspend fun insertTiket(tiket: Tiket)
    suspend fun updateTiket(id_tiket: String, tiket: Tiket)
    suspend fun deleteTiket(id_tiket: String)
    suspend fun getTiketById(id_tiket: String): Tiket
}

class NetworkTiketRepository(private val tiketService: TiketService) : TiketRepository {
    override suspend fun insertTiket(tiket: Tiket) {
        tiketService.insertTiket(tiket)
    }

    override suspend fun updateTiket(id_tiket: String, tiket: Tiket) {
        tiketService.updateTiket(id_tiket, tiket)
    }

    override suspend fun deleteTiket(id_tiket: String) {
        try {
            val response = tiketService.deleteTiket(id_tiket)
            if (!response.isSuccessful) {
                throw IOException("Failed to delete tiket. HTTP status code: ${response.code()}")
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getTiket(): List<Tiket> = tiketService.getTiket()

    override suspend fun getTiketById(id_tiket: String): Tiket = tiketService.getTiketById(id_tiket)
}