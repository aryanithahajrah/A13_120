package com.example.finalproject.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.finalproject.ui.navigasi.DestinasiNavigasi

object DestinasiMenu : DestinasiNavigasi {
    override val route = "destinasi_menu"
    override val titleRes = "Menu Aplikasi"
}

@Composable
fun HomeMenuView(
    onPesertaClick: () -> Unit,
    onEventClick: () -> Unit,
    onTiketClick: () -> Unit,
    onTransaksiClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFFFF)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Judul aplikasi di tengah layar
            Text(
                text = "Manajemen Tiket",
                fontSize = 45.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF151514),
                modifier = Modifier.padding(bottom = 32.dp)
            )

            // Tombol Menu dengan ukuran lebih besar
            MenuButton("Peserta", onPesertaClick, color = Color(0xFFCFE3F0))
            MenuButton("Event", onEventClick, color = Color(0xFF7CA5BF))
            MenuButton("Tiket", onTiketClick, color = Color(0xFFFFC432))
            MenuButton("Transaksi", onTransaksiClick, color = Color(0xFFBA3C54))
        }
    }
}

@Composable
fun MenuButton(text: String, onClick: () -> Unit, color: Color) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth(0.95f)
            .height(70.dp),
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.buttonColors()
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text, fontSize = 22.sp, fontWeight = FontWeight.Bold)
        }
    }
}