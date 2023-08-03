package opnutz.eu.fond.ui.accounts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import opnutz.eu.fond.data.repository.AccountRepository
import opnutz.eu.fond.data.repository.ProfileRepository
import javax.inject.Inject


@HiltViewModel
class AccountsViewModel @Inject constructor(
    private val profileRepository: ProfileRepository,
    private val accountRepository: AccountRepository
) : ViewModel() {

    val currentProfile = profileRepository.currentProfileWithAccountsAndOperations

    fun createAccount(profileId: Long, name: String, ceiling: Double) {
        viewModelScope.launch {
            accountRepository.createAccount(profileId, name, ceiling)
        }
    }
}