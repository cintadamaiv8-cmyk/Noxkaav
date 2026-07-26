package com.example.services

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

class PrayerService(private val context: Context) {
    fun hasLocationPermission(): Boolean {
        return ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
               ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }

    fun getLocationStatus(): String {
        return if (hasLocationPermission()) {
            "Lokasi berhasil ditemukan." // Mock status for now since no real GPS is accessed
        } else {
            "Lokasi belum tersedia."
        }
    }
}
