package opnutz.eu.fond.ui.accountdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import opnutz.eu.fond.data.repository.AccountRepository
import opnutz.eu.fond.data.repository.OperationRepository
import javax.inject.Inject


@HiltViewModel
class AccountDetailViewModel @Inject constructor(
    private val accountRepository: AccountRepository,
    private val operationRepository: OperationRepository
) : ViewModel() {

    fun account(id: Long) = accountRepository.accountWitOperation(id)

    fun createOperation(accountId: Long, name: String, amount: Double, date: String) {
        viewModelScope.launch {
            operationRepository.createOperation(accountId, name, amount, date)
        }
    }
}