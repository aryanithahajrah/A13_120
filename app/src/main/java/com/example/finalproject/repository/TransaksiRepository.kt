package com.example.finalproject.repository

import com.example.finalproject.model.Transaksi
import com.example.finalproject.service_api.TransaksiService
import java.io.IOException

interface TransaksiRepository {
    suspend fun getTransaksi(): List<Transaksi>
    suspend fun insertTransaksi(transaksi: Transaksi)
    suspend fun updateTransaksi(id_transaksi: String, transaksi: Transaksi)
    suspend fun deleteTransaksi(id_transaksi: String)
    suspend fun getTransaksiById(id_transaksi: String): Transaksi
}

class NetworkTransaksiRepository(private val transaksiService: TransaksiService) : TransaksiRepository {
    override suspend fun insertTransaksi(transaksi: Transaksi) {
        transaksiService.insertTransaksi(transaksi)
    }

    override suspend fun updateTransaksi(id_transaksi: String, transaksi: Transaksi) {
        transaksiService.updateTransaksi(id_transaksi, transaksi)
    }

    override suspend fun deleteTransaksi(id_transaksi: String) {
        try {
            val response = transaksiService.deleteTransaksi(id_transaksi)
            if (!response.isSuccessful) {
                throw IOException("Failed to delete transaksi. HTTP status code: ${response.code()}")
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getTransaksi(): List<Transaksi> = transaksiService.getTransaksi()

    override suspend fun getTransaksiById(id_transaksi: String): Transaksi = transaksiService.getTransaksiById(id_transaksi)
}