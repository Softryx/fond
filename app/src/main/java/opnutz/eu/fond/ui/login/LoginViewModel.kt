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
    private val profileRepository: ProfileRepository
) : ViewModel() {

    val profiles = profileRepository.watchProfiles()

    val currentProfile = profileRepository.currentProfile

    fun createProfile(name: String) {
        viewModelScope.launch {
            profileRepository.createProfile(name)
        }
    }

    fun setCurrentProfile(profile: Profile) {
        viewModelScope.launch {
            profileRepository.setCurrentProfile(profile)
        }
    }
}