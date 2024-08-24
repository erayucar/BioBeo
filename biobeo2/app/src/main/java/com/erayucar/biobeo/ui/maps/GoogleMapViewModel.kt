package com.erayucar.biobeo.ui.maps

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erayucar.biobeo.core.common.ResponseState
import com.erayucar.biobeo.core.data.LocationTracker
import com.erayucar.biobeo.core.data.mapTo
import com.erayucar.biobeo.core.data.model.POI
import com.erayucar.biobeo.core.domain.GetPOIUseCase
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.PolyUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class GoogleMapViewModel @Inject constructor(
    private val getPOIUseCase: GetPOIUseCase,
    private val locationTracker: LocationTracker
) : ViewModel() {

    private val _poiListUiState = mutableStateOf(POIListUiState.initial())
    val poiListUiState: State<POIListUiState> = _poiListUiState
    private val _currentLocationState = mutableStateOf(POIListUiState.initial())
    val currentLocationState: State<POIListUiState> = _currentLocationState

    val testLocations = mutableStateOf(listOf(LatLng(0.0, 0.0)))


    fun getPOI() {
        viewModelScope.launch {
            getPOIUseCase().collect { responseState ->
                when (responseState) {
                    is ResponseState.Error -> {
                        _poiListUiState.value =
                            POIListUiState(isError = true, errorMessage = responseState.message)
                    }

                    ResponseState.Loading -> {
                        _poiListUiState.value = POIListUiState(isLoading = true)
                    }

                    is ResponseState.Success -> {
                        _poiListUiState.value = POIListUiState(POIs = responseState.data)
                    }
                }
            }
        }
    }

    fun getCurrentLocation() {
        viewModelScope.launch {
            locationTracker.getCurrentLocation().collect { responseState ->
                when (responseState) {
                    is ResponseState.Error -> {
                        _currentLocationState.value =
                            POIListUiState(isError = true, errorMessage = responseState.message)
                    }

                    ResponseState.Loading -> {
                        _currentLocationState.value = POIListUiState(isLoading = true)
                    }

                    is ResponseState.Success -> {
                        _currentLocationState.value = POIListUiState(currentLocation = responseState.data.mapTo {
                            LatLng(it.latitude, it.longitude)
                        })
                    }
                }

            }
        }

    }


    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun getAddressFromLocation(
        context: Context,
        latitude: Double,
        longitude: Double
    ) {
        viewModelScope.launch {
            val geocoder = Geocoder(context, Locale.getDefault())

            val addresses = mutableListOf<Address>()
            geocoder.getFromLocation(latitude, longitude, 1,
                @RequiresApi(Build.VERSION_CODES.TIRAMISU)
                object : Geocoder.GeocodeListener {
                    override fun onGeocode(p0: MutableList<Address>) {
                        addresses.addAll(p0)
                    }

                    override fun onError(errorMessage: String?) {
                        super.onError(errorMessage)
                    }

                }
            )
        }
    }


    fun isLocationInsideCircle(
        loc1: LatLng,
        loc2: LatLng,
        radius: Double
    ): Boolean {
        val distance = FloatArray(1)
        Location.distanceBetween(
            loc1.latitude,
            loc1.longitude,
            loc2.latitude,
            loc2.longitude,
            distance
        )
        return distance[0] <= radius
    }

    fun isLocationInsideTriangle(center: LatLng, location: LatLng, radius: Double): Boolean {
        return PolyUtil.containsLocation(location, calculateTrianglePoint(center, radius), true)
    }

    fun calculateTrianglePoint(center: LatLng, radius: Double): List<LatLng> {
        val point1 = calculatePoint(center, radius, 0.0)
        val point2 = calculatePoint(center, radius, 120.0)
        val point3 = calculatePoint(center, radius, -120.0)
        return listOf(point1, point2, point3)
    }

    private fun calculatePoint(center: LatLng, radius: Double, angle: Double): LatLng {
        val x = center.latitude + radius * Math.cos(Math.toRadians(angle))
        val y = center.longitude + radius * Math.sin(Math.toRadians(angle))
        return LatLng(x, y)
    }


    fun testLocations() {
        val grandPlaceBrussels = LatLng(50.8467, 4.3525)
        val mannekenPis = LatLng(50.8452, 4.3508)
        val galeriesRoyalesSaintHubert = LatLng(50.8475, 4.3581)
        val montDesArts = LatLng(50.8424, 4.3580)

        testLocations.value = listOf(
            grandPlaceBrussels,
            mannekenPis,
            galeriesRoyalesSaintHubert,
            montDesArts
        )

    }
}


data class POIListUiState(
    val POIs: List<POI> = emptyList(),
    val currentLocation: LatLng = LatLng(0.0, 0.0),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
) {
    companion object {
        fun initial() = POIListUiState()
    }
}