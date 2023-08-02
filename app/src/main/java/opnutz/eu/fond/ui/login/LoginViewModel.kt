package opnutz.eu.fond.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import opnutz.eu.fond.data.repository.ProfileRepository
import opnutz.eu.fond.data.vo.Profile
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: ProfileRepository
) : ViewModel() {

    val profiles = userRepository.watchProfiles()

    val currentProfile = userRepository.currentProfile

    fun createUser(name: String) {
        viewModelScope.launch {
            userRepository.createProfile(name)
        }
    }


    fun setCurrentProfile(profile: Profile) {
        viewModelScope.launch {
            userRepository.setCurrentProfile(profile)
        }
    }
}