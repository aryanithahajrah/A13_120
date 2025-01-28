package com.example.finalproject

import android.app.Application
import com.example.finalproject.di.AppContainer
import com.example.finalproject.di.PenjualanContainer

class PenjualanApplications: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container= PenjualanContainer()
    }
}