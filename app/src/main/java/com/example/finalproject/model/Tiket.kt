package com.example.finalproject.model

import kotlinx.serialization.Serializable

@Serializable
data class Tiket(
    val id_tiket: String,
    val id_event: String,
    val id_peserta: String,
    val kapasitas_tiket: String,
    val harga_tiket: String
)