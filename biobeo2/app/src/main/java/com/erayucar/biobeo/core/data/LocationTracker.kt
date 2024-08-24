package com.erayucar.biobeo.core.data

import android.location.Location
import com.erayucar.biobeo.core.common.ResponseState
import kotlinx.coroutines.flow.Flow

interface LocationTracker {
    suspend fun getCurrentLocation(): Flow<ResponseState<Location>>
}