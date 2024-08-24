package com.erayucar.biobeo.ui.login

import android.content.SharedPreferences
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erayucar.biobeo.core.common.ResponseState
import com.erayucar.biobeo.core.domain.AuthUseCase
import com.erayucar.biobeo.core.network.dto.SignUpBody
import com.erayucar.biobeo.utils.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
) : ViewModel() {

    private val _loginUiState = mutableStateOf(LoginScreenUiState.initial())
    val loginUiState: State<LoginScreenUiState> get() = _loginUiState


    fun testAuthenticate(body: SignUpBody) {
        viewModelScope.launch {
            authUseCase.invoke(body).collect { responseState ->
                when (responseState) {
                    is ResponseState.Success -> {
                        _loginUiState.value = LoginScreenUiState(
                            data = LoginUiState(
                                responseState.data.message,
                                responseState.data.token,
                                responseState.data.userId
                            ))


                    }

                    is ResponseState.Error -> {
                        _loginUiState.value = LoginScreenUiState(isError = true, errorMessage = responseState.message)
                    }

                    is ResponseState.Loading -> {
                        _loginUiState.value = LoginScreenUiState(isLoading = true)
                    }
                }
            }
        }
    }
}

data class LoginScreenUiState(
    val data: LoginUiState = LoginUiState("", "", 0),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
){

    companion object {
        fun initial() = LoginScreenUiState(isLoading = true)
    }
}

data class LoginUiState(
    val message: String,
    val token: String,
    val userId: Int
)

