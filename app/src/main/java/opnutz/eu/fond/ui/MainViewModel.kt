package opnutz.eu.fond.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import opnutz.eu.fond.data.repository.ProfileRepository
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val userRepository: ProfileRepository
) : ViewModel() {

    fun resetCurrentProfile() {
        viewModelScope.launch {
            userRepository.setCurrentProfile(null)
        }
    }
}