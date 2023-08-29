package com.example.googleauthapp.viewModel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.googleauthapp.domain.model.MessageBarState
import com.example.googleauthapp.domain.model.dto.AuthenticationApiResponse
import com.example.googleauthapp.domain.model.dto.User
import com.example.googleauthapp.domain.repository.LoginRepository
import com.example.googleauthapp.util.Constants
import com.example.googleauthapp.util.RequestState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val loginRepository: LoginRepository
): ViewModel() {

    private val _currentUser: MutableState<User?> = mutableStateOf(null)
    val currentUser: State<User?> = _currentUser

    private val _apiResponse: MutableState<RequestState<AuthenticationApiResponse>> = mutableStateOf(RequestState.Idle)
    val apiResponse: State<RequestState<AuthenticationApiResponse>> = _apiResponse

    private val _messageBarState: MutableState<MessageBarState> = mutableStateOf(MessageBarState())
    val messageBarState: State<MessageBarState> = _messageBarState

    private val _firstName = mutableStateOf("")
    val firstName: State<String> = _firstName

    private val _lastName = mutableStateOf("")
    val lastName: State<String> = _lastName

    init {
        getUserInfo()
    }

    private fun getUserInfo() = viewModelScope.launch/*(Dispatchers.IO)*/ {
        _apiResponse.value = RequestState.Loading
        try {
            val response = loginRepository.getUserInfo()
            _apiResponse.value = RequestState.Success(data = response)
            _messageBarState.value = MessageBarState(
                message = response.message,
                error = response.error
            )
            response.user?.let { user ->
                _currentUser.value = user
                user.name.split(" ").apply {
                    _firstName.value = first()
                    _lastName.value = last()
                }
            }
        } catch (e: Exception) {
            _apiResponse.value = RequestState.Error(e)
            _messageBarState.value = MessageBarState(
                message = e.message,
                error = e
            )
        }
    }

    fun updateFirstName(newValue: String) {
        if (newValue.length < Constants.MAX_CHAR_TEXT_FIELD) {
            Log.d("FuckingValue", newValue)
            _firstName.value = newValue
        }
    }

    fun updateLastName(newValue: String) {
        if (newValue.length < Constants.MAX_CHAR_TEXT_FIELD) {
            _lastName.value = newValue
        }
    }
}