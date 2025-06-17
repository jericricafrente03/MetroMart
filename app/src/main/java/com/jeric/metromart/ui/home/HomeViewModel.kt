package com.jeric.metromart.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeric.metromart.domain.model.GithubModel
import com.jeric.metromart.domain.use_cases.UseCases
import com.jeric.metromart.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCases: UseCases
): ViewModel() {

    private val _uiState = MutableStateFlow(HomeStateEvent.UiState())
    val uiState: StateFlow<HomeStateEvent.UiState> get() = _uiState.asStateFlow()

    private val _navigation = Channel<HomeStateEvent.Navigation>()
    val navigation: Flow<HomeStateEvent.Navigation> = _navigation.receiveAsFlow()

    init {
        loadData()
    }

    private fun loadData() = viewModelScope.launch {
        useCases.fetchDataUseCase().collectLatest { result ->
            when(result){
                is DataState.Error -> _uiState.update { it.copy(error = result.message) }
                is DataState.Loading -> _uiState.update { it.copy(isLoading = true) }
                is DataState.Success -> {
                    _uiState.update { it.copy(isSuccess = result.data ?: emptyList()) }
                    delay(1000)
                    _uiState.update { it.copy(isLoading = false) }
                }
            }
        }
    }

    fun onEvent(event: HomeStateEvent.Event){
        when(event){
            is HomeStateEvent.Event.GotoDetailsEvent -> viewModelScope.launch {
                _navigation.send(HomeStateEvent.Navigation.GotoDetailsNav(event.id))
            }
            HomeStateEvent.Event.GotoSearchEvent -> viewModelScope.launch {
                _navigation.send(HomeStateEvent.Navigation.GotoSearch)
            }
        }
    }
}

object HomeStateEvent{
    data class UiState(
        val isLoading: Boolean = false,
        val error: String ?=null,
        val isSuccess: List<GithubModel> = emptyList()
    )
    sealed class Navigation {
        data class GotoDetailsNav(val id: Int): Navigation()
        data object GotoSearch : Navigation()
    }
    sealed interface Event{
        data object GotoSearchEvent: Event
        data class GotoDetailsEvent(val id: Int): Event
    }
}

