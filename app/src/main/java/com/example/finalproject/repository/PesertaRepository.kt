package com.example.finalproject.repository

import com.example.finalproject.model.Peserta
import com.example.finalproject.service_api.PesertaService
import java.io.IOException

interface PesertaRepository {
    suspend fun getPeserta(): List<Peserta>
    suspend fun insertPeserta(peserta: Peserta)
    suspend fun updatePeserta(id_peserta: String, peserta: Peserta)
    suspend fun deletePeserta(id_peserta: String)
    suspend fun getPesertaById(id_peserta: String): Peserta
}

class NetworkPesertaRepository(private val pesertaService: PesertaService) : PesertaRepository {
    override suspend fun insertPeserta(peserta: Peserta) {
        pesertaService.insertPeserta(peserta)
    }

    override suspend fun updatePeserta(id_peserta: String, peserta: Peserta) {
        pesertaService.updatePeserta(id_peserta, peserta)
    }

    override suspend fun deletePeserta(id_peserta: String) {
        try {
            val response = pesertaService.deletePeserta(id_peserta)
            if (!response.isSuccessful) {
                throw IOException("Failed to delete event. HTTP status code: ${response.code()}")
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getPeserta(): List<Peserta> = pesertaService.getPeserta()
    override suspend fun getPesertaById(id_peserta: String): Peserta = pesertaService.getPesertaById(id_peserta)
}