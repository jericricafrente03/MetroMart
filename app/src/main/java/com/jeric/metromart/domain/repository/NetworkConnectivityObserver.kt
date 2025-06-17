package com.jeric.metromart.domain.repository

import com.jeric.metromart.util.NetworkStatus
import kotlinx.coroutines.flow.StateFlow

interface NetworkConnectivityObserver {
    val networkStatus: StateFlow<NetworkStatus>
}