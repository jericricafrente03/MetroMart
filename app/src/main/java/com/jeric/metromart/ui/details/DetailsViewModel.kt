package com.jeric.metromart.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeric.metromart.domain.model.GithubModel
import com.jeric.metromart.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    private val _uiState = MutableStateFlow(DetailsStateEvent.UiState())
    val uiState: StateFlow<DetailsStateEvent.UiState> get() = _uiState.asStateFlow()

    private val _navigation = Channel<DetailsStateEvent.Navigation>()
    val navigation: Flow<DetailsStateEvent.Navigation> = _navigation.receiveAsFlow()


    fun onEvent(event: DetailsStateEvent.Event){
        when(event){
            is DetailsStateEvent.Event.GotoDetailsEvent -> getDetails(event.id)
            DetailsStateEvent.Event.GotoHomeEvent -> {
                viewModelScope.launch {
                    _navigation.send(DetailsStateEvent.Navigation.GotoHomeNav)
                }
            }
        }
    }

    private fun getDetails(id: Int) = viewModelScope.launch {
        _uiState.update { it.copy(isLoading = true) }
        val result = useCases.selectedDataUseCase(id = id)
        delay(500)
        _uiState.update { it.copy(isSuccess = result, isLoading = false) }
    }

}

object DetailsStateEvent {
    data class UiState(
        val isLoading: Boolean = false,
        val error: String ?=null,
        val isSuccess: GithubModel?= null
    )
    sealed class Navigation {
        data object GotoHomeNav: Navigation()
    }
    sealed interface Event{
        data class GotoDetailsEvent(val id: Int): Event
        data object GotoHomeEvent: Event
    }
}
