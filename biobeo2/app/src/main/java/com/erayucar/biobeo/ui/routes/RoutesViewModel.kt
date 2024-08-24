package com.erayucar.biobeo.ui.routes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erayucar.biobeo.core.common.ResponseState
import com.erayucar.biobeo.core.data.model.Route
import com.erayucar.biobeo.core.domain.GetAllRoutesUseCase
import com.erayucar.biobeo.core.network.dto.RouteDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoutesViewModel @Inject constructor(
    private val getAllRoutesUseCase: GetAllRoutesUseCase
) : ViewModel() {

    private val _routesScreenUiState = mutableStateOf(RouteListScreenUiState.initial())
    val routesScreenUiState : State<RouteListScreenUiState> = _routesScreenUiState


    fun getAllRoutes() {
        viewModelScope.launch {
            getAllRoutesUseCase().collect { responseState ->
                when (responseState) {
                    is ResponseState.Success -> {
                       _routesScreenUiState.value = RouteListScreenUiState(routes = responseState.data)
                    }

                    is ResponseState.Error -> {
                        _routesScreenUiState.value = RouteListScreenUiState(isError = true, errorMessage = responseState.message)
                    }

                    ResponseState.Loading -> {
                        _routesScreenUiState.value = RouteListScreenUiState(isLoading = true)
                    }
                }


            }
        }
    }


}

data class RouteListScreenUiState(
    val routes: List<Route> = emptyList(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String? = ""
) {
    companion object {
        fun initial() = RouteListScreenUiState()
    }
}