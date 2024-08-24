package com.erayucar.biobeo.ui.statistic

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erayucar.biobeo.core.common.ResponseState
import com.erayucar.biobeo.core.data.model.Statistic
import com.erayucar.biobeo.core.domain.GetAllStatisticsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class StatisticViewModel @Inject constructor(
    private val getAllStatisticsUseCase: GetAllStatisticsUseCase
) : ViewModel() {

    private val _statisticScreenUiState = mutableStateOf(StatisticScreenUiState().initial())
    val statisticScreenUiState: State<StatisticScreenUiState> = _statisticScreenUiState


    fun getAllStatistics() {
        viewModelScope.launch {
            getAllStatisticsUseCase().collect { responseState ->
                when (responseState) {
                    is ResponseState.Loading -> {
                        _statisticScreenUiState.value = StatisticScreenUiState(isLoading = true)
                    }

                    is ResponseState.Success -> {

                        _statisticScreenUiState.value = StatisticScreenUiState(
                            statistics = responseState.data
                        )

                    }

                    is ResponseState.Error -> {
                        _statisticScreenUiState.value = StatisticScreenUiState(
                            isError = true,
                            errorMsg = responseState.message.orEmpty()
                        )
                    }
                }
            }
        }
    }
}

data class StatisticScreenUiState(
    val isLoading: Boolean = false,
    val statistics: List<Statistic> = emptyList(),
    val isError: Boolean = false,
    val errorMsg: String? = null
) {
    fun initial() = StatisticScreenUiState(isLoading = true)

}