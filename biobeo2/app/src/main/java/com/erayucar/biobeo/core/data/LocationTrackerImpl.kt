package com.erayucar.biobeo.core.data


import android.Manifest
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Build
import android.os.Looper
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.erayucar.biobeo.core.common.ResponseState
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationAvailability
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.Priority
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume


@ExperimentalCoroutinesApi
class LocationTrackerImpl @Inject constructor(
    private val locationClient: FusedLocationProviderClient, private val application: Application
) : LocationTracker {
    companion object {
        const val UPDATE_INTERVAL_MS: Long = 60000 // 1 dakika
        const val FASTEST_UPDATE_INTERVAL_MS: Long = 30000 // En fazla 30 saniyede bir
    }
    @RequiresApi(Build.VERSION_CODES.S)
    override suspend fun getCurrentLocation(): Flow<ResponseState<Location>> = callbackFlow {
        trySend(ResponseState.Loading).isSuccess
        val locationRequest = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY,15000)
            .setWaitForAccurateLocation(false)
            .setMinUpdateIntervalMillis(10000)
            .build()

        val locationCallback = object : LocationCallback() {
            override fun onLocationResult(p0: LocationResult) {
                p0.locations.firstOrNull()?.let {
                    trySend(ResponseState.Success(it)).isSuccess
                }
            }

            override fun onLocationAvailability(p0: LocationAvailability) {
                if (!p0.isLocationAvailable) {
                    trySend(ResponseState.Error("Location not found")).isSuccess
                }
            }
        }

        try {
            locationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper()).apply {
                awaitClose {
                    locationClient.removeLocationUpdates(locationCallback)
                }
            }
        } catch (e: SecurityException) {
            trySend(ResponseState.Error(e.message.orEmpty())).isSuccess
        }
    }
}

