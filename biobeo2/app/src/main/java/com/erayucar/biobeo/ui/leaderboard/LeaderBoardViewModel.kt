package com.erayucar.biobeo.ui.leaderboard

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erayucar.biobeo.core.common.ResponseState
import com.erayucar.biobeo.core.data.model.LeaderBoardItem
import com.erayucar.biobeo.core.domain.GetLeadeBoardUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LeaderBoardViewModel @Inject constructor(
    private val getLeaderBoardUseCase: GetLeadeBoardUseCase
) : ViewModel() {
    private val _leaderBoardList = mutableStateOf(LeaderBoardUiState.initial())
    val leaderBoardList : State<LeaderBoardUiState> get() = _leaderBoardList



    fun getLeaderBoard() {
        viewModelScope.launch {
            getLeaderBoardUseCase().collect { responseState ->

                when (responseState) {
                    is ResponseState.Loading -> {
                        _leaderBoardList.value = LeaderBoardUiState(isLoading = true)

                    }

                    is ResponseState.Error -> {
                        _leaderBoardList.value =
                            LeaderBoardUiState(
                                isError = true,
                                errorMessage = responseState.message
                            )

                    }

                    is ResponseState.Success -> {
                        _leaderBoardList.value =
                            LeaderBoardUiState(
                                leaderBoardList = responseState.data
                            )

                    }


                }

            }
        }

    }
}

data class LeaderBoardUiState(
    val leaderBoardList: List<LeaderBoardItem> = emptyList(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String? = ""
) {
    companion object {
        fun initial() = LeaderBoardUiState(isLoading = true)
    }
}