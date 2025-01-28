package com.example.finalproject.model

import kotlinx.serialization.Serializable

@Serializable
data class Transaksi(
    val id_transaksi: String,
    val id_tiket: String,
    val jumlah_tiket: String,
    val jumlah_pembayaran: String,
    val tanggal_transaksi: String
)