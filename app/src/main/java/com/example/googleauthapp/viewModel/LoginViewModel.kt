package com.example.googleauthapp.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.googleauthapp.domain.model.MessageBarState
import com.example.googleauthapp.domain.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: LoginRepository
): ViewModel() {

    private val _signedInState = MutableStateFlow(false)
    val signedInState: StateFlow<Boolean> = _signedInState

    private val _messageBarState: MutableState<MessageBarState> = mutableStateOf(MessageBarState())
    val messageBarState: State<MessageBarState> = _messageBarState

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.readSignedInState().collect { signedIn ->
                _signedInState.value = signedIn
            }
        }
    }

    fun saveSignedInState(signedIn: Boolean) = viewModelScope.launch(Dispatchers.IO) {
        repository.saveSignedInState(signedIn = signedIn)
    }

    fun updateMessageBarState() {
        _messageBarState.value = MessageBarState(error = GoogleAccountNotFoundException())
    }
}

class GoogleAccountNotFoundException(
    override val message: String? = "Google Account Not Found."
): Exception()