// presentation/list/UserListViewModel.kt
package com.extrime.myrandomusers.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.extrime.myrandomusers.domain.model.User
import com.extrime.myrandomusers.domain.usecase.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {

    private val _usersState = MutableStateFlow<List<User>>(emptyList())
    val usersState: StateFlow<List<User>> = _usersState.asStateFlow()

    private val _loadingState = MutableStateFlow(false)
    val loadingState: StateFlow<Boolean> = _loadingState.asStateFlow()

    private val _errorState = MutableStateFlow<String?>(null)
    val errorState: StateFlow<String?> = _errorState.asStateFlow()

    private val _isCachedData = MutableStateFlow(false)
    val isCachedData: StateFlow<Boolean> = _isCachedData.asStateFlow()

    private var isInitialLoad = true

    // Сохраняем параметры фильтрации
    private var currentGender: String? = null
    private var currentNationality: String? = null

    fun loadUsers(
        count: Int = 20,
        gender: String?,
        nationality: String?,
        forceRefresh: Boolean = false
    ) {
        val processedGender = if (gender.isNullOrEmpty()) null else gender
        val processedNationality = if (nationality.isNullOrEmpty()) null else nationality

        // Если данные уже загружены и параметры не изменились, и это не принудительное обновление - не загружаем снова
        if (!forceRefresh && !isInitialLoad &&
            currentGender == processedGender && currentNationality == processedNationality &&
            _usersState.value.isNotEmpty()) {
            return
        }

        _loadingState.value = true
        _errorState.value = null
        _isCachedData.value = false

        // Сохраняем текущие параметры
        currentGender = processedGender
        currentNationality = processedNationality

        viewModelScope.launch {
            val result = getUsersUseCase.execute(count, processedGender, processedNationality)
            result.fold(
                onSuccess = { users ->
                    _usersState.value = users
                    _isCachedData.value = result.isSuccess && users.isNotEmpty()
                    isInitialLoad = false
                },
                onFailure = { error ->
                    _errorState.value = "Не удалось выполнить запрос"
                    // Не очищаем список при ошибке, если данные уже есть
                    if (_usersState.value.isEmpty()) {
                        _usersState.value = emptyList()
                    }
                }
            )
            _loadingState.value = false
        }
    }

    fun clearError() {
        _errorState.value = null
    }

    fun refreshUsers(
        count: Int = 20,
        gender: String?,
        nationality: String?
    ) {
        loadUsers(count, gender, nationality, forceRefresh = true)
    }

    // Метод для получения текущих пользователей без перезагрузки
    fun getCurrentUsers(): List<User> {
        return _usersState.value
    }
}