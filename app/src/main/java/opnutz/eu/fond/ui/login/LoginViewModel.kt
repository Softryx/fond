package opnutz.eu.fond.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import opnutz.eu.fond.data.repository.ProfileRepository
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: ProfileRepository
) : ViewModel() {

    val users = userRepository.watchProfiles()


    fun createUser(name: String) {
        viewModelScope.launch {
            userRepository.createProfile(name)
        }
    }
}