package com.jeric.metromart.ui.navigation

import kotlinx.serialization.Serializable


@Serializable
sealed class Routes {
    @Serializable
    data object Home : Routes()

    @Serializable
    data object Search : Routes()

    @Serializable
    data class Details(val id: Int) : Routes()

}