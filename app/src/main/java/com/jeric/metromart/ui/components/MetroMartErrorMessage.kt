package com.jeric.metromart.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun MetroMartErrorMessage(
    message: String
) {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()){
        Text(message)
    }
}